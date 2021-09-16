# BFS
[[BFS#standard]]

# Diff to BFS
## edge weight != 1 and > 0
所谓「无权图」，与其说每条「边」没有权重，不如说每条「边」的权重都是 1，从起点start到任意一个节点之间的路径权重就是它们之间「边」的条数，那可不就是step变量记录的值么？

再加上 BFS 算法利用for循环一层一层向外扩散的逻辑和visited集合防止走回头路的逻辑，当你每次从队列中拿出节点cur的时候，从start到cur的最短权重就是step记录的步数。

现在我们想解决「加权图」中的最短路径问题，「步数」已经没有参考意义了，「路径的权重之和」才有意义，所以这个for循环可以被去掉。

加权图中的 Dijkstra 算法和无权图中的普通 BFS 算法不同，在 Dijkstra 算法中，你第一次经过某个节点时的路径权重，不见得就是最小的，所以对于同一个节点，我们可能会经过多次，而且每次的`distFromStart`可能都不一样

## Template
Memo + BFS + greedy->heap：
==All dist are Max/2, to avoid du + duv overflow==
==Ignore equal case , when comparing to dist[]==
==since non-negative edge, dist/memo will prune loop, no visited here==
```java
//All dist are Max/2, to avoid du + duv overflow
Arrays.fill(dist, INF/2);  dist[src] = 0; 
List<int[]>[] adj = new List[n];
void dijkstra(int n, List<int[]>[] adj, int[] dist, int src) { //O(VlogV)
	//MinHeap, always handle lowest cost first.
	PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1])); 
	pq.add(new int[]{src, 0});
	while (!pq.isEmpty()) {
		int[] cur = pq.remove();
		int u = cur[0]; //备选路径末端
		int du = cur[1]; //此条备选路径延伸到这个末端时的路径cost
		//这个末端已经被其他路径抵达，并cost更低，skip 这条备选路径。
		if (du > dist[u]) {continue;} //????????????

		for (int[] nb : adj[u]) {//从这个路径末端延伸
			int v = nb[0];
			int duv = nb[1];
			if (dist[v] > du + duv) { //Ignore equal case
				//可以抵达新vertex （cost is INF/2) 或比其他路径更低cost抵达v
				//添加新末端和路径cost到Queue
				dist[v] = du + duv;
				pq.add(new int[]{v, dist[v]});
			}
		}
	}
}     <---> Greedy + BFS + MemPruning (No Visited here)
```
`dist`数组可以理解成我们熟悉的 dp table/Memo，因为这个算法逻辑就是在不断的最小化`dist`数组中的元素：

**因为两个节点之间的最短距离（路径权重）肯定是一个确定的值，不可能无限减小下去，所以队列一定会空，队列空了之后，`dist`数组中记录的就是从`src`到其他节点的最短距离**。

## Greedy->Heap 
如果只关心起点`src`到某一个终点`end`的最短路径，
因为优先级队列自动排序的性质，**每次**从队列里面==拿出来==的都是`distFromSrc`值最小的

本文实现的 Dijkstra 算法，使用了 Java 的PriorityQueue这个数据结构，这个容器类底层使用二叉堆实现，但没有提供通过索引操作队列中元素的 API，所以队列中会有重复的节点，最多可能有E个节点存在队列中。

所以本文实现的 Dijkstra 算法复杂度并不是理想情况下的O(ElogV)，而是O(ElogE)，可能会略大一些，因为图中边的条数一般是大于节点的个数的。

## No negative Weight
**因为 Dijkstra 计算最短路径的正确性依赖一个前提：路径中每增加一条边，路径的总权重就会增加**。
Dijkstra O(ElogE) 只能解决==没有负权重边的图的单源最短路径问题==

Dijkstra算法是贪心，依据“没有负权重边”的特性，进行优化的BFS。它使用MinHeap做Queue，所以总是优先扩展当前cost最小的路径。由于“没有负权重边”，当前cost最小的路径一定是源点到这个末端Node的cost最小的路径，这样在这个Node上的扩展才有用（如果有负权重，其他cost大的路径可能经过一个负权重边到达这个Node，使之前由cost最小原则在此node做过的扩展操作无用）

## examples
[[LC407 Trapping Rain Water II]]
[[LC1631 Path With Minimum Effort]]
[[LC1102 Path With Maximum Minimum Value]]


https://labuladong.gitee.io/algo


