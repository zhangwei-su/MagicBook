# My understanding
## basic function
a space-efficient probabilistic data structure. in other words, a query returns either "possibly in set" or "definitely not in set"

Big bitArray + Several Hash func, Hash Funcs map value to several bits (set 1, same num as funcs) in Big bitArray
if one of query target map bits is not One in bitArray, that means not in Set.
If all of query target map bit are ONE in bitArray, that means MAYBE in set.

Pros: Save mem,  usually ==never return a false negative==
Cons: ==Maybe False positive==.

## scenario
Check whether in a Set,  if false positive, Not too damage 
spam filter, cache filter, 
## balance
the more items added, the larger the probability of false positives.
 fewer than 10 bits per element are required for a 1% false positive probability
 
## comparing to other solution
1. URL->MD5/SHA-1->Set/HashMap
2. Bit-Map, setup BitSet，URL->Hash func->Bit

1. URL compressed to 128Bit(MD5) or 160Bit(SHA-1), Saved Mem, But still linear increase mem usage.
2. Save more mem,  but Large Data + Single Hash Func may cause high rate collision.

## Tuning on Bloom Filter
-   _m =_ number of bits in a Bloom filter
-   _n =_ number of elements to insert
-   _k =_ number of hash functions
so,  ==accuracy positive correlation with m/n,  and k==


---


# 布隆过滤器使用场景

> 之前在《数学之美》里面看到过布隆过滤器的介绍。那么什么场景下面需要使用布隆过滤器呢？

看下下面几个问题

-   字处理软件中，需要检查一个英语单词是否拼写正确
    
-   在 FBI，一个嫌疑人的名字是否已经在嫌疑名单上
    
-   在网络爬虫里，一个网址是否被访问过
    
-   yahoo, gmail等邮箱垃圾邮件过滤功能
    

> 以上这些场景有个共同的问题：如何查看一个东西是否在有大量数据的池子里面。

通常的做法有如下几种思路：

-   数组
    
-   链表
    
-   树、平衡二叉树、Trie
    
-   Map (红黑树)
    
-   哈希表
    

> 上面这几种数据结构配合一些搜索算法是可以解决数据量不大的问题的，如果当集合里面的数据量非常大的时候，就会有问题。比如：
> 
> 有500万条记录甚至1亿条记录？这个时候常规的数据结构的问题就凸显出来了。数组、链表、树等数据结构会存储元素的内容，一旦数据量过大，消耗的内存也会呈现线性增长，最终达到瓶颈。哈希表查询效率可以达到O(1)。但是哈希表需要消耗的内存依然很高。使用哈希表存储一亿 个垃圾 email 地址的消耗？哈希表的做法：首先，哈希函数将一个email地址映射成8字节信息指纹；考虑到哈希表存储效率通常小于50%（哈希冲突）；因此消耗的内存：8 * 2 * 1亿 字节 = 1.6G 内存，普通计算机是无法提供如此大的内存。这个时候，布隆过滤器（Bloom Filter）就应运而生。

在继续介绍布隆过滤器的原理时，先讲解下关于哈希函数的预备知识。

# 哈希函数

> 哈希函数的概念是：将任意大小的数据转换成特定大小的数据的函数，转换后的数据称为哈希值或哈希编码。下面是一幅示意图：


![[Pasted image 20210906202408.png]]

可以明显的看到，原始数据经过哈希函数的映射后称为了一个个的哈希编码，数据得到压缩。哈希函数是实现哈希表和布隆过滤器的基础。

# 布隆过滤器介绍

-   巴顿.布隆于一九七零年提出
    
-   一个很长的二进制向量 （位数组）
    
-   一系列随机函数 (哈希)
    
-   空间效率和查询效率高
    
-   不会漏判，但是有一定的误判率（哈希表是精确匹配）
    

# 布隆过滤器原理

布隆过滤器（Bloom Filter）的核心实现是一个超大的位数组和几个哈希函数。假设位数组的长度为m，哈希函数的个数为k

![[Pasted image 20210906202632.png]]

> 以上图为例，具体的操作流程：假设集合里面有3个元素{x, y, z}，哈希函数的个数为3。首先将位数组进行初始化，将里面每个位都设置位0。对于集合里面的每一个元素，将元素依次通过3个哈希函数进行映射，每次映射都会产生一个哈希值，这个值对应位数组上面的一个点，然后将位数组对应的位置标记为1。查询W元素是否存在集合中的时候，同样的方法将W通过哈希映射到位数组上的3个点。如果3个点的其中有一个点不为1，则可以判断该元素一定不存在集合中。反之，如果3个点都为1，则该元素可能存在集合中。注意：此处不能判断该元素是否一定存在集合中，可能存在一定的误判率。可以从图中可以看到：假设某个元素通过映射对应下标为4，5，6这3个点。虽然这3个点都为1，但是很明显这3个点是不同元素经过哈希得到的位置，因此这种情况说明元素虽然不在集合中，也可能对应的都是1，这是误判率存在的原因。

# 添加元素

-   将要添加的元素给k个哈希函数
    
-   得到对应于位数组上的k个位置
    
-   将这k个位置设为1
    

# 查询元素

-   将要查询的元素给k个哈希函数
    
-   得到对应于位数组上的k个位置
    
-   如果k个位置有一个为0，则肯定不在集合中
    
-   如果k个位置全部为1，则可能在集合中