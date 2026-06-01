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

## Random Value Generator

乱数発生方法を考える際, ガンマ分布の scape parameter は本質的でない.
したがって今後は, 標準ガンマ分布 (sGamma) について考える事とする.

- shape parameter が1の標準ガンマ分布は標準指数分布に一致するので, 今は問題としない.
- shape parameter が1より大きい標準ガンマ分布については,
  後述する Marsaglia-Tsang の方法に基づく発生方法を利用する.
- shape parameter が1より小さい標準ガンマ分布は,
  $y \sim \text{Beta}(k,1)$, $z \sim \text{sGamma}(k+1)$ を独立に発生させ,
  $x = yz$ とすれば, $x \sim \text{sGamma}(k)$ となる.
    - $z$ は shape parameter が 1より大きいので, 前項に適合する.
    - $y \sim \text{Beta}(k,1)$ の発生は逆関数法を用いる.
      これは, $f_Y(y) = k y^{k-1}$ よりべき分布である.
        - 0から1の範囲の一様分布 (標準一様分布) に従う乱数 $u$ を発生させ, $y = u^{1/k}$ とすればよい.  
          もしくは, $e = -\log u$ の変数変換を考え, 標準指数分布に従う乱数 $e$ を発生させ, $y = \exp(-\frac{1}{k}e)$ としてもよい.
          通常, べき乗の計算は $\exp$ と $\log$ を用いて実装されるので, 指数乱数を使う仕組みの方が高速になる.
- ベータ分布に従う乱数は, 独立な標準ガンマ分布に従う乱数2個を用いて得られる.  
  $x_1 \sim \text{sGamma}(k_1)$, $x_2 \sim \text{sGamma}(k_2)$ を発生させ,
  $y = \frac{x_1}{x_1+x_2}$ とすれば, $y \sim \text{Beta}(k_1,k_2)$ となる.


**(Marsaglia-Tsang の方法)**

shape parameter が1より大きい標準ガンマ分布の生成に関して,
Marsaglia-Tsang の方法を説明する.
引用文献は次である.  
G. Marsaglia and W.W. Tsang, "A simple method for generating gamma variables." ACM Transactions on Mathematical Software (TOMS) 26 (2000) 363-372.

主な方針は, $X$ がガンマ分布に従うとき, $\sqrt[3]{X}$ が正規分布に近くなるという性質を使い,
Rejection Algorithm に結び付けることである.
$X \sim \text{sGamma}(k)$ であるとき, $X = d(1+cZ')^3$ と変数変換した $Z'$ が標準正規分布に近くなるような
$c$, $d$ を計算すれば, $d=k-\frac{1}{3}$, $c=\frac{1}{\sqrt{9d}}$ となる.
逆にこの値を使って, $\text{sGamma}(k)$ の提案分布として, $Z$ が標準正規分布に従う確率変数としたときの, $X' = d(1+cZ)^3$ を考えると,

$$
f_{X'}(x) = \frac{3c w^{-2/3}}{\sqrt{2\pi}} \exp(-z^2/2)
$$

となる ( $w = (1+cz)^3$ とした).
$X'$ についてはこの時点で負の数も許容する (後の Rejection 判定により 100 %棄却される).
$f_X(x) / f_{X'}(x)$ は $z = 0$ で最大値をとるので, 
提案分布に乗じる定数 $K$ を, 

$$
K = \frac{\sqrt{2\pi} d^{k-\frac{1}{2}}\exp(-d)}{\Gamma{(k)}}
$$

とすれば,

$$
\log \frac{f_X(x)}{Kf_{X'}(x)} = d \log w - x + \frac{z^2}{2} + d \quad \text{for } x > 0
$$

を得る.
これと, 標準一様分布に従う乱数 $u$ とに対する $\log u$ とを比較するという Rejection Algorithm になる.
