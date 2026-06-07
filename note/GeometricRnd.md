# Geometric Distribution, Random
## Overview of Geometric Distribution
### Definitions

幾何分布は, 成功確率 $p$ ( $0 < p \le 1$ ) によって特徴づけられる離散確率分布である.
確率変数 $X$ が, 成功確率 $p$ の幾何分布に従う ( $X \sim \text{Geo}(p)$ ) とき,
確率質量関数 $P_X(k)$ は

$$
P_X(k) = p (1-p)^{k-1} \quad \text{for } k = 1,2,...
$$

となる (ただし, $0^0=1$ とする).

## Random Value Generator

幾何分布は指数分布を利用して作ることができる.
scale parameter が $\theta$ の指数分布に従う確率変数 $X$ ( $X \sim \text{Exp}(\theta)$ )
を考え, $Y = 1+ \lfloor X \rfloor$ としたとき,
$Y = k$ となる確率 ( $\mathbb{P}(Y = k)$ ) は,

$$
\begin{split}
\mathbb{P}(Y = k) &= \int_{k-1}^k dx\ \frac{1}{\theta} \exp (-x/\theta) \\
& = \exp (-(k-1)/\theta) (1 - \exp (-1/\theta))
\end{split}
$$

となる.
ただし, $\lfloor \rfloor$ は floor 関数である.
そこで, $\theta = - \frac{1}{\log (1-p)}$ とすれば,
$\mathbb{P}(Y = k) = p (1-p)^{k-1}$ を得る.

すなわち,　標準指数分布に従う乱数 $e$ を発生させ,
$y = 1 + \lfloor -e/\log (1-p) \rfloor$
とすれば,
$y \sim \text{Geo}(p)$ となる.
$p = 1$ の場合は, $-\frac{1}{\log (1-p)} = 0$ とみなす.
