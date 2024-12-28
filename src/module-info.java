/**
 * 確率分布に従う乱数を扱うモジュール.
 * 
 * <p>
 * このモジュールの主要なパッケージは, {@link matsu.num.statistics.random} パッケージと
 * {@link matsu.num.statistics.random.service} パッケージである.
 * </p>
 * 
 * <p>
 * {@link matsu.num.statistics.random} パッケージには,
 * 乱数発生器インターフェースとそのファクトリインターフェースが定義されている. <br>
 * 乱数発生器は {@code XxxRnd},
 * そのファクトリは {@code XxxRnd.Factory} といったインターフェース名である.
 * </p>
 * 
 * <p>
 * {@link matsu.num.statistics.random.service} パッケージには,
 * 前述の乱数発生器のファクトリインスタンスを取得するための仕組みが用意されている. <br>
 * ユーザーは
 * {@link matsu.num.statistics.random.service.RandomGeneratorFactoryProvider}
 * のメソッドを経由して各ファクトリのインスタンスを取得する.
 * </p>
 * 
 * <p>
 * 詳しくは, 各パッケージの説明文を参照すること.
 * </p>
 * 
 * <p>
 * <i>依存モジュール:</i> <br>
 * (無し)
 * </p>
 * 
 * @author Matsuura Y.
 * @version 23.1
 */
module matsu.num.statistics.Random {

    exports matsu.num.statistics.random;
    exports matsu.num.statistics.random.lib;
    exports matsu.num.statistics.random.service;
}
