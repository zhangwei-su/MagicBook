
# Math lab
- min/max/abs
- double log (double a)/double pow (double a, double b)
- double floor (double a)//largest double value that less than or equal to the argument and is equal to a mathematical integer
- double ceil (double a)// the smallest double value that is greater than or equal to the argument and is equal to a mathematical integer.

# Geometry
## Manhattan Distance
where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|

# GCD/LCM
GCD: Greatest Common Divisor def gcd(a, b)
```
def gcd(a, b): 
	while b != 0: t = a % b 
	a = b 
	b = t 
	return a
```
LCM: Least Common Multiple
```
def lcm(a,b): return a*b/gcd(a, b)
```


# random
## sampling
```
//stream代表数据流
//reservoir代表返回长度为k的池塘
//从stream中取前k个放入reservoir；

for ( int i = 1; i < k; i++)  
    reservoir[i] = stream[i];  
for (i = k; stream != null; i++) {  
    p = random(0, i);  
    if (p < k) reservoir[p] = stream[i];  
return reservoir;
```
[https://leetcode.com/discuss/interview-question/451431/Facebook-or-Onsite-or-Generate-random-max-index](https://leetcode.com/discuss/interview-question/451431/Facebook-or-Onsite-or-Generate-random-max-index)

## Random func from existing API
```
rand2->rand4: (rand2() - 1） * 2 + rand2() rand6->rand2: (rand6%2)+1 rand7->rand5 : const rand5 function rand5() { var result = 7; // arbitrarily large while (result > 5) { result = rand7(); } return result; } 398. Random Pick Index

reservoir sampling to get 1/n (0~n-1, equal chance);

int p = (int)(Math.random()*n);

if (p==0) update ret; //1/n chance
```
指数扩大，“拒绝采样”修剪，倍数缩小

rand7->rand10 int rand10() { while (true) { int num = (rand7() - 1) * 7 + rand7(); //1~49 if (num <= 40) return num % 10 + 1; } }

## Random Shuffle
```
void RandomShuffle(int a[], int n){
    for(int i=0; i<n; ++i){
        int j = rand() % (n-i) + i;// 产生i到n-1间的随机数
        Swap(a[i], a[j]);
    }
} // int rand = (int)(Math.random() * range) + min;
```

# Log
## 换底公式
```java
public static int getRightMostUnsetBit(int n){
        return (int)(Math.log(~n & (n+1))/Math.log(2)); 
	//换底公式: 2^X=~n&(n+1) => X=log(~n&(n+1)) [2 base] = Math.log(~n & (n+1))/Math.log(2) [e base]

}
System.out.println(getRightMostUnsetBit(1));//1
System.out.println(getRightMostUnsetBit(3));//2
System.out.println(getRightMostUnsetBit(7));//3
System.out.println(getRightMostUnsetBit(6));//0
```

# other
Quick switch 0<->1 : 1-a
Any equation need be changed to better one.
[[LC1499 Max Value of Equation]]
