/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
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
 * Sealedインターフェースとしてタイプが表現されている.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 20.0
 * @param <T> このタイプが返却する乱数発生器ファクトリの型
 */
@SuppressWarnings("rawtypes")
public sealed interface RandomGeneratorType<T extends RandomGeneratorFactory> permits FunctionalType {

}
