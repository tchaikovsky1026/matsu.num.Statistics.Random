# Gamma and Beta Distribution, Random

## Overview of Gamma and Beta Distribution
### Definitions

ガンマ分布は shape parameter と scale parameter によって特徴づけられる連続分布である.
確率変数 $X$ が shape parameter $k > 0$ と scale parameter $\theta > 0$ のガンマ分布に従う
( $X \sim \text{Gamma}(k,\theta)$ ) のとき, $X$ の確率密度関数 $f_X(x)$ は

$$
f_X(x) = \frac{1}{\Gamma(k)\theta^k}x^{k-1}\exp(-x/\theta) \quad \text{for } x > 0
$$

となる ( $\Gamma()$ はガンマ関数).
特に, $\theta = 1$ のとき標準ガンマ分布という場合がある
(本文ではそのように呼ぶ).

ベータ分布は2個の shape parameter によって特徴づけられる連続分布である.
確率変数 $Y$ が shape parameter $\alpha > 0$, $\beta > 0$ のベータ分布に従う
( $Y \sim \text{Beta}(\alpha,\beta)$ ) のとき, $Y$ の確率密度関数 $f_Y(y)$ は

$$
f_Y(y) = \frac{1}{\mathrm{B}(\alpha,\beta)}y^{\alpha-1}(1-y)^{\beta-1} \quad \text{for } 0 < y < 1
$$

となる ( $\mathrm{B}()$ はベータ関数).


### Related Distributions

- If $X \sim \text{Gamma}(1,\theta)$, then $X \sim \text{Exp}(\theta)$.
- If $X \sim \text{Gamma}(k,\theta)$, then $cX \sim \text{Gamma}(k,c\theta)$.
- If $X_1 \sim \text{Gamma}(k_1,\theta)$ and $X_2 \sim \text{Gamma}(k_2,\theta)$ are independent,
  then $Y = X_1/(X_1+X_2)$ and $Z = X_1+X_2$ are independent
  and are $Y \sim \text{Beta}(k_1,k_2)$ and $Z \sim \text{Gamma}(k_1+k_2,\theta)$, respectively.
- If $Y \sim \text{Beta}(k_1,k_2)$ and $Z \sim \text{Gamma}(k_1+k_2,\theta)$ are independent,
  then $X_1 = YZ$ and $X_2 = Z(1-Y)$ are independent
  and are $X_1 \sim \text{Gamma}(k_1,\theta)$ and $X_2 \sim \text{Gamma}(k_2,\theta)$, respectively.

---

**(Proof memo)**

確率変数 $X_1$, $X_2$ は独立で, $X_1 \sim \text{Gamma}(k_1,\theta)$, $X_2 \sim \text{Gamma}(k_2,\theta)$ とする.
$(X_1,X_2)$ と $(Y,Z)$ は1対1対応するので,
$Y = X_1/(X_1+X_2)$ と $Z = X_1+X_2$ の同時確率密度を考えると,

$$
\begin{split}
f_{Y,Z}(y,z) &= f_{X_1}(x_1) f_{X_2}(x_2) \frac{\partial(x_1,x_2)}{\partial(y,z)} \\
& = \frac{1}{\Gamma(k_1)\Gamma(k_2)\theta^{k_1+k_2}}
 y^{k_1-1} (1-y)^{k_2-1} z^{k_1+k_2-1} \exp(-z/\theta)
\end{split}
$$

となる.
すなわち, $Y$ と $Z$ は独立であり, $Z \sim \text{Gamma}(k_1+k_2,\theta)$,
$Y \sim \text{Beta}(k_1,k_2)$ である.

逆も同様.

---
