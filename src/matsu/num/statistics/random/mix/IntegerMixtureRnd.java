/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.7.2
 */
package matsu.num.statistics.random.mix;

import java.util.Collection;
import java.util.function.ToIntFunction;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.CategoricalRnd;
import matsu.num.statistics.random.GeometricRnd;
import matsu.num.statistics.random.IntegerRandomGenerator;

/**
 * 整数により値が表現された,
 * 離散確率分布の混合モデルによる乱数発生器を表現する.
 * 
 * <p>
 * コンポーネント数を <i>n</i> とし, 各コンポーネントの確率質量関数を
 * P<sub><i>k</i></sub>(<i>x</i>) とする
 * (<i>k</i> = 0, 1, ..., <i>n</i> - 1). <br>
 * 混合モデルの確率質量関数 P(<i>x</i>) は, <br>
 * P(<i>x</i>) = &Sigma;<sub><i>k</i> = 0</sub><sup><i>n</i> - 1</sup>
 * <i>&phi;</i><sub><i>k</i></sub>
 * P<sub><i>k</i></sub>(<i>x</i>) <br>
 * と表される. <br>
 * ただし, <i>&phi;</i><sub><i>k</i></sub> は重みであり, <br>
 * &Sigma;<sub><i>k</i> = 0</sub><sup><i>n</i> - 1</sup>
 * <i>&phi;</i><sub><i>k</i></sub> = 1 <br>
 * である.
 * </p>
 * 
 * <p>
 * 乱数の生成は, {@link IntegerRandomGenerator} から継承された
 * {@link IntegerRandomGenerator#nextRandom(matsu.num.statistics.random.BaseRandom)
 * nextRandom(BaseRandom)}
 * メソッドによって行われる. <br>
 * {@link matsu.num.statistics.random.Rnd} のインターフェース説明のとおり,
 * 実装のインスタンスはイミュータブルであり,
 * (乱数であることを除いて) 関数的に振る舞う.
 * </p>
 * 
 * <p>
 * {@link IntegerMixtureRnd} のインスタンスの生成は,
 * 最も基本的には {@link #createFrom(CategoricalRnd, Collection)} メソッドにより行う.
 * <br>
 * 詳細は, {@link #createFrom(CategoricalRnd, Collection)}
 * メソッドの説明の通りである.
 * </p>
 * 
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface IntegerMixtureRnd extends IntegerRandomGenerator {

    /**
     * この混合モデルのコンポーネント数 (<i>n</i>) を返す.
     * 
     * @return コンポーネント数
     */
    public abstract int size();

    /**
     * モデル選択器とコンポーネント群を与えて, 混合分布による乱数発生器を生成する.
     * 
     * <p>
     * このメソッドは, 最も基本的な {@code static} ファクトリである. <br>
     * モデル選択器: {@link CategoricalRnd} は,
     * <i>&phi;</i><sub>0</sub>, <i>&phi;</i><sub>1</sub>, ...,
     * <i>&phi;</i><sub><i>n</i> - 1</sub>
     * をカテゴリ確率とするカテゴリカル乱数発生器である. <br>
     * 各コンポーネントは,
     * {@link ToIntFunction
     * ToIntFunction&lt;?&nbsp;super&nbsp;BaseRandom&gt;}
     * により表現し,
     * {@link BaseRandom} から {@code int} を生成させる. <br>
     * 例えば, 次のように与える
     * ({@link GeometricRnd} の場合).
     * </p>
     * 
     * <pre>
     *   {@literal ToIntFunction<? super BaseRandom> component = br -> geometricRnd.nextRandom(br);}
     * </pre>
     * 
     * <p>
     * コンポーネント群は,
     * コンポーネントの {@link Collection} であり,
     * {@link CategoricalRnd} による乱数値と {@link Collection} の index が対応する.
     * </p>
     * 
     * @param modelSelector モデル選択器
     * @param components コンポーネント群
     * @return 混合分布乱数発生器
     * @throws IllegalArgumentException モデル選択器とコンポーネント群のサイズが異なる場合
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static IntegerMixtureRnd createFrom(
            CategoricalRnd modelSelector,
            Collection<? extends ToIntFunction<? super BaseRandom>> components) {

        return CategoricalBasedIntegerMixtureRnd.createFrom(modelSelector, components);
    }
}
