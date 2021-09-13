# standard

# bidirectional 

双向 BFS 还是遵循 BFS 算法框架的，只是不再使用队列，而是使用 HashSet 方便快速判断两个集合是否有交集。

另外的一个技巧点就是 while 循环的最后交换 q1 和 q2 的内容，所以只要默认扩散 q1 就相当于轮流扩散 q1 和 q2。