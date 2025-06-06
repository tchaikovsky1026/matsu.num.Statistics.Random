/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.6
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
 * 注意:
 * このクラスをモジュール外では型として扱うべきではない.
 * 具体的に, 次のような取り扱いは強く非推奨である.
 * </i></u>
 * </p>
 * 
 * <ul>
 * <li>このクラスを変数宣言の型として使う.</li>
 * <li>{@code instanceof} 演算子により, このクラスのサブタイプかを判定する.</li>
 * <li>インスタンスをこのクラスにキャストして使用する.</li>
 * </ul>
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
