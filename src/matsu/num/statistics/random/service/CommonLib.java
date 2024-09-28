/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.9.28
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
 * デフォルトインスタンスの生成は {@link #defaultImplemented()} で可能だが,
 * その他はビルダを使用する.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 21.0
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
     * <p>
     * {@link CommonLib} のイミュータブルビルダ.
     * </p>
     * 
     * <p>
     * ビルダ生成時には, デフォルトとなるライブラリがセットされている. <br>
     * 個別のライブラリに置き換える場合は専用のメソッドを用いる.
     * </p>
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
         * デフォルトのライブラリが入った状態を初期状態として, ビルダを返す.
         * 
         * @return 初期ビルダ
         */
        public static Builder implementedInit() {
            return DEFAULT_BUILDER;
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
