# Classical Statistical Distribution, Random

このノートは, 古典的な統計学で現れる分布である Student の t 分布, カイ二乗分布, F 分布についてである.

## Overview of Classical Statistical Distribution
### Normal Distribution and Related Topics

#### 正規分布

Student の t 分布, カイ二乗分布, F 分布は, 正規分布モデルを用いた統計的推定で現れる.
平均 $\mu$, 分散 $\sigma^2$ の正規分布に従う独立な確率変数 $X_1,...,X_n$ を考える
( $X_k \sim \text{N}(\mu,\sigma^2)$ ).
これらの同時確率密度関数 $f_{X_1,...,X_n}(x_1,...,x_n)$ は

$$
f_{X_1,...,X_n}(x_1,...,x_n) = \frac{1}{(2\pi \sigma^2)^{\frac{n}{2}}}
  \prod_{k=1}^{n} {\textstyle \exp(-\frac{(x_k-\mu)^2}{2\sigma^2})}
$$

である.

#### 標本平均, 標本分散

$X_1,X_2,...$ の標本平均 $\bar{X}$ と偏差平方和 $S_X$ は

$$
\bar{X} = \frac{1}{n} \sum_{k=1}^{n} X_k, \quad
S_X = \sum_{k=1}^{n} (X_k-\bar{X})^2
$$

と書ける. この $\bar{X}$ と $S_X$ の性質を議論する.

議論を見やすくするため,
$Z_k = \frac{X_k - \mu}{\sqrt{\sigma^2}}$ と変換し,
標本平均と偏差平方和を $\bar{Z}$, $S_Z$ と表す:

$$
\bar{Z} = \frac{1}{n} \sum_{k=1}^{n} Z_k, \quad
S_Z = \sum_{k=1}^{n} (Z_k-\bar{Z})^2.
$$

$Z_1,Z_2,...$ は独立, $Z_k \sim \text{N}(0,1)$ であり,
$\bar{X} =\mu +  \sqrt{\sigma^2} \bar{Z}$, $S_X = \sigma^2 S_Z$ である.
$n$ 次単位行列 $\mathsf{I}$ と, 大きさ 1 の $n$ 次元縦ベクトル $\mathbf{q}_1$ :

$$
\mathbf{q}_1 = \frac{1}{\sqrt{n}} \begin{pmatrix}
1 \\
\vdots \\
1
\end{pmatrix}
$$

を考える.
また, 確率変数 $\mathbf{Z}$ を

$$
\mathbf{Z} = \begin{pmatrix}
Z_1 \\
Z_2 \\
\vdots \\
Z_n
\end{pmatrix}
$$

と定める.
$\mathbf{Z}$ は標準多変量正規分布に従う ( $\mathbf{Z} \sim \text{N}(\mathbf{0}, \mathsf{I})$ ) .
これを用いると, $\bar{Z} = \frac{1}{\sqrt{n}} \mathbf{q}_1^T \mathbf{Z}$,
$S_Z = \mathbf{Z}^T (\mathsf{I} - \mathbf{q}_1 \mathbf{q}_1^T) \mathbf{Z}$
と書ける.

ここで, $\mathbf{q}_1$ を基底ベクトルに含むような $n$ 次元空間の正規直交基底を構成し,
その基底ベクトルを $\mathbf{q}_1,\mathbf{q}_2,...,\mathbf{q}_n$ とし,
直交行列 $\mathsf{Q}$ を

$$
\mathsf{Q} = \begin{pmatrix}
\mathbf{q}_1 & \cdots & \mathbf{q}_n
\end{pmatrix}
$$

と定める.
これにより,
$S_Z = \mathbf{Z}^T \mathsf{Q} \mathsf{D} \mathsf{Q}^T \mathbf{Z}$
を得る.
$\mathsf{D}$ は対角行列であり,

$$
\mathsf{D} = \begin{pmatrix}
0 \\
& 1 \\
& & \ddots \\
& & & 1
\end{pmatrix}
$$

である.

さらに $\mathbf{Z}$ から $\mathbf{Y} = \mathsf{Q}^T \mathbf{Z}$ と変数変換する
( $\mathbf{Y}$ の成分を $Y_1,...,Y_n$ とする).
$\mathbf{Y}^T\mathbf{Y} = \mathbf{Z}^T\mathbf{Z}$ であるので,
標準多変量積分布の確率密度関数を参照することにより, $\mathbf{Y} \sim \text{N}(\mathbf{0}, \mathsf{I})$ がわかり,
$Y_1,...,Y_n$ は独立で $Y_k \sim \text{N}(0,1)$ である.
$\bar{Z} = \frac{1}{\sqrt{n}} Y_1$, $S_Z = \sum_{k=2}^{n} Y_k^2$ と表されるため,
$\bar{Z}$ と $S_Z$ は独立である.

ここから, 次のようにまとめられる.

- $\bar{Z}$ と $S_Z$ は独立, ゆえに $\bar{X}$ と $S_X$ は独立.
- $\bar{Z} \sim \text{N}(0,\frac{1}{n})$ であり, $\bar{X} \sim \text{N}(\mu,\frac{\sigma^2}{n})$.
- $Y_k^2 \sim \text{Gamma}(\frac{1}{2},2)$ である. ガンマ分布の再生性より $S_Z \sim \text{Gamma}(\frac{n-1}{2},2)$,
  ゆえに $S_X \sim \text{Gamma}(\frac{n-1}{2},2\sigma^2)$.
    - $\text{Gamma}(a,\theta)$ は shape parameter $a$, scale parameter $\theta$ のガンマ分布を表す.
      ガンマ分布については `GammaAndBetaRnd.md` を参照.
- $X_1,X_2,...$ の標本不偏分散 $U_X$ は $U_X = \frac{1}{n-1}S_X$ である.
  $U_X \sim \text{Gamma}(\frac{n-1}{2},\frac{2\sigma^2}{n-1})$.


### Chi-squared Distribution

カイ二乗分布は, 自由度 $k$ により特徴づけられる連続確率分布である.
確率変数 $W$ が自由度 $k$ のカイ二乗分布に従う ( $W \sim \chi^2(k)$ ) とき,
確率密度関数 $f_W(w)$ は

$$
f_W(w) = \frac{1}{2^{\frac{k}{2}}\Gamma (\frac{k}{2})} {\textstyle w^{\frac{k}{2}-1}\exp(-\frac{w}{2})}
\quad \text{for } w > 0
$$

と表される.
ただし, $\Gamma()$ はガンマ関数である.
通常, 自由度 $k$ は1以上の整数であるが,
確率分布としては $k > 0$ の実数としてよい.
$\chi^2(k)$ は $\text{Gamma}(\frac{k}{2},2)$ に一致する.

$X_1,X_2,...,X_n$ が独立で, 平均 $\mu$, 分散 $\sigma^2$ の正規分布に従うとき,
その標本不偏分散を $U_X$ とすれば,

$$
\frac{n-1}{\sigma^2}U_X \sim \chi^2(n-1)
$$

となる.
この性質をもとに, 母分散の区間推定や検定がつくられる.


### F-Distribution

F 分布は, 2個の自由度 $d_1$, $d_2$ により特徴づけられる連続確率分布である.
確率変数 $V$ が自由度 $d_1$, $d_2$ の F 分布に従う ( $V \sim \text{F}(d_1,d_2)$ ) とき,
確率密度関数 $f_V(v)$ は

$$
f_V(v) = \frac{(\frac{d_1}{d_2})^{\frac{d_1}{2}}}{\text{B}(\frac{d_1}{2},\frac{d_2}{2})}
{\textstyle v^{\frac{d_1}{2}-1} (1 + \frac{d_1}{d_2}v)^{-\frac{d_1+d_2}{2}}}
\quad \text{for } v > 0
$$

と表される.
ただし, $\text{B}()$ はベータ関数である.
通常, 自由度 $d_1$, $d_2$ は1以上の整数であるが,
確率分布としては $d_1 > 0$, $d_2 > 0$ の実数としてよい.
$V \sim \text{F}(d_1,d_2)$ のとき, $\frac{d_1}{d_2}V \sim \text{BetaPrime}(\frac{d_1}{2},\frac{d_2}{2})$ である
($\text{BetaPrime}(a,b)$ は shape parameter $a$, $b$ のベータプライム分布を表す).

確率変数 $W_1$, $W_2$ が独立で, $W_1 \sim \chi^2(d_1)$, $W_2 \sim \chi^2(d_2)$ であるときの,
$V = \frac{W_1 / d_1}{W_2 / d_2}$ を考える.
$W_1 \sim \text{Gamma}(\frac{d_1}{2},2)$, $W_2 \sim \text{Gamma}(\frac{d_2}{2},2)$
であるので, $\frac{W_1}{W_2} \sim \text{BetaPrime}(\frac{d_1}{2},\frac{d_2}{2})$ である.
また, $\frac{d_1}{d_2}V = \frac{W_1}{W_2}$ より,
$V \sim \text{F}(d_1,d_2)$ が言える.

正規分布モデルの枠組みの中では
$\frac{W_1}{d_1}$ や $\frac{W_2}{d_2}$ は標本不偏分散を母分散で除した確率変数であるので,
F 分布をもとに, 分散の一致性の議論が展開される.


### Student's t-Distribution

Student の t 分布 (以降, t 分布) は, 自由度 $\nu$ により特徴づけられる連続確率分布である.
確率変数 $T$ が自由度 $\nu$ の t 分布に従う ( $T \sim \text{t}(\nu)$ ) とき,
確率密度関数 $f_T(t)$ は

$$
f_T(t) = \frac{1}{\sqrt{\nu}\text{B}(\frac{1}{2},\frac{\nu}{2})}
{\textstyle (1 + \frac{t^2}{\nu})^{-\frac{\nu + 1}{2}}}
\quad \text{for } -\infty < t < \infty
$$

と表される.
通常, 自由度 $\nu$ は1以上の整数であるが,
確率分布としては $\nu > 0$ の実数としてよい.

今, 確率変数 $Z \sim \text{N}(0,1)$ と $W \sim \chi^2(\nu)$ が独立であるとしたときの,
$T = \frac{Z}{\sqrt{W/\nu}}$ が満たす確率分布を考える.
$(Z,W)$ の同時確率密度関数を $f_{Z,W}(z,w)$ とすれば,
$(Z,W)$ と $(T,W)$ は1対1対応であるので,
$(T,W)$ の同時確率密度関数 $f_{T,W}(t,w)$ は
$f_{T,W}(t,w) = \sqrt{\frac{w}{\nu}} f_{Z,W}(t\sqrt{\frac{w}{\nu}},w)$
となり, $w$ について周辺化することで $T$ についての確率密度関数が得られ,
$T \sim \text{t}(\nu)$ であることがわかる.

$X_1,X_2,...,X_n$ が独立で, 平均 $\mu$, 分散 $\sigma^2$ の正規分布に従うとき,
その標本不偏分散を $U_X$ として $\sigma^2$ の推定値とし,
標本平均 $\bar{X} \sim \text{N}(\mu,\frac{\sigma^2}{n})$ をもとに,
$(\bar{X} - \mu) / \sqrt{U_X/n}$ という確率変数を考えれば,

$$
\frac{\bar{X} - \mu}{\sqrt{\frac{U_X}{n}}} \sim \text{t}(n-1)
$$

を得る.
この性質をもとに, 母平均の区間推定や検定がつくられる.


## Random Value Generator

Student の t 分布, カイ二乗分布, F 分布に従う乱数生成法について, 簡単に述べる.

- カイ二乗分布に従う乱数は, $\chi^2(k)$ と $\text{Gamma}(\frac{k}{2},2)$ が同一という性質を用いる.
    - **[ $\chi^2(k)$ に従う乱数]**  
      $\text{sGamma}(\frac{k}{2})$ (shape parameter が $\frac{k}{2}$ の標準ガンマ分布) に従う乱数を生成し, $u$ とする.
      Return $2u$.
- F 分布に従う乱数は, $V \sim \text{F}(d_1,d_2)$ のとき,
  $\frac{d_1}{d_2}V \sim \text{BetaPrime}(\frac{d_1}{2},\frac{d_2}{2})$ であるという性質を用いる.
    - **[ $\text{F}(d_1,d_2)$ に従う乱数]**  
      $\text{sGamma}(\frac{d_1}{2})$, $\text{sGamma}(\frac{d_2}{2})$ に従う乱数を生成し, $u_1$, $u_2$ とする.
      Return $\frac{d_2 u_1}{d_1 u_2}$.
- Student の t 分布に従う乱数は, 独立な $Z \sim \text{N}(0,1)$ と $W \sim \chi^2(\nu)$ に対して,
  $\frac{Z}{\sqrt{W/\nu}} \sim \text{t}(\nu)$ という性質を用いる.
    - **[ $\text{t}(\nu)$ に従う乱数]**  
      $\text{N}(0,1)$ に従う乱数 $z$ と, $\text{sGamma}(\frac{\nu}{2})$ に従う乱数 $u$ を生成する.
      Return $\frac{z}{\sqrt{2u/\nu}}$.
