/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.6
 */
package matsu.num.statistics.random;

/**
 * <p>
 * 標準 Cauchy 分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 標準 Cauchy 分布の確率密度関数 P(<i>x</i>) は次のとおりである. <br>
 * (ただし, 境界値が発生する可能性がある.)
 * </p>
 * 
 * <ul>
 * <li>
 * P(<i>x</i>) &prop;
 * 1 / (1 + <i>x</i><sup><i>2</i></sup>)
 * &emsp; (-&infin; &lt; <i>x</i> &lt; +&infin;)
 * </li>
 * </ul>
 * 
 * <p>
 * 標準 Cauchy 分布は自由度1の Student-t 分布に一致する.
 * </p>
 * 
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface CauchyRnd extends FloatingRandomGenerator {

    /**
     * <p>
     * この標準 Cauchy 分布と同等な, Student-t 分布としてのビューを返す.
     * </p>
     * 
     * <p>
     * 戻り値の Student-t 分布の自由度は 1 である. <br>
     * ビューの変更であるため,
     * {@link CauchyRnd} としての
     * {@link #nextRandom(BaseRandom)}
     * と
     * {@link TDistributionRnd} としての
     * {@link TDistributionRnd#nextRandom(BaseRandom)}
     * とは同一の処理を行う.
     * </p>
     * 
     * @return 標準 Cauchy 分布と同等な, 自由度 1 の Student-t 分布
     * @deprecated
     *                 このメソッドは version 26 以降削除予定である. <br>
     *                 モジュール外では, ビューの変更 (型変換) を行うのは不適切である. <br>
     *                 (「クライアントが依存するインターフェースはクライアントが定めなければならない」)
     */
    @Deprecated(forRemoval = true)
    public default TDistributionRnd asTDistributionRnd() {
        return new CauchyRndTDistView(this);
    }

    /**
     * {@link CauchyRnd} のファクトリ.
     * 
     * @implSpec
     *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
     *               モジュール外で実装してはいけない.
     */
    public static interface Factory extends ParameterlessRndFactory<CauchyRnd> {

    }
}
