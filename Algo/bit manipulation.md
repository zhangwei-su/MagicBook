manipulation -   /məˌnɪpjuˈleɪʃn/
Allways check with ZERO (not ONE): check !=0 instead of ==1
- fullUsed = (1<<nums.length) - 1; //not 1<<nums.length - 1
- used & (1<<i)) != 0 // not used & (1<<i) != 0
- n & (n-1)//remove lowest bit One
- lowbit(n)->n&(-n) or n^(n&(n-1)), keep lowest bit One.  
- lowZero(n)->  (~n)&(n+1)