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
 * 形状パラメータ <i>k</i> のガンマ乱数発生器を取得する.)</li>
 * 
 * <li>乱数発生器に基本乱数発生器 ({@link matsu.num.statistics.random.BaseRandom BaseRandom})
 * のインスタンスを渡して, 乱数の値を得る.
 * {@code nextRandom(BaseRandom)} に準じたメソッド名を持つ. <br>
 * (基本乱数発生器インスタンスの取得は, {@link matsu.num.statistics.random.BaseRandom BaseRandom}
 * を参照)</li>
 * 
 * </ol>
 * 
 * {@code XxxRnd} の実装にはループ処理を含むものがある. <br>
 * このループを正常に終了するためには,
 * {@link matsu.num.statistics.random.BaseRandom BaseRandom} が正しく実装されている必要がある.
 * <br>
 * 正しく実装されていない場合はアルゴリズム的には無限ループが生じる可能性があるが,
 * このモジュールでは安全のために例外
 * {@link matsu.num.statistics.random.UnexpectedRandomGenerationException
 * UnexpectedRandomGenerationException}
 * をスローするようにした. <br>
 * このことは各メソッドの契約には記載されていことに注意.
 */
package matsu.num.statistics.random;
