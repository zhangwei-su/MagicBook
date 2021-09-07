## HashMap 
```java
map.computeIfAbsent(key, k -> new HashSet<V>()).add(v);
size/put/remove/get
containsKey(K)
keySet/values/entrySet
getOrDefault
```
[[Bloom Filter]]
## Set
```java
size()
add(E element)
remove(Object o)
toArray(T[] a)
```

## PriorityQueue
```java
PriorityQueue minHeap = new PriorityQueue<Pair>((a,b)->a.first - b.first);
add(E)
Remove(obj) O(N)
contains(obj) O(N)
size()
E poll()
E peek()
```

## ArrayDeque / LinkedList
```java
addFirst(e)
addLast(e)
removeFirst
removeLast
getFirst
getLast
```

## ArrayList
```java
size()
add(E element)/add(Index, E)
E remove(int index)
get(int index)
String[] arr = list.toArray(new String[0]) //(not work for Integer/int)
Collections.sort(aList)
Collections.sort(aList, Collections.reverseOrder())
Collections.reverse(alist)
Collections.max(list)
```
### Adjacency List
```java
ArrayList<int[]>[] adj = new ArrayList[n]; //int[] is edge, [v,w]
for (int i=0; i<n; i++) adj[i] = new ArrayList<>();
```
## Array
```java
Arrays.asList(array) //(not work for Integer/int)
Arrays.toString(array)
Arrays.sort(array)
Arrays.sort(arr, Collections.reverseOrder());array.length array[0].length
Arrays.fill(array, -1);
return new int[]{0,0};
int[] dir = {0,1,0,-1,0};
Collections.max(Arrays.asList(array))
```
## Integer/ double
```java
Integer.MAX_VALUE //2^31-1 = 2,147,483,647 (or hexadecimal 0x7FFFFFFF)
Integer.MIN_VALUE //-2^31 = -2147483648
(int)Math.pow(10, 9) = (int)1e9
```
==对于double 的结果，可以用* 0.5，可以用/2.0，不能用/2 long, 64bit-sign, max:(long)1e19 level==

## String
```java
length(); String.valueOf()
charAt(int index)
substring(int beginIndex, int endIndex)
new StringBuilder(str2).reverse().toString();
```
## TreeSet/TreeMap
```java
E floor(E e)/floorKey max(<=)
E ceiling(E e)/ceilingKey min(>=)
getFirst()/firstKey
getLast()/lastKey
remove(object e)
```
