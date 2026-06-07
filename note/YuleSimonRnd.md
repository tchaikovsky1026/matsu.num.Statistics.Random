# Yule-Simon Distribution, Random
## Overview of Yule-Simon Distribution
### Definitions

Yule-Simon 分布は単一のパラメータ $\rho$ ( $\rho > 0$ ) によって特徴づけられる離散確率分布である.
確率変数 $X$ が, パラメータ $\rho$ の Yule-Simon 分布に従う ( $X \sim \text{YS}(\rho)$ ) とき,
確率質量関数 $P_X(k)$ は

$$
P_X(k) = \rho \mathrm{B}(k,\rho+1) \quad \text{for } k = 1,2,...
$$

となる ( $\mathrm{B}()$ は ベータ関数 ).

**(memo)**

$P_X(k)$ の正規化は,
$\frac{\Gamma(k)}{\Gamma(k+\rho+1)}
 = \frac{\Gamma(k) (k + \rho - k)}{\rho \Gamma(k+\rho+1)}
 =\frac{1}{\rho} [\frac{\Gamma(k)}{\Gamma(k+\rho)} - \frac{\Gamma(k+1)}{\Gamma(k+\rho+1)}]$
を使えば確認できる.


### Related Distributions

- If $U \sim \text{sUni}$ and $X|U \sim \text{Geo}(U^{1/\rho})$,
  then $X \sim \text{YS}(\rho)$.

**(proof memo)**

$U \sim \text{sUni}$ のとき, $U$ の確率密度関数は $f_U(u) = 1$ for $0 < u <1$ である.
このとき, $W = U^{1/\rho}$ とすれば,

$$
f_W(w) = \rho w^{\rho - 1} \quad \text{for } 0 < w < 1
$$

である.
$X|W \sim \text{Geo}(W)$ とすると, $P_{X|W}(k|w) = w(1-w)^{k-1}$  であるので,

$$
\begin{split}
P_X(k) &= \int_0^1 dw\ P_{X|W}(k|w)f_W(w) \\
  &= \int_0^1 dw\ \rho w^{\rho}(1-w)^{k-1} \\
  &= \rho \mathrm{B}(k,\rho+1)
\end{split}
$$

となる.


## Random Value Generator

$U \sim \text{sUni}$ かつ $X|U \sim \text{Geo}(U^{1/\rho})$ ならば,
 $X \sim \text{YS}(\rho)$ という性質を用いて乱数生成を行う.
$\text{Geo}(p)$ に従う乱数は, 標準指数分布に従う乱数 $e$ を用いて
$1 + \lfloor -e/\log (1-p) \rfloor$
により得られるので
( $\lfloor \rfloor$ は floor 関数), アルゴリズムは次のようにまとめられる.

1. 標準一様分布に従う乱数 $u$ と 標準指数分布に従う乱数 $e$ を発生させる.
2. $x = 1 + \lfloor -e/\log (1-u^{1/\rho}) \rfloor$ とし, return $x$.
