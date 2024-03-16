/**
 * <p>
 * 確率分布に従う乱数を扱うモジュール.
 * </p>
 * 
 * <p>
 * {@link matsu.num.statistics.random.service.RandomGeneratorFactoryProvider}
 * を経由して各ファクトリを生成することが, 
 * このモジュールが提供する乱数発生器の標準的な利用法である.
 * </p>
 * 
 * <p>
 * <i>必須モジュール:</i>
 * </p>
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
module matsu.num.statistics.Random {

    exports matsu.num.statistics.random;
    exports matsu.num.statistics.random.service;
}