/**
 * <p>
 * 確率分布に従う乱数を扱うモジュール.
 * </p>
 * 
 * <p>
 * 各乱数発生器の生成機能 (ファクトリ) はインターフェースとして公開されている. <br>
 * {@link matsu.num.statistics.random.service.RandomGeneratorFactoryProvider}
 * のメソッドを経由して各ファクトリのインスタンスを取得するのが,
 * 標準的な利用方法である.
 * </p>
 * 
 * <p>
 * <i>必須モジュール:</i>
 * </p>
 * 
 * @author Matsuura Y.
 * @version 22.0
 */
module matsu.num.statistics.Random {

    exports matsu.num.statistics.random;
    exports matsu.num.statistics.random.lib;
    exports matsu.num.statistics.random.service;
}