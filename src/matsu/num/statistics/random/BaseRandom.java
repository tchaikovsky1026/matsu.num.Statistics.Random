/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.9
 */
package matsu.num.statistics.random;

import java.util.function.Supplier;

/**
 * <p>
 * このモジュールが提供する機能で使用する基本乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * 基本的なインスタンスは, このインターフェース内に定義された
 * {@code static} ファクトリメソッドにより得られる. <br>
 * また, 必要ならば独自に {@code implement} して使用しても良い.
 * </p>
 * 
 * @author Matsuura Y.
 */
public interface BaseRandom {

    /**
     * <p>
     * {@code true} または {@code false} を等確率で返す.
     * </p>
     * 
     * @return {@code true}, {@code false} が等確率
     */
    public abstract boolean nextBoolean();

    /**
     * <p>
     * {@code int} が取り得る2<sup>32</sup>種類の値のいずれかを等確率で返す.
     * </p>
     * 
     * @return {@code int} が取り得る値全体のうちの1個
     */
    public abstract int nextInt();

    /**
     * <p>
     * 0 &le; x &lt; bound を満たす一様整数乱数を発生する.
     * </p>
     * 
     * <p>
     * boundは正でなければならない.
     * </p>
     * 
     * @param bound 乱数の上限(自身は含まない)
     * @return 0以上bound未満の値
     * @throws IllegalArgumentException boundが正でない場合
     */
    public abstract int nextInt(int bound);

    /**
     * <p>
     * 0 &le; x &lt; 1 を満たす一様乱数を発生する.
     * </p>
     * 
     * @return 0以上1未満の値
     */
    public abstract double nextDouble();

    /**
     * <p>
     * {@code java.util.random.RandomGenerator} をラップした
     * {@link BaseRandom} インスタンスを返す.
     * </p>
     * 
     * @param random ラップされるインスタンス
     * @return ラップされた {@link BaseRandom}
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static BaseRandom of(java.util.random.RandomGenerator random) {
        return new BaseRandomHelper.RandomImpl(random);
    }

    /**
     * <p>
     * 与えたサプライヤにより {@link java.util.random.RandomGenerator}
     * を呼び出して乱数を生成するように振る舞う
     * {@link BaseRandom} インスタンスを返す.
     * </p>
     * 
     * <p>
     * このメソッドにより返される {@link BaseRandom} は,
     * 毎回の乱数生成のたびに {@link Supplier#get()} を行う. <br>
     * すなわち, 独自の {@link java.util.random.RandomGenerator} インスタンス管理機構を持つ仕組みに適合する.
     * <br>
     * 例えば, <br>
     * {@code getter = () -> java.util.concurrent.ThreadLocalRandom.current();}
     * <br>
     * などである.
     * </p>
     * 
     * <p>
     * {@code getter} の {@link Supplier#get()} メソッドでは
     * {@link java.util.random.RandomGenerator} インスタンスを生成してはならない. <br>
     * そのような状況は, このメソッドではなく {@link #of(java.util.random.RandomGenerator)}
     * が適切である.
     * </p>
     * 
     * @param getter {@code java.util.random.RandomGenerator} を呼び出すためのサプライヤ
     * @return 乱数生成のたびにサプライヤから呼び出すように振る舞う {@link BaseRandom}
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static BaseRandom fromGetter(Supplier<? extends java.util.random.RandomGenerator> getter) {
        //ここでNullPointerExを発生させる可能性がある
        return new BaseRandomHelper.RandomFromGetter(getter);
    }

    /**
     * <p>
     * スレッド間で競合が発生しないような {@link BaseRandom} インスタンスを返す.
     * </p>
     * 
     * <p>
     * このインスタンスはスレッド間の競合が発生しないように実装されている. <br>
     * よって, このインスタンス自体は複数スレッドで共有されたとしても, 適切に動作する.
     * </p>
     * 
     * <p>
     * このメソッドは, {@link #fromGetter(Supplier)} の派生である.
     * </p>
     * 
     * @return スレッド間の競合が発生しない {@link BaseRandom}
     */
    public static BaseRandom threadSeparatedRandom() {
        return BaseRandomHelper.THREAD_SEPARATED_RANDOM;
    }
}
