/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/**
 * 乱数発生器に関する型を定義するパッケージ.
 * 
 * <p>
 * このパッケージでは乱数発生器インターフェースとそのファクトリインターフェースが定義されている. <br>
 * 乱数発生器は {@code XxxRnd},
 * そのファクトリは {@code XxxRnd.Factory} といったインターフェース名である. <br>
 * 乱数を生成するまでの流れは次の通りである.
 * </p>
 * 
 * <ol>
 * 
 * <li>ファクトリのインスタンスを取得する. <br>
 * ({@link matsu.num.statistics.random.service} パッケージを参照)</li>
 * 
 * <li>ファクトリに必要なパラメータを渡して, 乱数発生器を取得する. <br>
 * (例えば, ガンマ乱数発生器ファクトリに形状パラメータ <i>k</i> を渡して,
 * 形状パラメータ <i>k</i> のガンマ乱数発生器を取得する, のような)</li>
 * 
 * <li>乱数発生器に基本乱数発生器 ({@link matsu.num.statistics.random.BaseRandom})
 * のインスタンスを渡して, 乱数の値を得る.
 * {@code nextRandom(BaseRandom)} に準じたメソッド名を持つ. <br>
 * (基本乱数発生器インスタンスの取得は, {@link matsu.num.statistics.random.BaseRandom}
 * を参照)</li>
 * 
 * </ol>
 * 
 */
package matsu.num.statistics.random;
