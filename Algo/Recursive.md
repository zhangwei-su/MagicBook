# backtrack
**解决一个回溯问题，实际上就是一个决策树的遍历过程**。你只需要思考 3 个问题：

1. 路径：也就是已经做出的选择。(Visited)
2. 选择列表：也就是你当前可以做的选择。(for-loop not in visited)
3. 结束条件：也就是到达决策树底层，无法再做选择的条件。

```
result = []  
def backtrack(路径, 选择列表):  
 if 满足结束条件:  
 result.add(路径)  
 return  
  
 for 选择 in 选择列表://我们只要在递归之前做出选择，在递归之后撤销刚才的选择
    # 做选择
    将该选择从选择列表移除
    路径.add(选择)
    backtrack(路径, 选择列表)
    # 撤销选择
    路径.remove(选择)
    将该选择再加入选择列表
 ```
 **其核心就是 for 循环里面的递归，在递归调用之前「做选择」，在递归调用之后「撤销选择」**

**前序遍历的代码在进入某一个节点之前的那个时间点执行，后序遍历代码在离开某个节点之后的那个时间点执行**

符合回溯框架，而且时间复杂度都不可能低于 O(N!)，因为穷举整棵决策树是无法避免的。**这也是回溯算法的一个特点，不像动态规划存在重叠子问题可以优化，回溯算法就是纯暴力穷举，复杂度一般都很高**。

# Combination Permutation
[[Combination Permutation]]
# Tree
## lowest common ancestor
LCA
- If guaranteed, post-order, return when node met, so may get the one 1st Node met OR the 1st common father
- If NOT guaranteed, when got Only one node (1st met), dfs to ensure another one in subtree of 1st met.

## Recursive in Recursive
[[LC863 All Nodes Distance K in Binary Tree]]
二叉树Recursive中，可以嵌套另一种Recursive