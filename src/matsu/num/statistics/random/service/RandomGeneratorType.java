/**
 * 2024.3.19
 */
package matsu.num.statistics.random.service;

import matsu.num.statistics.random.RandomGeneratorFactory;
import matsu.num.statistics.random.service.functionaltype.FunctionalType;

/**
 * <p>
 * このモジュールが提供する乱数生成器のタイプ.
 * </p>
 * 
 * <p>
 * Sealedインターフェースとしてタイプが表現されている. <br>
 * ユーザーは {@link GeneratorTypes} に用意された定数を使用する.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 19.0
 * @param <T> このタイプが返却する乱数発生器ファクトリの型
 */
@SuppressWarnings("rawtypes")
public sealed interface RandomGeneratorType<T extends RandomGeneratorFactory> permits FunctionalType {

}
