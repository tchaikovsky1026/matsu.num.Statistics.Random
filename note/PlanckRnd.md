# Planck Distribution, Random
## Overview of (Standard) Planck Distribution
### Definitions

(標準) Planck 分布は, shape parameter $\alpha>0$ により特徴づけられる連続分布であり,
$X$ が shape parameter $\alpha$ の Planck 分布に従う ( $X \sim \text{Planck}(\alpha)$ ) とき,
確率密度関数 $f_X(x)$ は,

$$
f_X(x) = \frac{1}{\Gamma(\alpha+1) \zeta(\alpha+1)} \frac{x^\alpha}{\exp(x) - 1} \quad \text{for } x > 0
$$

となる.
ただし, $\Gamma ()$ はガンマ関数, $\zeta ()$ は Riemann ゼータ関数である.


### Related Distributions

- If $Y \sim \text{sGamma} (\alpha + 1)$ and $Z \sim \text{Zeta} (\alpha + 1)$ are independent,
  then $\frac{Y}{Z} \sim \text{Planck}(\alpha)$.
    - $\text{sGamma}()$ represents standard gamma distribution with shape parameter (scale parameter is fixed to 1),
      and $\text{Zeta}()$ represents zeta distribution. See also `GammaAndBetaRnd.md` and `ZetaRnd.md`.

**(Proof memo)**

独立な確率変数 $Y \sim \text{sGamma} (\alpha + 1)$ と $Z \sim \text{Zeta} (\alpha + 1)$ の確率密度関数 / 確率質量関数は,

$$
f_Y(y) = \frac{1}{\Gamma(\alpha+1)} y^{\alpha}\exp (-y) \quad \text{for } y > 0,
$$

$$
P_Z(k) = \frac{1}{\zeta(\alpha+1)} k^ {-\alpha -1} \quad \text{for } k = 1,2,...
$$

である.
$X = \frac{Y}{Z}$ の確率密度関数 $f_X(x)$ は,
$Z = k$ とした各 $k$ について $y = kx$ という変数変換したものの総和であるので,

$$
\begin{split}
f_X(x) &= \sum_{k=1}^{\infty} k P_Z(k)f_Y(kx) \\
  &= \frac{1}{\Gamma(\alpha+1) \zeta(\alpha+1)} \frac{x^\alpha}{\exp(x) - 1}
\end{split}
$$

が得られる.


## Random Value Generator

独立な確率変数 $Y \sim \text{sGamma} (\alpha + 1)$ と $Z \sim \text{Zeta} (\alpha + 1)$
から $\frac{Y}{Z} \sim \text{Planck}(\alpha)$ を得る.
各乱数生成については `GammaAndBetaRnd.md`, `ZetaRnd.md` に従う.
