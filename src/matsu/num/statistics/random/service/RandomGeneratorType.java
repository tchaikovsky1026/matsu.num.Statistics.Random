/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.24
 */
package matsu.num.statistics.random.service;

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
 * @version 21.0
 * @param <T> このタイプが返却する乱数発生器ファクトリの型
 */
@SuppressWarnings("rawtypes")
public sealed interface RandomGeneratorType<T> permits FunctionalType {

}
