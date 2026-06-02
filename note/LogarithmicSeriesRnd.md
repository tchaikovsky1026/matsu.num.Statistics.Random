# Logarithmic Series Distribution, Random
## Overview of Logarithmic Series Distribution
### Definitions

対数級数分布は, 単一のパラメータ $p$ ( $0 \le p < 1$ ) によって特徴づけられる離散確率分布である.
確率変数 $X$ が, パラメータ $p$ の対数級数分布に従う ( $X \sim \text{LogSeries}(p)$ ) とき,
確率質量関数 $P_X(k)$ は

$$
P_X(k) =\frac{-p}{\log(1-p)} \frac{p^{k-1}}{k} \quad \text{for } k = 1,2,...
$$

となる (ただし, $0^0=1$ とし, $p=0$ のとき $\frac{-p}{\log(1-p)}=1$ とする).


## Random Value Generator

(参考文献: A.W. Kemp, "Efficient generation of logarithmically distributed pseudo‐random variables." Journal of the Royal Statistical Society: Series C (Applied Statistics) 30.3 (1981): 249-253.)

対数級数分布の累積分布関数は初等的でないので, 逆関数法は適さない.
そこで, 確率質量関数が幾何分布に近い形であることを参考に,

1. $0 \le Y < 1$ の範囲 (の一部) で $y$ を発生
2. $1-Y$ を成功確率とする幾何分布により $X$ を発生 ( $P_{X|Y}(k|y) = (1-y)y^{k-1}$ )

という2段階で $X \sim \text{LogSeries}(p)$ を得ることを考える.

$Y$ の確率密度関数を $f_Y(y)$ とすると,
$X$ の確率質量関数 $P_X(k)$ は,

$$
\begin{split}
P_X(k) &= \int_0^1 dy\ P_{X|Y}(k|y) f_Y(y) \\
&= \int_0^1 dy\ f_Y(y) (1-y)y^{k-1}
\end{split}
$$

となる.
したがって, $0 \le y \le  p$ をとるようにし,
$f_Y(y) = -\frac{1}{\log (1-p)}\frac{1}{1-y}$ を満たすなら,
$X \sim \text{LogSeries}(p)$ となる.
標準一様分布に従う確率変数 $U$ に対して
$Y = 1-(1-p)^{U}$ とすれば,
$Y$ はこの確率密度関数を満たす.

すなわち, 次のアルゴリズムにより $\text{LogSeries}(p)$ に従う乱数が得られる.

1. 標準一様分布に従う乱数 $u$ を発生させ, $y = 1-(1-p)^{u}$ を計算 ( $0 \le y \le  p$ をとる).
3. 標準指数分布に従う乱数 $e$ を発生させ, $x = 1 + \lfloor -e/\log (y) \rfloor$ を計算して返す
( $y=0$ の場合は $-\frac{1}{\log (y)} = 0$ とみなす).
