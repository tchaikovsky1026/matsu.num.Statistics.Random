/**
 * 2024.3.16
 */
package matsu.num.statistics.random.cat;

import java.util.Objects;
import java.util.function.Function;

import matsu.num.statistics.random.CategoricalRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * テーブル法に基づく, カテゴリカル分布に従う乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 19.0
 */
public final class TableBasedCategoricalRndFactory implements CategoricalRnd.Factory {

    private static final Function<double[], IllegalArgumentException> exceptionGetter =
            values -> new IllegalArgumentException(String.format("パラメータ不正: size=%s", values.length));

    private final Exponentiation exponentiation;

    public TableBasedCategoricalRndFactory(Exponentiation exponentiation) {
        this.exponentiation = Objects.requireNonNull(exponentiation);
    }

    @Override
    public boolean acceptsSizeOf(double[] probabilityValues) {
        return probabilityValues.length >= 1;
    }

    @Override
    public CategoricalRnd instanceOf(double[] probability) {
        if (!this.acceptsSizeOf(probability)) {
            throw exceptionGetter.apply(probability);
        }
        return TableBasedCategoricalRnd.instanceOf(probability);
    }

    @Override
    public CategoricalRnd instanceOfExp(double[] logProbability) {
        if (!this.acceptsSizeOf(logProbability)) {
            throw exceptionGetter.apply(logProbability);
        }
        return TableBasedCategoricalRnd.instanceOfExp(logProbability, this.exponentiation);
    }

    @Override
    public String toString() {
        return "CategoricalRnd.Factory";
    }
}
