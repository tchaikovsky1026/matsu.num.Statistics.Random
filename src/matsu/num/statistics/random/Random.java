/**
 * 2023.12.18
 */
package matsu.num.statistics.random;

import java.util.function.Supplier;

import matsu.num.statistics.random.random.impl.RandomFromGetter;
import matsu.num.statistics.random.random.impl.RandomImpl;
import matsu.num.statistics.random.random.impl.ThreadSeparatedRandom;

/**
 * <p>
 * このモジュールが提供する機能で使用する基本乱数発生器を扱う.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 17.3
 */
public interface Random {

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
     * {@code java.util.Random} をラップした
     * {@linkplain Random} インスタンスを返す.
     * </p>
     * 
     * @param random ラップされるインスタンス
     * @return ラップされた {@linkplain Random}
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static Random of(java.util.Random random) {
        return new RandomImpl(random);
    }

    /**
     * <p>
     * 与えたサプライヤにより {@code java.util.Random}
     * を呼び出して乱数を生成するように振る舞う
     * {@linkplain Random} インスタンスを返す.
     * </p>
     * 
     * <p>
     * このメソッドにより返される {@linkplain Random} は,
     * 毎回の乱数生成のたびに {@linkplain Supplier#get()} を行う. <br>
     * すなわち, 独自の {@code java.util.Random} インスタンス管理機構を持つ仕組みに適合する. <br>
     * 例えば, <br>
     * {@code getter = () -> java.util.concurrent.ThreadLocalRandom.current();}
     * <br>
     * などである.
     * </p>
     * 
     * <p>
     * {@linkplain Supplier#get()} がインスタンス生成を伴うような状況では,
     * このメソッドではなく {@linkplain #of(java.util.Random)} を使用すべきである. <br>
     * （つまり {@linkplain Supplier#get()} を呼ぶべきではない）.
     * </p>
     * 
     * @param getter {@code java.util.Random} を呼び出すためのサプライヤ
     * @return 乱数生成のたびにサプライヤから呼び出すように振る舞う {@linkplain Random}
     * @throws NullPointerException 引数にnullが含まれる場合
     */
    public static Random fromGetter(Supplier<? extends java.util.Random> getter) {
        //ここでNullPointerExを発生させる可能性がある
        return new RandomFromGetter(getter);
    }

    /**
     * <p>
     * 内部で競合が発生しないように完全に分離された {@linkplain Random} インスタンスを返す.
     * </p>
     * 
     * <p>
     * このインスタンスの内部ではスレッド間の競合が発生しないように分離されている. <br>
     * よって, このインスタンス自体は複数スレッドで共有できる.
     * </p>
     * 
     * <p>
     * このメソッドは, {@linkplain #fromGetter(Supplier)} の派生である.
     * </p>
     * 
     * @return スレッド間の競合が発生しない {@linkplain Random}
     */
    public static Random threadSeparatedRandom() {
        return ThreadSeparatedRandom.INSTANCE;
    }
}
