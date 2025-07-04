/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.7.4
 */
package matsu.num.statistics.random;

/**
 * あらかじめ定められた上限 <i>n</i> で,
 * 0 以上 <i>n</i> - 1 以下の値が生成されることが保証された整数乱数発生器.
 * 
 * <p>
 * このインターフェースは, このモジュールの機能の引数で型として扱うために用意されている.
 * </p>
 * 
 * @implSpec
 *               このインターフェースはモジュール外で実装できるように設計されている. <br>
 *               {@link Rnd} インターフェースの説明と, 各メソッドの要件に注意して実装すること.
 * 
 *               <p>
 *               0以上 {@link Integer#MAX_VALUE} 以下／未満の整数を返すような乱数発生器は, <br>
 *               上限が契約として明確に現れないので,
 *               このインターフェースのサブタイプであるべきではない.
 *               </p>
 * 
 * @author Matsuura Y.
 */
public interface BoundIntRnd extends Rnd {

    /**
     * 上限 (exclusive) の値を返す.
     * 
     * <p>
     * {@link #nextRandom(BaseRandom) nextRandom(BaseRandom)} メソッドで生成される値が
     * 0 以上 {@link #limit() limit} 未満であることを保証する.
     * </p>
     * 
     * @return 上限 (exclusive)
     */
    public abstract int limit();

    /**
     * 与えられた基本乱数発生器を用いて, 所定の確率分布の乱数を生成する. <br>
     * 生成される値は 0 以上 {@link #limit() limit} 未満であることが保証されている.
     *
     * @implSpec
     *               引数が {@code null} でない場合,
     *               必ず 0 以上 {@link #limit() limit} 未満の {@code int} を返さなければならない.
     *
     * @param random 基本乱数発生器
     * @return 所定の確率分布に従う乱数の値, 0以上 {@link #limit() limit} 未満
     * @throws NullPointerException 引数がnullの場合
     */
    public abstract int nextRandom(BaseRandom random);
}
