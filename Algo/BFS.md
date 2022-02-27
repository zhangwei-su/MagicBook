# standard
 BFS（广度优先搜索）的算法框架：
```java
// 输入起点，进行 BFS 搜索
int BFS(Node start) {
    Queue<Node> q; // 核心数据结构
    Set<Node> visited; // 避免走回头路

    q.offer(start); // 将起点加入队列
    visited.add(start);

    int step = 0; // 记录搜索的步数
    while (q not empty) {
        int sz = q.size();
        /* 将当前队列中的所有节点向四周扩散一步 */
        for (int i = 0; i < sz; i++) {
            Node cur = q.poll();
            printf("从 %s 到 %s 的最短距离是 %s", start, cur, step);

            /* 将 cur 的相邻节点加入队列 */
            for (Node x : cur.adj()) {
                if (x not in visited) {
                    q.offer(x);
                    visited.add(x);
                }
            }
        }
        step++;
    }
}
```
# bidirectional 

```
s1.insert(start) 
s2.insert(end)
step = 0
while not (s1.empty() || s2.empty()):
    ++step
    swap*(S1, s2)
    s = {}
    for node in s1:
        new_nodes = expand (node)
        if any(new_nodes) in s2: return step + 1
        s.append (new nodes)
    s1 = s
return NOT_FOUND
```

双向 BFS 还是遵循 BFS 算法框架的，只是不再使用队列，而是==使用 HashSet 方便快速判断两个集合是否有交集==。

另外的一个技巧点就是 while 循环的最后交换 q1 和 q2 的内容，所以只要默认扩散 q1 就相当于轮流扩散 q1 和 q2。