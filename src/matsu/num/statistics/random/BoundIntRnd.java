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

import matsu.num.statistics.random.accomp.IntegerRandomGenerator;

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
public interface BoundIntRnd extends IntegerRandomGenerator {

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
     * {@inheritDoc }
     * 
     * <p>
     * 生成される値は 0 以上 {@link #limit() limit} 未満であることが保証されている.
     * </p>
     *
     * @implSpec
     *               引数が {@code null} でない場合,
     *               必ず 0 以上 {@link #limit() limit} 未満の {@code int} を返さなければならない.
     *
     * @return {@inheritDoc }, 0以上 {@link #limit() limit} 未満
     * @throws NullPointerException {@inheritDoc }
     */
    @Override
    public abstract int nextRandom(BaseRandom random);
}
