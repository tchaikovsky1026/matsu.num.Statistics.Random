/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.30
 */
package matsu.num.statistics.random.mix;

import java.util.Collection;
import java.util.function.ToDoubleFunction;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.CategoricalRnd;
import matsu.num.statistics.random.FloatingRandomGenerator;

/**
 * <p>
 * 浮動小数点数により値が表現された,
 * 連続確率分布の混合モデルによる乱数発生器を表現する.
 * </p>
 * 
 * <p>
 * 乱数の生成は, {@link FloatingRandomGenerator} から継承された
 * {@link FloatingRandomGenerator#nextRandom(matsu.num.statistics.random.BaseRandom)
 * nextRandom(BaseRandom)}
 * メソッドによって行われる. <br>
 * {@link matsu.num.statistics.random.Rnd} のインターフェース説明のとおり,
 * 実装のインスタンスはイミュータブルであり,
 * (乱数であることを除いて) 関数的に振る舞う.
 * </p>
 * 
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface FloatingMixtureRnd extends FloatingRandomGenerator {

    /**
     * この混合モデルのコンポーネント数を返す.
     * 
     * @return コンポーネント数
     */
    public abstract int size();

    /**
     * モデル選択器とコンポーネント群を与えて, 混合分布による乱数発生器を生成する.
     * 
     * @param modelSelector モデル選択器
     * @param components コンポーネント群
     * @return 混合分布乱数発生器
     * @throws IllegalArgumentException モデル洗濯機とコンポーネント群のサイズが異なる場合
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static FloatingMixtureRnd createFrom(
            CategoricalRnd modelSelector,
            Collection<? extends ToDoubleFunction<? super BaseRandom>> components) {

        return CategoricalBasedFloatingMixtureRnd.createFrom(modelSelector, components);
    }
}
