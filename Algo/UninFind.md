# template
```
class UnionFindSet {
  public boolean Union(int u, int v) {
      int pu = Find(u);
      int pv = Find(v);
      if (pu == pv) return false;
     parents_[pv] = pu;
}
public int Find(int u) {
	if (parents_[u] == u) return u;
	int p = Find(parents_[u]);
	parents_[u] = p;
	return p;
}
```