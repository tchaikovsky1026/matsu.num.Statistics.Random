/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.11.8
 */
package matsu.num.statistics.random.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.CategoricalRnd;
import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.ChiSquaredRnd;
import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.FDistributionRnd;
import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.GeometricRnd;
import matsu.num.statistics.random.GumbelRnd;
import matsu.num.statistics.random.LevyRnd;
import matsu.num.statistics.random.LogisticRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.PoissonRnd;
import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.TDistributionRnd;
import matsu.num.statistics.random.VoigtRnd;
import matsu.num.statistics.random.WeibullRnd;
import matsu.num.statistics.random.beta.GammaBasedBetaRnd;
import matsu.num.statistics.random.cat.TableBasedCategoricalRnd;
import matsu.num.statistics.random.cauchy.ZiggCauchyRnd;
import matsu.num.statistics.random.chisq.GammaTypeChiSquaredRnd;
import matsu.num.statistics.random.exp.ZiggExponentialRnd;
import matsu.num.statistics.random.fdist.BetaBasedFDistributionRnd;
import matsu.num.statistics.random.gamma.MTTypeGammaRndFactory;
import matsu.num.statistics.random.geo.InversionBasedGeoRndFactory;
import matsu.num.statistics.random.gumbel.UniZiggGumbelRnd;
import matsu.num.statistics.random.levy.NormalBasedLevyRnd;
import matsu.num.statistics.random.logi.ZiggLogiRnd;
import matsu.num.statistics.random.norm.ZiggNormalRnd;
import matsu.num.statistics.random.poi.GammaHomoProcessBasedPoissonRndFactory;
import matsu.num.statistics.random.staticbeta.GammaBasedStaticBetaRnd;
import matsu.num.statistics.random.staticgamma.MTTypeStaticGammaRnd;
import matsu.num.statistics.random.tdist.NormalGammaBasedTDistRndFactory;
import matsu.num.statistics.random.voigt.StandardImplVoigtRndFactory;
import matsu.num.statistics.random.weibull.GumbelBasedWeibullRndFactory;

/**
 * <p>
 * このモジュールが管理する {@link RandomGeneratorType} の定数インスタンスの列挙を公開するユーティリティクラス. <br>
 * {@link RandomGeneratorType} インスタンスは全てこのクラス内に定義されている.
 * </p>
 * 
 * @author Matsuura Y.
 */
public final class GeneratorTypes {

    private static final Collection<RandomGeneratorType<?>> values;

    /**
     * ベータ分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<BetaRnd.Factory> BETA_RND;
    /**
     * カテゴリカル分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<CategoricalRnd.Factory> CATEGORICAL_RND;
    /**
     * 標準Cauchy分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<CauchyRnd.Factory> CAUCHY_RND;
    /**
     * カイ二乗分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<ChiSquaredRnd.Factory> CHI_SQUARED_RND;
    /**
     * 標準指数分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<ExponentialRnd.Factory> EXPONENTIAL_RND;
    /**
     * F分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<FDistributionRnd.Factory> F_DISTRIBUTION_RND;
    /**
     * 標準ガンマ分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<GammaRnd.Factory> GAMMA_RND;
    /**
     * 幾何分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<GeometricRnd.Factory> GEOMETRIC_RND;
    /**
     * 標準Gumbel分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<GumbelRnd.Factory> GUMBEL_RND;
    /**
     * 標準L&eacute;vy分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<LevyRnd.Factory> LEVY_RND;
    /**
     * 標準ロジスティック分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<LogisticRnd.Factory> LOGISTIC_RND;
    /**
     * 標準正規分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<NormalRnd.Factory> NORMAL_RND;
    /**
     * Poisson分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<PoissonRnd.Factory> POISSON_RND;
    /**
     * Staticベータ乱数発生器を表す.
     */
    public static final RandomGeneratorType<StaticBetaRnd.Factory> STATIC_BETA_RND;
    /**
     * Staticガンマ乱数発生器を表す.
     */
    public static final RandomGeneratorType<StaticGammaRnd.Factory> STATIC_GAMMA_RND;
    /**
     * Student-t分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<TDistributionRnd.Factory> T_DISTRIBUTION_RND;
    /**
     * Voigt分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<VoigtRnd.Factory> VOIGT_RND;
    /**
     * 標準Weibull分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<WeibullRnd.Factory> WEIBULL_RND;

    static {
        List<RandomGeneratorType<?>> list = new ArrayList<>();

        BETA_RND = new RandomGeneratorType<>(
                "BETA_RND", BetaRnd.Factory.class,
                p -> GammaBasedBetaRnd.createFactory(p.get(GeneratorTypes.GAMMA_RND)));
        list.add(BETA_RND);

        CATEGORICAL_RND = new RandomGeneratorType<>(
                "CATEGORICAL_RND", CategoricalRnd.Factory.class,
                p -> TableBasedCategoricalRnd.createFactory(p.lib().exponentiation()));
        list.add(CATEGORICAL_RND);

        CAUCHY_RND = new RandomGeneratorType<>(
                "CAUCHY_RND", CauchyRnd.Factory.class,
                p -> ZiggCauchyRnd.createFactory(p.lib().exponentiation()));
        list.add(CAUCHY_RND);

        CHI_SQUARED_RND = new RandomGeneratorType<>(
                "CHI_SQUARED_RND", ChiSquaredRnd.Factory.class,
                p -> GammaTypeChiSquaredRnd.createFactory(p.get(GeneratorTypes.GAMMA_RND)));
        list.add(CHI_SQUARED_RND);

        EXPONENTIAL_RND = new RandomGeneratorType<>(
                "EXPONENTIAL_RND", ExponentialRnd.Factory.class,
                p -> ZiggExponentialRnd.createFactory(p.lib().exponentiation()));
        list.add(EXPONENTIAL_RND);

        F_DISTRIBUTION_RND = new RandomGeneratorType<>(
                "F_DISTRIBUTION_RND", FDistributionRnd.Factory.class,
                p -> BetaBasedFDistributionRnd.createFactory(p.get(GeneratorTypes.BETA_RND)));
        list.add(F_DISTRIBUTION_RND);

        GAMMA_RND = new RandomGeneratorType<>(
                "GAMMA_RND", GammaRnd.Factory.class,
                p -> MTTypeGammaRndFactory.create(
                        p.lib().exponentiation(),
                        p.get(GeneratorTypes.EXPONENTIAL_RND), p.get(GeneratorTypes.NORMAL_RND)));
        list.add(GAMMA_RND);

        GEOMETRIC_RND = new RandomGeneratorType<>(
                "GEOMETRIC_RND", GeometricRnd.Factory.class,
                p -> new InversionBasedGeoRndFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.EXPONENTIAL_RND)));
        list.add(GEOMETRIC_RND);

        GUMBEL_RND = new RandomGeneratorType<>(
                "GUMBEL_RND", GumbelRnd.Factory.class,
                p -> UniZiggGumbelRnd.createFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.EXPONENTIAL_RND)));
        list.add(GUMBEL_RND);

        LEVY_RND = new RandomGeneratorType<>(
                "LEVY_RND", LevyRnd.Factory.class,
                p -> NormalBasedLevyRnd.createFactory(p.get(GeneratorTypes.NORMAL_RND)));
        list.add(LEVY_RND);

        LOGISTIC_RND = new RandomGeneratorType<>(
                "LOGISTIC_RND", LogisticRnd.Factory.class,
                p -> ZiggLogiRnd.createFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.EXPONENTIAL_RND)));
        list.add(LOGISTIC_RND);

        NORMAL_RND = new RandomGeneratorType<>(
                "NORMAL_RND", NormalRnd.Factory.class,
                p -> ZiggNormalRnd.createFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.EXPONENTIAL_RND)));
        list.add(NORMAL_RND);

        POISSON_RND = new RandomGeneratorType<>(
                "POISSON_RND", PoissonRnd.Factory.class,
                p -> new GammaHomoProcessBasedPoissonRndFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.GAMMA_RND)));
        list.add(POISSON_RND);

        STATIC_BETA_RND = new RandomGeneratorType<>(
                "STATIC_BETA_RND", StaticBetaRnd.Factory.class,
                p -> GammaBasedStaticBetaRnd.createFactory(p.get(GeneratorTypes.STATIC_GAMMA_RND)));
        list.add(STATIC_BETA_RND);

        STATIC_GAMMA_RND = new RandomGeneratorType<>(
                "STATIC_GAMMA_RND", StaticGammaRnd.Factory.class,
                p -> MTTypeStaticGammaRnd.createFactory(
                        p.lib().exponentiation(),
                        p.get(GeneratorTypes.EXPONENTIAL_RND), p.get(GeneratorTypes.NORMAL_RND)));
        list.add(STATIC_GAMMA_RND);

        T_DISTRIBUTION_RND = new RandomGeneratorType<>(
                "T_DISTRIBUTION_RND", TDistributionRnd.Factory.class,
                p -> new NormalGammaBasedTDistRndFactory(
                        p.lib().exponentiation(),
                        p.get(GeneratorTypes.NORMAL_RND), p.get(GeneratorTypes.GAMMA_RND)));
        list.add(T_DISTRIBUTION_RND);

        VOIGT_RND = new RandomGeneratorType<>(
                "VOIGT_RND", VoigtRnd.Factory.class,
                p -> new StandardImplVoigtRndFactory(
                        p.get(GeneratorTypes.NORMAL_RND), p.get(GeneratorTypes.CAUCHY_RND)));
        list.add(VOIGT_RND);

        WEIBULL_RND = new RandomGeneratorType<>(
                "WEIBULL_RND", WeibullRnd.Factory.class,
                p -> new GumbelBasedWeibullRndFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.GUMBEL_RND)));
        list.add(WEIBULL_RND);

        values = Collections.unmodifiableList(list);
    }

    /**
     * 既知の {@link RandomGeneratorType} のコレクションを返す. <br>
     * (主にテスト用.)
     * 
     * @return {@link RandomGeneratorType} のコレクション
     */
    static Collection<? extends RandomGeneratorType<?>> values() {
        return values;
    }

    private GeneratorTypes() {
        //インスタンス化不可.
        throw new AssertionError();
    }
}
