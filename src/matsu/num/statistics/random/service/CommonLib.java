/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.18
 */
package matsu.num.statistics.random.service;

import java.util.Objects;

import matsu.num.statistics.random.lib.Exponentiation;

/**
 * <p>
 * このモジュールで使うライブラリを管理する概念. <br>
 * イミュータブルである.
 * </p>
 * 
 * <p>
 * デフォルトのライブラリを扱うインスタンスの取得は {@link #defaultImplemented()} で可能だが,
 * その他のインスタンスはビルダ ({@link Builder}) を用いて生成する.
 * </p>
 * 
 * <p>
 * <i>コンストラクタが公開されていないので, 外部からの継承は不可.</i>
 * </p>
 * 
 * @author Matsuura Y.
 * @version 22.2
 */
public abstract class CommonLib {

    /**
     * 管理外でのオーバーライドを禁止するために, パッケージプライベートにしている.
     */
    CommonLib() {
        super();
    }

    /**
     * 指数関数, 対数関数の計算を扱うライブラリを返す.
     * 
     * @return 指数関数, 対数関数の計算
     */
    abstract Exponentiation exponentiation();

    /**
     * このライブラリの文字列表現.
     * 
     * @return 文字列表現
     */
    @Override
    public String toString() {
        return "CommonLib";
    }

    /**
     * デフォルトライブラリを返す.
     * 
     * @return デフォルトライブラリ
     */
    public static CommonLib defaultImplemented() {
        return CommonLibDefaultHolder.DEFAULT_INSTANCE;
    }

    /**
     * -
     * 
     * @return -
     * @throws CloneNotSupportedException 常に
     * @deprecated Clone不可
     */
    @Deprecated
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /**
     * <p>
     * {@link CommonLib} のイミュータブルなビルダ.
     * </p>
     * 
     * <p>
     * 基本となるビルダインスタンスは, {@link #implementedInit()} により取得する. <br>
     * このビルダインスタンスにはデフォルトとなるライブラリがセットされている. <br>
     * 個別のライブラリに置き換える場合は専用のメソッドを用いる. <br>
     * ただし, ビルダインスタンスはイミュータブルであるため, 戻り値を受け取る必要がある.
     * </p>
     * 
     * <p>
     * 使用例は以下である
     * (メソッド名は適宜読み替える).
     * </p>
     * 
     * 
     * <blockquote>
     * 
     * <pre>
     * // 基本となるビルダインスタンスの取得
     * CommonLib.Builder builder =　CommonLib.Builder.implementedInit();　
     * // 個別のライブラリで置き換えた新しいビルダインスタンスを受け取る
     * builder = builder.replacedX(myXLibrary);
     * // ライブラリのビルド
     * CommonLib lib = builder.build();
     * </pre>
     * 
     * </blockquote>
     */
    public static final class Builder {

        private static final Builder DEFAULT_BUILDER = new Builder();

        private Exponentiation exponentiation;

        /**
         * このビルダの状態でビルドされたインスタンス.
         * 遅延初期化される.
         */
        private volatile CommonLib build;

        /**
         * デフォルトライブラリを保持したビルダを構築する.
         */
        private Builder() {
            super();
            CommonLib defaultInstance = CommonLibDefaultHolder.DEFAULT_INSTANCE;
            this.exponentiation = defaultInstance.exponentiation();
        }

        /**
         * 内部から呼ばれるコピーコンストラクタ.
         * 
         * @param src ソース
         */
        private Builder(Builder src) {
            this.exponentiation = src.exponentiation;
        }

        /**
         * デフォルトのライブラリが入った状態のビルダインスタンスを返す.
         * 
         * @return ビルダインスタンス
         */
        public static Builder implementedInit() {
            return DEFAULT_BUILDER;
        }

        /**
         * <p>
         * 自身の指数関数計算器を引数のものに置き換え, 新しいビルダインスタンスとして返す. <br>
         * 新しいインスタンスであるため, 戻り値を無視してはいけない.
         * </p>
         * 
         * @param newExponentiation 指数関数計算器
         * @return 置き換え後の新しいビルダ
         * @throws NullPointerException 引数にnullが含まれる場合
         */
        public Builder replacedExponentiation(Exponentiation newExponentiation) {
            Builder out = new Builder(this);
            out.exponentiation = Objects.requireNonNull(newExponentiation);
            return out;
        }

        /**
         * {@link CommonLib} をビルドする.
         * 
         * @return ビルドされたインスタンス.
         */
        public CommonLib build() {
            CommonLib out = this.build;
            if (Objects.nonNull(out)) {
                return out;
            }

            out = new CommonLibImpl(this);
            this.build = out;
            return out;
        }

        /**
         * このインスタンスの文字列表現
         * 
         * @return 文字列表現
         */
        @Override
        public String toString() {
            return "LibBuilder";
        }

        /**
         * -
         * 
         * @return -
         * @throws CloneNotSupportedException 常に
         * @deprecated Clone不可
         */
        @Deprecated
        @Override
        protected Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }

        /**
         * ビルダを用いて生成される {@link CommonLib} の具象クラス.
         */
        private static final class CommonLibImpl extends CommonLib {

            private final Exponentiation exponentiation;

            /**
             * ビルダから呼ばれる.
             */
            CommonLibImpl(Builder builder) {
                super();
                this.exponentiation = builder.exponentiation;
            }

            @Override
            public Exponentiation exponentiation() {
                return this.exponentiation;
            }

            @Override
            public String toString() {
                return "CommonLib(byBuilder)";
            }
        }
    }
}
