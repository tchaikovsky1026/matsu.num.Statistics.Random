# Poisson Distribution, Random

## Overview of Poisson Distribution
### Definitions

Poisson 分布はパラメータ $\lambda (\ge 0)$ により特徴づけられる離散確率分布である.
確率変数 $X$ が パラメータ $(n,p)$ の Poisson 分布に従う ( $X \sim \text{Poi}(\lambda)$ ) とき,
確率質量関数 $P_X(k)$ は

$$
P_X(k) = \frac{\lambda^k \exp(-\lambda)}{k!}
$$

となる
(ただし, $0^0=1$ とする).

### Reproducibility

- If $X_1 \sim \text{Poi}(\lambda_1)$ and $X_2 \sim \text{Poi}(\lambda_2)$ are independent,
  then  $X_1+X_2 \sim \text{Poi}(\lambda_1 + \lambda_2)$.


### Relation to Exponential Distributions
#### (Standard) Exponential Distribution

標準指数分布とは, scale parameter が 1 の指数分布のことである.
$X$ が 標準指数分布に従う ( $X \sim \text{sExp}$ ) とき,
確率密度関数 $f_{X}(x)$ は

$$
f_{X}(x) = \exp (-x)
\quad \text{for } x > 0
$$

と表される.


#### Convert of Exponential Distribution to Poisson Distribution

$\text{sExp}$ に従う独立な乱数列 $X_0,X_1,...$ を考える.
$\lambda \ge 0$ であるような $\lambda$ に対し,
この $X_0,X_1,...$ から整数値をとる $Y (\ge 0)$ を次のように定める.

$$
Y = \min k \quad \text{s.t} \ \sum_{j=0} ^k X_j \ge \lambda
$$

このとき, $Y \sim \text{Poi}(\lambda)$ となる.

**(Proof memo)**

$P(Y \ge k)$ を考える.
$P(Y \ge k) = P(\sum_{j=0} ^{k-1} X_j < \lambda)$ であるので,
$X_0,...,X_{k-1}$ の同時確率密度が

$$
f_{X_0,...,X_{k-1}}(x_0,...,x_{k-1}) = \exp (-(x_0+\cdots + x_{k-1}))
$$

であることより,

$$
\begin{split}
P(Y \ge k) &= \int_0^{\lambda} dx_{0}\int_0^{\lambda-x_0} dx_{1}\cdots
  \int_0^{\lambda- \sum_{j=0}^{k-2} x_j} dx_{k-1} \ f_{X_0,...,X_{k-1}}(x_0,...,x_{k-1}) \\
&= \int_0^{\lambda} dx_{0}\int_0^{\lambda-x_0} dx_{1}\cdots
  \int_0^{\lambda- \sum_{j=0}^{k-2} x_j} dx_{k-1} \ \exp (-(x_0+\cdots + x_{k-1}))
\end{split}
$$

より,

$$
\begin{split}
P(Y = k) &= P(Y \ge k) - P(Y \ge k+1) \\
&= \int_0^{\lambda} dx_{0}\int_0^{\lambda-x_0} dx_{1}\cdots
  \int_0^{\lambda- \sum_{j=0}^{k-2} x_j} dx_{k-1} \ \exp (-\lambda) \\
&= \lambda^k \exp (-\lambda) \int_0^{1} du_{0}\int_0^{1-u_0} du_{1}\cdots
  \int_0^{1- \sum_{j=0}^{k-2} u_j} du_{k-1} \\
&= \frac{\lambda^k \exp(-\lambda)}{k!}
\end{split}
$$

となる.


#### (Standard) Gamma Distribution

(後のため, ガンマ分布について説明しておく.)

標準ガンマ分布とは, scale parameter が 1 のガンマ分布のことである.
$X$ が shape parameter $\alpha(>0)$ の標準ガンマ分布に従う ( $X \sim \text{sGamma}(\alpha)$ ) とき,
確率密度関数 $f_{X}(x)$ は

$$
f_{X}(x) =
\frac{1}{\Gamma(\alpha)} x^{\alpha - 1} \exp (-x)
\quad \text{for } x > 0
$$

と表される.
ただし, $\Gamma()$ はガンマ関数である.
shape parameter が 1 のとき, ガンマ分布は指数分布に一致する.

標準ガンマ分布には, 次の性質がある.

- If $X_1 \sim \text{sGamma}(\alpha_1)$ and $X_2 \sim \text{sGamma}(\alpha_2)$ are independent,
  then $X_1 + X_2 \sim \text{sGamma}(\alpha_1+\alpha_2)$.


## Random Value Generator
### Basic Method with Exponential Distribution

$\text{Poi}(\lambda)$ に従う乱数の最も基本的な実装方法は,
$\text{sExp}$ に従う乱数列 $X_0,X_1,...$ を次々と発生させ,
初めて $\sum_{j=0} ^k X_j \ge \lambda$ となる $k$ を返すというものである.
つまるところ, 発生させた乱数を逐次加えていき, $\lambda$ 以上となったときの加えた個数と関連付けた値を返すことになる.

さて, $X \sim \text{sExp}$ のとき, $U = \exp (-X)$ は標準一様分布
(0 から 1 の範囲の実数値をとる連続一様分布) に従う ( $U \sim \text{sUni}$ ).
これを用いれば, 初めて $\prod_{j=0} ^k U_j \le \exp (-\lambda)$ となる $k$ を返すとしてよい.
通常, 1回の $\exp (-\lambda)$ の計算コストを払ってでも, $\text{sExp}$ に従う乱数を使用するよりも高速である.

これらの方法の計算コストは $O(\lambda)$ であり, $\lambda$ が大きい場合は効率が良いとは言えない.
また, $\text{sUni}$ の方を使用する方法は, 数値計算的には $\exp (-\lambda)$ のアンダーフローに気を付ける必要がある.
よって, どちらの側面からも, これらの方法は $\lambda$ が小さい場合に適していると言える.


### Method with Gamma Distribution

$\text{sExp}$ に従う乱数列 $X_0,X_1,...$ を使用する方法において,
$X_0+X_1+\cdots$ をまとめてシミュレートできれば,
多数個の $\text{sExp}$ に従う乱数を生成を1個の乱数に置き換えることができる.
すでに述べたように $\text{sExp}$ は $\text{sGamma}(1)$ と同一であるため,
$X_0+X_1+\cdots +X_{m-1} \sim \text{sGamma}(m)$ となる.
よって, 最初のアルゴリズムとしては, 次が考えられる.

[Algorithm Gamma]

- $Z \sim \text{sGamma}(m)$ としたときに $Z \ge \lambda$ となる確率が 0 と見なせるような $m$ を選択する.
- $\text{sGamma}(m)$ に従う乱数を発生させ, その値を $z$ とする.
  $\text{Poi}(\lambda -z)$ に従う乱数を発生させ, その値に $m$ を加えて返す.

Algorithm Gamma の方法を再帰的に用い, 最終的にパラメータの小さい Poisson 分布にまで到達すれば,
その後は前述の基本的な方法により得られる.
一方, 途中で $z > \lambda$ となった場合については議論する必要がある.

今考えるべき問題は,
$\text{sExp}$ に従う乱数列 $X_0,X_1,...,X_{m-1}$ について,
$X_0 + X_1 + \cdots + X_{m-1} = z (>\lambda)$ という束縛条件のもとでの条件付分布を考え,
$\sum_{j=0}^{k}X_j \ge \lambda$ となる最小の $k$ を求めるという問題である.
これは, $Y_j = X_j / z$ とすれば,
$(Y_0,...,Y_{m-1})$ は集中度パラメータがすべて 1 であるような Dirichlet 分布に従うことがわかるので,
$k$ は二項分布 $\text{Bin}(m-1,\frac{\lambda}{z})$ に従う
(参考: BinomialRnd.md).
二項分布に従う乱数の生成法でも, Dirichlet 分布やガンマ分布を利用した二分法的アプローチが使える.

以上より, Poisson 分布に従う乱数発生方法の完全なアルゴリズムは次のようになる.

**[Algorithm Gamma and Binoimal]** : $\text{Poi}(\lambda)$ に従う乱数を発生させる.

- $\lambda$ が小さいとき, basic な方法で乱数生成:
  $U_0,U_1,... \sim \text{sUni}$ とし, $\prod_{j=0} ^k U_j \le \exp (-\lambda)$ となる最小の $k$ を返す.
- $\lambda$ が大きいとき, 整数 $m \ge 1$ を適当に定め, $Z \sim \text{sGamma}(m)$ で乱数発生を行い, その値を $z$ とする.
    - $z \le \lambda$ ならば, $\text{Poi}(\lambda - z)$ に従う乱数を発生させ $k'$ とし, $m + k'$ を返す.
    - $z > \lambda$ ならば, $\text{Bin}(m-1,\frac{\lambda}{z})$ に従う乱数を発生させて返す.
    - できるだけ $z \le \lambda$ となる確率が高くなるように $m$ を選択することが好ましい.

このアルゴリズムでの計算量は, 概して $O(\log (\lambda))$ である.
