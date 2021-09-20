# Graph
[[Java most-used APIs#Adjacency List]]

## basic traverse
```java
/* 图遍历框架 */  
void traverse(Graph graph, int s) {  
 if (visited[s]) return;  
 // 经过节点 s  
 visited[s] = true;  
 for (TreeNode neighbor : graph.neighbors(s))  
 traverse(neighbor);  
 // 离开节点 s  
 visited[s] = false;   
}
```
==当有向图含有环的时候才需要`visited`数组辅助==，如果不含环，连`visited`数组都省了，基本就是多叉树的遍历

## Single-Source-Shortest-Paths
### Dijkstra DAG-Shortest-Paths
DAG[^DAG]
[[Dijkstra]]
### Bellman-Ford
[[LC787 Cheapest Flights Within K Stops]]
```
Bellman-Ford O(VE)
Arrays.fill(dis, (int)1e9); dis[src] = 0;
for(int k = 1 ; k <= K+1 ; k ++) { //只进行K+1轮松弛
    int[] tmp = dis.clone();
    for(int i = 1 ; i < m ; i ++) {// 枚举每一条边
        if(dis[v[i]] > dis[u[i]] + w[i]) //对比新扩展和之前的松弛结果
            tmp[v[i]] = dis[u[i]] + w[i]; //更新从新扩展中得到的更好结果
    }
    dis = tmp;
}
```
## All-Pair-Shortest-Paths
### Floyd-Warshall
[[LC1334 Find the City With the Smallest Number of Neighbors at a Threshold Distance]]
```
int[][] dist = new int[n][n];            
Arrays.fill(dist[i],(int)1e9);
dist[i][i] = 0; 
for (int[] e : edges) { dist[u][v] = d; dist[v][u] = d;}
//for every vertex i and j, check whether the 3rd vertex k and decrease their distance.
void floyd(int n, int[][] dist) { 
	for (int k = 0; k < n; k++) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
			}
		}
	}
}
```
### Johnson
## Is-Bipartite

## DFS-Improve

## Eulerian-Path-And-Circuit
[[LC332 Reconstruct Itinerary]] 一条经过所有边的路径
```
void visit(const string& src) {
	auto& dests = trips_[src];
	while (!dests.empty()) {
		// Get the smallest dest //按题目要求输出
		const string dest = dests.front();
		// Remove the ticket //删边
		dests.pop_front();
		// Visit dest
		visit(dest);
	}
	// Add current node to the route //后序存储
	route_.push_back(src);
}
return vector<string>(route_.crbegin(), route_.crend()); //逆序输出
```

## Topological-Sorting
[[LC269 Alien Dictionary]]
### DFS-Improve
```
for each node:
    if not marked:
        if (DFS-Improve(node) == CYCLE) return CYCLE
return OK
def DFS-Improve(node):
    if node is marked as visited: return OK
    if node is marked as visiting: return CYCLE
    mark node as visiting
    for each new_node in node.neighbors:
        if dfs(new_node) == CYCLE: return CYCLE
    mark node as visited
    add node ot the head of ordered_list or add end, but reverse list when final
    return OK
```
### In-degree


## Strongly-Connected-Components
强连通分量
### Kosaraju
### Tarjan
## Articulation-Points
图的割点

## Minimum-Spanning-Tree
### Kruskal
[[UninFind]]
### Prime
### Boruvka

# Tree style directive Graph
[[LC685 Redundant Connection II]]
树形DG，可以用并查集来找环；有环，优先去除入度为2节点上的环；无环，去除入度为2节点上的边. 树形DG检查环：并查集（环），入度为2的点（多余边）

--

图论的各种基本算法

[^DAG]:Directed Acyclic Graph