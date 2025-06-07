/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.7
 */
package matsu.num.statistics.random;

/**
 * 乱数発生器であることを表明するマーカーインターフェース.
 * 
 * <p>
 * このサブタイプのインスタンスはイミュータブルであり,
 * (乱数であることを除いて) 関数的に振る舞う. <br>
 * 乱数発生時に与えられる {@link BaseRandom} が許すならば,
 * 並行プロセスにおいて競合が発生しないことを保証する.
 * </p>
 * 
 * <p>
 * <u><i>
 * 注意: <br>
 * モジュール外ではこのインターフェースを型として依存するのは強く非推奨である.
 * </i></u>
 * </p>
 * 
 * @implSpec
 *               このインターフェースをモジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface Rnd {

}
