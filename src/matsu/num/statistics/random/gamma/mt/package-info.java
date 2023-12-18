/**
 * Marsaglia-Tsangアルゴリズムに基づくガンマ分布乱数発生器を扱う. <br>
 * <br>
 * <i>実装, 分岐について:</i> <br>
 * 形状パラメータをkとして, {@literal k < 1, k = 1, k > 1}で実装を分岐する. <br>
 * {@literal k = 1}は標準指数分布(パラメータ無し)に一致するので, キャッシュしたインスタンスを返す. <br>
 * {@literal k > 1}はMarsaglia-Tsangの方法を用いて生成する. <br> {@literal k < 1}の場合,
 * X～Gamma(k+1),Y～Beta(k+1,1)のとき(XY)～Gamma(k)となることを利用する.
 */
package matsu.num.statistics.random.gamma.mt;