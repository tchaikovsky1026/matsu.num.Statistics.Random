# Negative Binomial Distribution, Random

## Overview of Negative Binomial Distribution
### Definitions

負の二項分布は成功回数 $r (\ge 1)$ と成功確率 $p (0 < p \le 1)$ により特徴づけられる離散確率分布であり,
確率 $p$ の Bernoulli を $r$ 回成功するまで繰り返したときの失敗回数の分布が該当する.
確率変数 $X$ が パラメータ $(r,p)$ の負の二項分布に従う ( $X \sim \text{NB}(r,p)$ ) とき,
確率質量関数 $P_X(k)$ は

$$
P_X(k) = \frac{(k+r-1)!}{k!(r-1)!} p^r (1-p)^k \quad \text{for } k \ge 0
$$

となる.


### Related Distributions

- If $Y \sim \text{Gamma}(r,\frac{1}{p}-1)$ (shape parameter $r$ and scale parameter $(\frac{1}{p}-1)$ )
  and $X|Y \sim \text{Poi}(Y)$, then $X \sim \text{NB}(r,p)$.

**(Proof memo)**

$Y \sim \text{Gamma}(r,\frac{1}{p}-1)$, $X|Y \sim \text{Poi}(Y)$ のとき,
$Y$ の確率密度関数と $X|Y$ の (条件付き) 確率質量関数はそれぞれ,

$$
f_Y(y) = \frac{p^r (1-p)^{-r}}{(r-1)!}y^{r-1} \exp (-p(1-p)^{-1}y) , \quad
P_{X|Y}(k|y) = \frac{1}{k!}y^k\exp (-y)
$$

である.
これをもとに, $P_{X}(k) = \int_0^\infty dy \ f_Y(y) P_{X|Y}(k|y)$ を計算する.


## Random Value Generator

$Y \sim \text{Gamma}(r,\frac{1}{p}-1)$ かつ $X|Y \sim \text{Poi}(Y)$ ならば,
$X \sim \text{NB}(r,p)$ という性質を用いて乱数生成を行う.
各乱数生成については GammaAndBetaRnd.md, PoissonRnd.md に従う.
