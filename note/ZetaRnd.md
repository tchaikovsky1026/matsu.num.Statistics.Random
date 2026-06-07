# Zeta Distribution, Random
## Overview of Zeta Distribution
### Definitions

ゼータ分布は, 単一のパラメータ $s$ ( $s > 1$ ) によって特徴づけられる離散確率分布である.
確率変数 $X$ が, パラメータ $s$ のゼータ分布に従う ( $X \sim \text{Zeta}(s)$ ) とき,
確率質量関数 $P_X(k)$ は

$$
P_X(k) =\frac{k^{-s}}{\zeta(s)} \quad \text{for } k = 1,2,...
$$

となる ( $\zeta(s)$ は Riemann ゼータ関数 ).


## Random Value Generator

(参考文献: L. Devroye, Non-Uniform Random Variate Generation, Springer, 1986.)

ゼータ分布の確率質量関数に近い確率密度関数を持つ分布として,
1以上の連続値をとる確率変数 $Z$ で, 累積分布関数 $F_Z(z)$ が次で与えられるものを考える.

$$
F_Z(z) = 1 - z^{-s+1} \quad \text{for } z \ge 1
$$

$Z$ は標準一様分布に従う確率変数 $U$ を, $Z = U^{-\frac{1}{s-1}}$ と変換することで得られる.
このような $Z$ に対して, 1以上の整数値をとる確率変数 $Y$ を, $Y =\lfloor Z \rfloor$ により定める.
この $Y$ の確率質量関数は

$$
P_Y(k) = F_Z(k+1) - F_Z(k) = k^{-s+1} - (k+1)^{-s+1}
$$

となる.

この $Y$ を用いて Rejection Algorithm を考える.
$X \sim \text{Zeta}(s)$ とするとき,

$$
\sup_{k \in \{1,2,...\}} \frac{P_{X}(k)}{P_{Y}(k)} = \frac{P_{X}(1)}{P_{Y}(1)}
  = \frac{1}{\zeta(s)(1 - 2^{-s+1})}
$$

である.
したがって, Rejection Algorithm は $s(k)$ を標準一様分布に従う乱数と比較することになる.
ただし, $s(k)$ は

$$
s(k) = \zeta(s)(1 - 2^{-s+1}) \frac{P_{X}(k)}{P_{Y}(k)} = \frac{1 - 2^{-s+1}}{k(1 - (1+\frac{1}{k})^{-s+1})}
$$

である.
採択確率は $\zeta(s)(1 - 2^{-s+1})$ であり, $s \to 1+0$ で 69 %,
$s \gg 1$ で 100 %となる.

Squeeze Method のために, $s'(k) \le s(k)$ なる, ゼロコストで計算可能な $s'(k)$ として,

$$
s'(k) = \frac{1 - 2^{-s+1}}{k}
$$

を考える.
$s(k)$ との比較の前に $s'(k)$ と比較することで, 比較的高コストなべき乗計算を回避できる可能性がある.
この処理の追加コストは $s'(k)$ との比較のみであり, $s$ が大きいときに効果を発揮する.

以上により, Rejection Algorithm による $\text{Zeta}(s)$ に従う乱数生成は次のようになる.

1. 2個の標準一様乱数を発生させて, $u$, $v$ とする.
2. $y = \lfloor u^{-\frac{1}{s-1}} \rfloor$ を計算する.
3. $v < s'(y)$ なら return $y$. そうでないなら次へ.
4. $v < s(y)$ なら return $y$. そうでないなら (1) に戻る.  
   $s(y)$ の計算には, $s'(y)$ の値を利用するようにする.
