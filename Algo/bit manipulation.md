manipulation -   /məˌnɪpjuˈleɪʃn/
Allways check with ZERO (not ONE): check !=0 instead of ==1
- fullUsed = (1<<nums.length) - 1; //not 1<<nums.length - 1
- used & (1<<i)) != 0 // not used & (1<<i) != 0
- n & (n-1)//remove last 1
- lowbit(n)->n&(-n)// = a^(a&(a-1)), keep lowest bit One
- 