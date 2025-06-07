/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.7
 */
package matsu.num.statistics.random;

/**
 * パラメータを持たない乱数発生器のファクトリを表現する.
 * 
 * <p>
 * 乱数発生器は {@link #instance()} メソッドにより取得する.
 * </p>
 * 
 * <p>
 * <u><i>
 * 注意: <br>
 * このインターフェースは将来にわたって維持されるとは限らず,
 * モジュール外ではこのインターフェースを型として依存してはいけない. <br>
 * 公開されたサブインターフェースが存在する場合, 型はサブインターフェースにより扱われなければならない.
 * </i></u>
 * </p>
 * 
 * @implSpec
 *               このインターフェースをモジュール外で継承・実装してはいけない.
 * 
 * @author Matsuura Y.
 * @param <T> このファクトリが扱う乱数発生器の型
 */
public interface ParameterlessRndFactory<T extends Rnd> extends RndFactory {

    /**
     * 乱数発生器インスタンスを返す.
     *
     * @return 乱数発生器
     */
    public abstract T instance();

}
