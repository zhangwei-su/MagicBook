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
所谓「无权图」，与其说每条「边」没有权重，不如说每条「边」的权重都是 1，从起点start到任意一个节点之间的路径权重就是它们之间「边」的条数，那可不就是step变量记录的值么？

再加上 BFS 算法利用for循环一层一层向外扩散的逻辑和visited集合防止走回头路的逻辑，当你每次从队列中拿出节点cur的时候，从start到cur的最短权重就是step记录的步数。

现在我们想解决「加权图」中的最短路径问题，「步数」已经没有参考意义了，「路径的权重之和」才有意义，所以这个for循环可以被去掉。

如果你想同时维护`depth`变量，让每个节点`cur`知道自己在第几层，可以想其他办法，比如新建一个`State`类，记录每个节点所在的层数

加权图中的 Dijkstra 算法和无权图中的普通 BFS 算法不同，在 Dijkstra 算法中，你第一次经过某个节点时的路径权重，不见得就是最小的，所以对于同一个节点，我们可能会经过多次，而且每次的`distFromStart`可能都不一样

Dijkstra 可以理解成一个带 dp table（或者说备忘录）的 BFS 算法，伪码如下：
```java
// 返回节点 from 到节点 to 之间的边的权重
int weight(int from, int to);

// 输入节点 s 返回 s 的相邻节点
List<Integer> adj(int s);

// 输入一幅图和一个起点 start，计算 start 到其他节点的最短距离
int[] dijkstra(int start, List<Integer>[] graph) {
    // 图中节点的个数
    int V = graph.length;
    // 记录最短路径的权重，你可以理解为 dp table
    // 定义：distTo[i] 的值就是节点 start 到达节点 i 的最短路径权重
    int[] distTo = new int[V];
    // 求最小值，所以 dp table 初始化为正无穷
    Arrays.fill(distTo, Integer.MAX_VALUE);
    // base case，start 到 start 的最短距离就是 0
    distTo[start] = 0;

    // 优先级队列，distFromStart 较小的排在前面
    Queue<State> pq = new PriorityQueue<>((a, b) -> {
        return a.distFromStart - b.distFromStart;
    });

    // 从起点 start 开始进行 BFS
    pq.offer(new State(start, 0));

    while (!pq.isEmpty()) {
        State curState = pq.poll();
        int curNodeID = curState.id;
        int curDistFromStart = curState.distFromStart;

        if (curDistFromStart > distTo[curNodeID]) {
            // 已经有一条更短的路径到达 curNode 节点了
            continue;
        }
        // 将 curNode 的相邻节点装入队列
        for (int nextNodeID : adj(curNodeID)) {
            // 看看从 curNode 达到 nextNode 的距离是否会更短
            int distToNextNode = distTo[curNodeID] + weight(curNodeID, nextNodeID);
            if (distTo[nextNodeID] > distToNextNode) {
                // 更新 dp table
                distTo[nextNodeID] = distToNextNode;
                // 将这个节点以及距离放入队列
                pq.offer(new State(nextNodeID, distToNextNode));
            }
        }
    }
    return distTo;
}
```
`distTo`数组可以理解成我们熟悉的 dp table，因为这个算法逻辑就是在不断的最小化`distTo`数组中的元素：

如果你能让到达`nextNodeID`的距离更短，那就更新`distTo[nextNodeID]`的值，让你入队，否则的话对不起，不让入队。

**因为两个节点之间的最短距离（路径权重）肯定是一个确定的值，不可能无限减小下去，所以队列一定会空，队列空了之后，`distTo`数组中记录的就是从`start`到其他节点的最短距离**。

如果只关心起点`start`到某一个终点`end`的最短路径，
因为优先级队列自动排序的性质，**每次**从队列里面拿出来的都是`distFromStart`值最小的，所以当你从队头拿出一个节点，如果发现这个节点就是终点`end`，那么`distFromStart`对应的值就是从`start`到`end`的最短距离。

本文实现的 Dijkstra 算法，使用了 Java 的PriorityQueue这个数据结构，这个容器类底层使用二叉堆实现，但没有提供通过索引操作队列中元素的 API，所以队列中会有重复的节点，最多可能有E个节点存在队列中。

所以本文实现的 Dijkstra 算法复杂度并不是理想情况下的O(ElogV)，而是O(ElogE)，可能会略大一些，因为图中边的条数一般是大于节点的个数的。

**因为 Dijkstra 计算最短路径的正确性依赖一个前提：路径中每增加一条边，路径的总权重就会增加**。

https://labuladong.gitee.io/algo