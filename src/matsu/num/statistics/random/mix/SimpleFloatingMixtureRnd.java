/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.7.4
 */
package matsu.num.statistics.random.mix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.ToDoubleFunction;

import matsu.num.statistics.random.BaseRandom;
import matsu.num.statistics.random.BoundIntRnd;

/**
 * {@link FloatingMixtureRnd} のシンプルな実装,
 * カテゴリカル分布を用いてコンポーネントの選択を行う.
 * 
 * @author Matsuura Y.
 */
final class SimpleFloatingMixtureRnd implements FloatingMixtureRnd {

    /**
     * モデルセレクタ.
     */
    private final BoundIntRnd modelSelector;

    /**
     * コンポーネント. <br>
     * ジェネリック配列なので, 参照が漏洩しないように注意しなければならない.
     */
    private final ToDoubleFunction<? super BaseRandom>[] components;

    /**
     * 唯一の非公開コンストラクタ.
     * 
     * <p>
     * カテゴリカル乱数発生器と, コンポーネントを表す配列を受け取る. <br>
     * コンポーネントは {@link BaseRandom} から {@code double} への写像
     * ({@link ToDoubleFunction})
     * として表現される
     * (ただし, 乱数である部分は参照透過ではない). <br>
     * これは例えば, <br>
     * {@code (BaseRandom br) -> normalRnd.nextRandom(br)} <br>
     * を与えることになる.
     * </p>
     * 
     * <p>
     * このコンストラクタは引数のチェックを行っていないので, 以下に注意して呼ばなければならない. <br>
     * カテゴリカルサイズと配列lengthは一致しかねればならない. <br>
     * {@code components} の要素は {@code null} ではいけない.
     * </p>
     */
    private SimpleFloatingMixtureRnd(
            BoundIntRnd modelSelector,
            ToDoubleFunction<? super BaseRandom>[] components) {

        this.modelSelector = modelSelector;
        this.components = components;
    }

    @Override
    public double nextRandom(BaseRandom random) {
        return this.components[this.modelSelector.nextRandom(random)].applyAsDouble(random);
    }

    @Override
    public int size() {
        return this.modelSelector.limit();
    }

    @Override
    public String toString() {
        return String.format(
                "FloatingMixtureRnd(size = %s)", this.size());
    }

    /**
     * モデル選択器とコンポーネント群を与えて, 混合分布による乱数発生器を生成する.
     * 
     * @param modelSelector モデル選択器
     * @param components コンポーネント群
     * @return 混合分布乱数発生器
     * @throws IllegalArgumentException モデル選択器とコンポーネント群のサイズが異なる場合
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    static FloatingMixtureRnd createFrom(
            BoundIntRnd modelSelector,
            Collection<? extends ToDoubleFunction<? super BaseRandom>> components) {

        List<ToDoubleFunction<? super BaseRandom>> componentList = new ArrayList<>(components);

        if (modelSelector.limit() != componentList.size()) {
            throw new IllegalArgumentException("size mismatch");
        }
        if (componentList.stream().anyMatch(Objects::isNull)) {
            throw new NullPointerException("includes null");
        }

        /*
         * ジェネリック型の配列を作成できないので, raw型のToDoubleFunction[]で代用する.
         * この配列の要素にToDoubleFunction<? super BaseRandom>以外が格納されないことを保証する.
         */
        @SuppressWarnings({ "cast", "unchecked" })
        ToDoubleFunction<? super BaseRandom>[] componentArr =
                (ToDoubleFunction<? super BaseRandom>[]) componentList.stream()
                        .toArray(ToDoubleFunction[]::new);

        return new SimpleFloatingMixtureRnd(modelSelector, componentArr);
    }
}
