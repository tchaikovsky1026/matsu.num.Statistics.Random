# Gamma and Beta Distribution, Random

## ガンマ分布
### 概要

ガンマ分布は shape parameter と scale parameter によって特徴づけられる連続分布である.
確率変数 $X$ が shape parameter $k$ と scale parameter $\theta$ のガンマ分布に従う
$(X\sim\text{Gamma}(k,\theta))$ のとき, $X$ の確率密度関数 $f_X(x)$ は
$$
f_X(x) = \frac{1}{\Gamma(k)\theta^k}x^{k-1}\exp(-x/\theta) \quad \text{for } x > 0
$$
となる.
