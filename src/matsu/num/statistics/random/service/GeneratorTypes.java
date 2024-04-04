/*
 * Copyright (c) 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2024.4.4
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
import matsu.num.statistics.random.RandomGeneratorFactory;
import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.TDistributionRnd;
import matsu.num.statistics.random.VoigtRnd;
import matsu.num.statistics.random.WeibullRnd;
import matsu.num.statistics.random.beta.GammaBasedBetaRndFactory;
import matsu.num.statistics.random.cat.TableBasedCategoricalRndFactory;
import matsu.num.statistics.random.cauchy.ZiggCauchyRndFactory;
import matsu.num.statistics.random.chisq.GammaTypeChiSquaredRndFactory;
import matsu.num.statistics.random.exp.ZiggExponentialRndFactory;
import matsu.num.statistics.random.fdist.BetaBasedFDistributionRndFactory;
import matsu.num.statistics.random.gamma.MTTypeGammaRndFactory;
import matsu.num.statistics.random.geo.InversionBasedGeoRndFactory;
import matsu.num.statistics.random.gumbel.UniZiggGumbelRndFactory;
import matsu.num.statistics.random.levy.NormalBasedLevyRndFactory;
import matsu.num.statistics.random.logi.ZiggLogiRndFactory;
import matsu.num.statistics.random.norm.ZiggNormalRndFactory;
import matsu.num.statistics.random.poi.GammaHomoProcessBasedPoissonRndFactory;
import matsu.num.statistics.random.service.functionaltype.FunctionalTypeImpl;
import matsu.num.statistics.random.staticbeta.GammaBasedStaticBetaRndFactory;
import matsu.num.statistics.random.staticgamma.MTTypeStaticGammaRndFactory;
import matsu.num.statistics.random.tdist.NormalGammaBasedTDistRndFactory;
import matsu.num.statistics.random.voigt.StandardImplVoigtRndFactory;
import matsu.num.statistics.random.weibull.GumbelBasedWeibullRndFactory;

/**
 * このパッケージが管理する {@link RandomGeneratorType} 定数.
 * 
 * @author Matsuura Y.
 * @version 20.0
 */
public final class GeneratorTypes {

    private static final Collection<RandomGeneratorType<? extends RandomGeneratorFactory>> values;

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
        List<RandomGeneratorType<? extends RandomGeneratorFactory>> list = new ArrayList<>();

        BETA_RND = new FunctionalTypeImpl<>(
                "BETA_RND", BetaRnd.Factory.class,
                p -> new GammaBasedBetaRndFactory(p.get(GeneratorTypes.GAMMA_RND)));
        list.add(BETA_RND);

        CATEGORICAL_RND = new FunctionalTypeImpl<>(
                "CATEGORICAL_RND", CategoricalRnd.Factory.class,
                p -> new TableBasedCategoricalRndFactory(p.lib().exponentiation()));
        list.add(CATEGORICAL_RND);

        CAUCHY_RND = new FunctionalTypeImpl<>(
                "CAUCHY_RND", CauchyRnd.Factory.class,
                p -> new ZiggCauchyRndFactory(p.lib().exponentiation()));
        list.add(CAUCHY_RND);

        CHI_SQUARED_RND = new FunctionalTypeImpl<>(
                "CHI_SQUARED_RND", ChiSquaredRnd.Factory.class,
                p -> new GammaTypeChiSquaredRndFactory(p.get(GeneratorTypes.GAMMA_RND)));
        list.add(CHI_SQUARED_RND);

        EXPONENTIAL_RND = new FunctionalTypeImpl<>(
                "EXPONENTIAL_RND", ExponentialRnd.Factory.class,
                p -> new ZiggExponentialRndFactory(p.lib().exponentiation()));
        list.add(EXPONENTIAL_RND);

        F_DISTRIBUTION_RND = new FunctionalTypeImpl<>(
                "F_DISTRIBUTION_RND", FDistributionRnd.Factory.class,
                p -> new BetaBasedFDistributionRndFactory(p.get(GeneratorTypes.BETA_RND)));
        list.add(F_DISTRIBUTION_RND);

        GAMMA_RND = new FunctionalTypeImpl<>(
                "GAMMA_RND", GammaRnd.Factory.class,
                p -> new MTTypeGammaRndFactory(
                        p.lib().exponentiation(),
                        p.get(GeneratorTypes.EXPONENTIAL_RND), p.get(GeneratorTypes.NORMAL_RND)));
        list.add(GAMMA_RND);

        GEOMETRIC_RND = new FunctionalTypeImpl<>(
                "GEOMETRIC_RND", GeometricRnd.Factory.class,
                p -> new InversionBasedGeoRndFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.EXPONENTIAL_RND)));
        list.add(GEOMETRIC_RND);

        GUMBEL_RND = new FunctionalTypeImpl<>(
                "GUMBEL_RND", GumbelRnd.Factory.class,
                p -> new UniZiggGumbelRndFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.EXPONENTIAL_RND)));
        list.add(GUMBEL_RND);

        LEVY_RND = new FunctionalTypeImpl<>(
                "LEVY_RND", LevyRnd.Factory.class,
                p -> new NormalBasedLevyRndFactory(p.get(GeneratorTypes.NORMAL_RND)));
        list.add(LEVY_RND);

        LOGISTIC_RND = new FunctionalTypeImpl<>(
                "LOGISTIC_RND", LogisticRnd.Factory.class,
                p -> new ZiggLogiRndFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.EXPONENTIAL_RND)));
        list.add(LOGISTIC_RND);

        NORMAL_RND = new FunctionalTypeImpl<>(
                "NORMAL_RND", NormalRnd.Factory.class,
                p -> new ZiggNormalRndFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.EXPONENTIAL_RND)));
        list.add(NORMAL_RND);

        POISSON_RND = new FunctionalTypeImpl<>(
                "POISSON_RND", PoissonRnd.Factory.class,
                p -> new GammaHomoProcessBasedPoissonRndFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.GAMMA_RND)));
        list.add(POISSON_RND);

        STATIC_BETA_RND = new FunctionalTypeImpl<>(
                "STATIC_BETA_RND", StaticBetaRnd.Factory.class,
                p -> new GammaBasedStaticBetaRndFactory(p.get(GeneratorTypes.STATIC_GAMMA_RND)));
        list.add(STATIC_BETA_RND);

        STATIC_GAMMA_RND = new FunctionalTypeImpl<>(
                "STATIC_GAMMA_RND", StaticGammaRnd.Factory.class,
                p -> new MTTypeStaticGammaRndFactory(
                        p.lib().exponentiation(),
                        p.get(GeneratorTypes.EXPONENTIAL_RND), p.get(GeneratorTypes.NORMAL_RND)));
        list.add(STATIC_GAMMA_RND);

        T_DISTRIBUTION_RND = new FunctionalTypeImpl<>(
                "T_DISTRIBUTION_RND", TDistributionRnd.Factory.class,
                p -> new NormalGammaBasedTDistRndFactory(
                        p.lib().exponentiation(),
                        p.get(GeneratorTypes.NORMAL_RND), p.get(GeneratorTypes.GAMMA_RND)));
        list.add(T_DISTRIBUTION_RND);

        VOIGT_RND = new FunctionalTypeImpl<>(
                "VOIGT_RND", VoigtRnd.Factory.class,
                p -> new StandardImplVoigtRndFactory(
                        p.get(GeneratorTypes.NORMAL_RND), p.get(GeneratorTypes.CAUCHY_RND)));
        list.add(VOIGT_RND);

        WEIBULL_RND = new FunctionalTypeImpl<>(
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
    static Collection<? extends RandomGeneratorType<? extends RandomGeneratorFactory>> values() {
        return values;
    }

    private GeneratorTypes() {
        //インスタンス化不可.
        throw new AssertionError();
    }
}
