# Binomial Distribution, Random

## Overview of Binomial Distribution
### Definitions

二項分布は試行回数 $n$ と成功確率 $p$ により特徴づけられる離散確率分布である.
確率変数 $X$ が パラメータ $(n,p)$ の二項分布に従う ( $X \sim \text{Bin}(n,p)$ ) とき,
確率質量関数 $P_X(k)$ は

$$
P_X(k) = \frac{n!}{k!(n-k)!} p^k (1-p)^{n-k} \quad \text{for } 0 \le k \le n
$$

となる.

### Reproducibility

- If $X_1 \sim \text{Bin}(n_1,p)$ and $X_2 \sim \text{Bin}(n_2,p)$ are independent,
  then  $X_1+X_2 \sim \text{Bin}(n_1+n_2,p)$.


### Relation to Dirichlet Distributions
#### Dirichlet Distribution

$n+1$ 変数の Dirichlet 分布とは, 非負の確率変数 $(X_0,X_1,...,X_n)$ について,
$\sum_{k=0}^n X_k = 1$ で束縛された連続分布である
(独立に動けるのは $n$ 変数である).
$X_0,...,X_n$ それぞれに対する集中度パラメータ $\alpha_0,...,\alpha_n (>0)$ によって分布は特徴づけられ,
$\text{Dir}(\alpha_0,...,\alpha_n)$ と表す.
$(X_0,X_1,...,X_n) \sim \text{Dir}(\alpha_0,...,\alpha_n)$ であるとき,
確率密度関数 $f_{(X_k)}(x_1,...,x_n)$ は

$$
f_{(X_k)}(x_1,...,x_n) =
\frac{1}{\mathrm{B}(\alpha_0,...,\alpha_n)}
x_0^{\alpha_0 - 1}x_1^{\alpha_1 - 1} \cdots x_n^{\alpha_n - 1}
\quad \text{for } x_k > 0 \ (k=0,1,...,n)
$$

と表される.
ただし, $X_0$ 以外の確率変数の値を $x_1,...,x_n$ とし,
$x_0 = 1 - \sum_{k=1}^n x_k$ の意味である.
$\mathrm{B}(\alpha_0,...,\alpha_n) =
\frac{\Gamma(\alpha_0)\Gamma(\alpha_1)\cdots \Gamma(\alpha_n)}{\Gamma(\alpha_0+\alpha_1+\cdots +\alpha_n)}$
は多変数ベータ関数である.

Dirichlet 分布には, 次の性質がある.

- (Aggregation) If $(X_0,X_1,...,X_n) \sim \text{Dir}(\alpha_0,...,\alpha_n)$,
  then $(X_0,X_1+X_2,X_3,...,X_n) \sim \text{Dir}(\alpha_0,\alpha_1 + \alpha_2,\alpha_3,...,\alpha_n)$.
    - (memo)
      $Z = X_1+X_2$ とおき,
      $(X_0,Z, X_3,...,X_n)$ の確率密度関数を考える.
      これは, $(X_0,X_1,...,X_n)$ の確率密度関数の独立変数を $x_0,x_2,x_3,...,x_n$ とし,
      $x_2$ による周辺化を行えばよい.


#### Convert of Dirichlet Distribution to Binomial Distribution

$n+1$ 変数の, 集中度パラメータがすべて 1 であるような Dirichlet 分布に従う確率変数
$(X_0,X_1,...,X_n)$ を考える
( $(X_0,X_1,...,X_n) \sim \text{Dir}(1,1,...,1)$ ).
この確率密度関数は,

$$
f_{(X_k)}(x_1,...,x_n) = n!
\quad \text{for } x_k > 0 \ (k=0,1,...,n)
$$

である (紛らわしいが, これは一様分布ではない).
$0 \le p \le 1$ であるような $p$ に対し,
この $(X_0,X_1,...,X_n)$ から, 整数値をとる $Y (0 \le Y \le n)$ を次のように定める.

$$
Y = \min k \quad \text{s.t} \ \sum_{j=0} ^k X_j \ge p
$$

このとき, $Y \sim \text{Bin}(n,p)$ となる.

**(Proof memo)**

$P(Y \le k)$ を考える.
$P(Y \le k) = P(\sum_{j=0} ^k X_j \ge p) = P(\sum_{j=k+1} ^n X_j < 1-p)$ であるので,

$$
\begin{split}
P(Y \le k) &= n! \int_0^{1-p} dx_n \int_0^{1-p-x_n} dx_{n-1}\cdots \int_0^{1-p- \sum_{j=k+2}^n x_j} dx_{k+1}
  \int_0^{1 - \sum_{j=k+1}^n x_j} dx_{k} \cdots \int_0^{1 - \sum_{j=2}^n x_j} dx_1 \\
&= n! \int_0^{1-p} dx_n \int_0^{1-p-x_n} dx_{n-1}\cdots \int_0^{1-p- \sum_{j=k+2}^n x_j} dx_{k+1}
  \frac{1}{k!} (1- (x_{k+1}+\cdots +x_n))^k
\end{split}
$$

より,

$$
\begin{split}
P(Y = k) &= P(Y \le k) - P(Y \le k-1) \\
&= \frac{n!}{k!} p^k \int_0^{1-p} dx_n \int_0^{1-p-x_n} dx_{n-1}\cdots \int_0^{1-p- \sum_{j=k+2}^n x_j} dx_{k+1} \\
&= \frac{n!}{k!(n-k)!} p^k (1-p)^{n-k}
\end{split}
$$

となる ( $k=0$ でも成立).


## Random Value Generator
### Naive Method

$\text{Bin}(n,p)$ に従う乱数の素朴な実装方法は,
標準一様分布 ( $\text{sUni}$ ) に従う乱数を $n$ 個発生させ, $p$ 未満である個数を数える,
という方法である.
この方法の計算コストは $O(n)$ であり,
$n$ が大きい場合は効率が良いとは言えない.


### Method with Dirichlet Distribution

二項分布と Dirichlet 分布の関係をもとに,
Dirichlet 分布に従う乱数発生器から二項分布に従う乱数を生成することを考える.
ただし, Dirichlet 分布に従う乱数発生器はすでに実装されているとする.
再掲しておくと,
$\text{Dir}(1,1,...,1)$ に従う乱数 $(X_0,X_1,...,X_n)$ から,
$\sum_{j=0} ^k X_j \ge p$ となるような最小の $k$ を求める.
素朴にこれを行うと, $(X_0,X_1,...,X_n)$ を求める必要があるので,
やはり計算コストは $O(n)$ となってしまう.

しかし, 必要になのは $\sum_{j=0} ^k X_j$ が $p$ を超えるかどうかであるので,
二分法をもとにしたアルゴリズムが構築できれば $O(\log (n))$ とできる.
そのためには, ある程度まとまった $\sum_j X_j$ を一度に生成できる必要がある.
概して, 次のような方法となる.

$(X_0,X_1,...,X_n) \sim \text{Dir}(1,1,...,1)$ であり,
$Y = \sum_{j=0}^{m} X_j$, $Z = \sum_{j=m+1}^{n} X_j$ とすれば,
$(Y,Z) \sim \text{Dir}(m+1,n-m)$ となる (Dirichlet 分布の Aggregation の性質より).
$Y$ の値を $y$ としたとき,
$y \ge p$ であれば二項分布乱数としては $m$ 以下を, $y < p$ であれば $m+1$ 以上を返すことになる.
ちなみに, 2変数の Dirichlet 分布はベータ分布であり,
$(Y,Z) \sim \text{Dir}(m+1,n-m)$ とは $Y \sim \text{Beta}(m+1,n-m)$, $Z = 1-Y$ である.

$y \ge p$ の場合, $\sum_{j=0}^{m} X_j = y$ と束縛したときの $(X_0,...,X_{m})$ をシミュレートし,
$\sum_{j=0} ^{k} X_j$ が $p$ を超えるかどうかを判定することになる.
$\sum_{j=0}^{m} X_j = y$ と束縛したとき, $(X_0,...,X_{m})$ と
$(X_{m+1},...,X_{n})$ が独立となることは明らかである.
さらに, $(X_0,...,X_{m})$ の条件付確率分布は,
$(\frac{X_0}{y},...,\frac{X_m}{y}) \sim \text{Dir}(1,1,...,1)$ である.

$y < p$ の場合, $\sum_{j=m+1}^{n}X_j = 1-y$ と束縛したときの $(X_{m+1},...,X_{n})$ をシミュレートし,
$\sum_{j=m+1} ^{n} X_j$ が $p-y$ を超えるかどうかを判定することになる.

以上より, 二項分布に従う乱数発生方法の完全なアルゴリズムは次のようになる.

**[Algorithm with Dirichlet]** : $\text{Bin}(n,p)$ に従う乱数を発生させる.

- $n$ が小さいとき, naive な方法で乱数生成:
  $\text{sUni}$ に従う乱数を $n$ 個発生させ, $p$ 未満である個数を返す.
- $n$ が大きいとき, 整数 $m (0 \le m \le n-1)$ を適当に定め, $Y \sim \text{Beta}(m+1,n-m)$ なる
  $Y$ を発生させ, その値を $y$ とする.
    - $y \ge p$ ならば, $\text{Bin}(m,\frac{p}{y})$ に従う乱数を発生させて返す.
    - $y < p$ ならば, $\text{Bin}(n-m-1,\frac{p-y}{1-y})$ に従う乱数を発生させて $k'$ とし, $m + k'+1$ を返す.
    - $m$ は二分探索的に, $n/2$ 程度がよい.


### Dirichlet-Distributed Random Value Generator

Dirichlet 分布に従う乱数は, 標準ガンマ分布 ( $\text{sGamma}$ ) に従う乱数から生成できる.
$(X_0,...,X_n) \sim \text{Dir}(\alpha_0,...,\alpha_n)$ の生成は,
$Z_j \sim \text{sGamma}(\alpha_j)$ を生成, $Z = \sum_{j=0}^{n}Z_j$ とし,
$X_j = \frac{Z_j}{Z}$ とすればよい.


## `DirichletBasedBinomialRnd` に関する資料

(このメモは過去のノートである. メンテナンスされていないかもしれない.)

### 原理

二項乱数の生成について, 次の原理を基にしている：
$n$ を非負整数, $p$ を $0 \le p \le 1$ の実数とする.
確率変数 $(X_0,...,X_n)$ が集中度パラメータが1の Dirichlet 分布に従うとき,
$Z$ を

$$
Z = ( X_0 + \cdots + X_k \ge p \text{となる最小の} k)
$$

とすれば, $Z\sim\mathrm{Bin}(n,p)$ である.

`DirichletBasedBinomialRnd` では,
$n+1$ 変数の集中度パラメータが1の Dirichlet 分布の確率変数をいくつかのグループに分け,
集中度母数が2の累乗と端数の集中度パラメータを持つ Dirichlet 分布の確率変数に帰着させることで,
確率変数の個数を $O(\log n)$ に減らしている.
その後, 各グループについて処理を行いシフトをする.
グループの判定やシフト量は, 以下に示した2の累乗パターンと似たものである
(メモのため, ここでは詳細を述べない).
グルーピングは $n+1$ の2進展開で求まる.

2の累乗の集中度パラメータのグループは, 試行回数が小さくなるまで次のアルゴリズムを再帰的に用い,
その後, 素朴な方法により発生させる.

**$\mathrm{Bin}(2^N-1,p)$ に従う乱数の生成**

1. 集中度パラメータが $2^{N-1}$ の2変数 Dirichlet 乱数を生成し, $x_1,x_2$ とする.
2. - $x_1 \ge p$ の場合は, $\mathrm{Bin}(2^{N-1}-1, \frac{p}{x_1})$ により発生させた乱数を返す.
   - $x_1 < p$ の場合は, $\mathrm{Bin}(2^{N-1}-1, \frac{p-x_1}{x_2})$ により発生させた乱数に $2^{N-1}$ を加えて返す.
