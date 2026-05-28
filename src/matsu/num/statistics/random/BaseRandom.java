/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2026.5.28
 */
package matsu.num.statistics.random;

import java.util.function.Supplier;

/**
 * このモジュールが提供する機能で使用する基本乱数発生器を扱う.
 * 
 * <p>
 * 基本的なインスタンスは, このインターフェース内に定義された
 * static ファクトリメソッドにより得られる.
 * </p>
 * 
 * @implSpec
 *               このインターフェースは実装を隠ぺいして型を公開するためのものである. <br>
 *               モジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 */
public interface BaseRandom {

    /**
     * {@code true} または {@code false} を等確率で返す.
     * 
     * @return {@code true}, {@code false} が等確率
     */
    public abstract boolean nextBoolean();

    /**
     * {@code long} が取り得る2<sup>64</sup>種類の値のいずれかを等確率で返す.
     * 
     * @return {@code long} が取り得る値全体のうちの1個
     */
    public abstract long nextLong();

    /**
     * {@code int} が取り得る2<sup>32</sup>種類の値のいずれかを等確率で返す.
     * 
     * @return {@code int} が取り得る値全体のうちの1個
     */
    public abstract int nextInt();

    /**
     * 0 &le; x &lt; bound を満たす一様整数乱数を発生する.
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
     * 0 &le; x &lt; 1 を満たす一様乱数を発生する.
     * 
     * @return 0以上1未満の値
     */
    public abstract double nextDouble();

    /**
     * 標準指数分布に従う乱数を発生させる.
     * 
     * <p>
     * 標準指数分布の確率密度関数 P(<i>x</i>) は次のとおりである. <br>
     * (ただし, 境界値が発生する可能性がある.)
     * </p>
     * 
     * <ul>
     * <li>
     * P(<i>x</i>) &prop;
     * exp(-<i>x</i>)
     * &emsp; (0 &lt; <i>x</i> &lt; +&infin;)
     * </li>
     * </ul>
     * 
     * @return 0以上の値 (+&infin;の可能性もある)
     */
    public abstract double nextExponential();

    /**
     * 標準正規分布に従う乱数を発生させる.
     * 
     * <p>
     * 標準正規分布の確率密度関数 P(<i>x</i>) は次のとおりである. <br>
     * (ただし, 境界値が発生する可能性がある.)
     * </p>
     * 
     * <ul>
     * <li>
     * P(<i>x</i>) &prop; exp(-<i>x</i><sup>2</sup> / 2)
     * &emsp; (-&infin; &lt; <i>x</i> &lt; +&infin;)
     * </li>
     * </ul>
     * 
     * @return NaN以外の {@code double} 値 (&pm;&infin;の可能性もある)
     */
    public abstract double nextGaussian();

    /**
     * {@code java.util.random.RandomGenerator} をラップした
     * {@link BaseRandom} インスタンスを返す.
     * 
     * <p>
     * このメソッドは, 実体のある {@link java.util.random.RandomGenerator}
     * インスタンスを使用した乱数生成を行う用途に適合する. <br>
     * 例えば, 適切に split された {@link java.util.SplittableRandom} インスタンスを渡して
     * {@link BaseRandom} を構築する.
     * </p>
     * 
     * @param random ラップされるインスタンス
     * @return ラップされた {@link BaseRandom}
     * @throws NullPointerException 引数がnullの場合
     */
    public static BaseRandom of(java.util.random.RandomGenerator random) {
        return new BaseRandomHelper.RandomImpl(random);
    }

    /**
     * 与えたサプライヤにより {@link java.util.random.RandomGenerator}
     * を呼び出して乱数を生成するように振る舞う
     * {@link BaseRandom} インスタンスを返す.
     * 
     * <p>
     * このメソッドにより返される {@link BaseRandom} は,
     * 毎回の乱数生成のたびに {@link Supplier#get()} を行う. <br>
     * すなわち, 独自の {@link java.util.random.RandomGenerator} インスタンス管理機構を持つ仕組みに適合する.
     * <br>
     * 例えば, <br>
     * {@code getter = () -> java.util.concurrent.ThreadLocalRandom.current();}
     * <br>
     * などである. <br>
     * (これは単なる使用例であり, {@link java.util.concurrent.ThreadLocalRandom}
     * はこの仕組みに適していない. <br>
     * 並行処理のためにスレッドローカルな仕組みを使うなら, {@link #threadSeparatedRandom()} を使用すべきである.)
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
     * @throws NullPointerException 引数がnullの場合, getterからget()した結果がnullの場合
     */
    public static BaseRandom fromGetter(Supplier<? extends java.util.random.RandomGenerator> getter) {
        //ここでNullPointerExを発生させる可能性がある
        return new BaseRandomHelper.RandomFromGetter(getter);
    }

    /**
     * スレッド間で競合が発生しないような {@link BaseRandom} インスタンスを返す.
     * 
     * <p>
     * このインスタンスはスレッド間の競合が発生しないように実装されている. <br>
     * よって, このインスタンス自体は複数スレッドで共有されたとしても, 適切に動作する.
     * </p>
     * 
     * @return スレッド間の競合が発生しない {@link BaseRandom}
     */
    public static BaseRandom threadSeparatedRandom() {
        return BaseRandomHelper.THREAD_SEPARATED_RANDOM;
    }
}
