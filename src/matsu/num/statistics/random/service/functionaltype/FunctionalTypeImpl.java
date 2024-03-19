/**
 * 2024.3.19
 */
package matsu.num.statistics.random.service.functionaltype;

import java.util.Objects;
import java.util.function.Function;

import matsu.num.statistics.random.RandomGeneratorFactory;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;

/**
 * <p>
 * {@link FunctionalType} の実装クラス.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 19.0
 * @param <T> このタイプが返却する乱数発生器ファクトリの型
 */
public final class FunctionalTypeImpl<T extends RandomGeneratorFactory>
        implements FunctionalType<T> {

    private final String typeName;
    private final Class<T> factoryClass;
    private final Function<RandomGeneratorFactoryProvider, T> getter;

    public FunctionalTypeImpl(String typeName, Class<T> factoryClass,
            Function<RandomGeneratorFactoryProvider, T> getter) {
        super();
        this.typeName = Objects.requireNonNull(typeName);
        this.factoryClass = Objects.requireNonNull(factoryClass);
        this.getter = Objects.requireNonNull(getter);
    }

    @Override
    public T cast(Object obj) {
        return this.factoryClass.cast(obj);
    }

    @Override
    public T get(RandomGeneratorFactoryProvider provider) {
        return this.getter.apply(provider);
    }

    /**
     * このタイプの文字列表現を返す.
     * 
     * @return タイプの文字列表現
     */
    @Override
    public final String toString() {
        return this.typeName;
    }
}
