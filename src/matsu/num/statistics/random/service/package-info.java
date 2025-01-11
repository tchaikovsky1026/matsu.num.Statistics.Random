/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/**
 * このモジュールで実装された機能を外部向けに提供するパッケージ.
 * 
 * <p>
 * このパッケージでは乱数発生器のファクトリインスタンスを取得する仕組みが用意されている. <br>
 * ファクトリインスタンスの取得は
 * ファクトリプロバイダ
 * {@link matsu.num.statistics.random.service.RandomGeneratorFactoryProvider}
 * のインスタンスを経由して行われる. <br>
 * ファクトリインスタンスの取得までの流れは次の通りである.
 * </p>
 * 
 * <ol>
 * 
 * <li>ファクトリプロバイダ
 * ({@link matsu.num.statistics.random.service.RandomGeneratorFactoryProvider})
 * インスタンスを取得する.</li>
 * 
 * <li>ファクトリを取得したい乱数発生器タイプ
 * ({@link matsu.num.statistics.random.service.RandomGeneratorType})
 * インスタンスを用意する.
 * {@link matsu.num.statistics.random.service.GeneratorTypes}
 * に定数として用意されている.</li>
 * 
 * <li>ファクトリプロバイダの
 * {@link matsu.num.statistics.random.service.RandomGeneratorFactoryProvider#get(RandomGeneratorType)}
 * メソッドをコールし, 乱数生成器のファクトリを取得する.</li>
 * 
 * </ol>
 * 
 */
package matsu.num.statistics.random.service;
