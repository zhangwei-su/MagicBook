# Graph
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

## All-Pair-Shortest-Paths
### Floyd-Warshall
### Johnson
## Is-Bipartite

## DFS-Improve

## Eulerian-Path-And-Circuit

## Topological-Sorting

## Strongly-Connected-Components
强连通分量
### Kosaraju
### Tarjan
## Articulation-Points
图的割点

## Minimum-Spanning-Tree
### Kruskal
### Prime
### Boruvka


--

图论的各种基本算法

[^DAG]:Directed Acyclic Graph