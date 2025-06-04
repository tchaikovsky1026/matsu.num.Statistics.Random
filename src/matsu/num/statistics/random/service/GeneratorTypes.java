/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/*
 * 2025.6.4
 */
package matsu.num.statistics.random.service;

import matsu.num.statistics.random.ArcsineRnd;
import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.BinomialRnd;
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
import matsu.num.statistics.random.NegativeBinomialRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.PoissonRnd;
import matsu.num.statistics.random.StaticBetaRnd;
import matsu.num.statistics.random.StaticGammaRnd;
import matsu.num.statistics.random.TDistributionRnd;
import matsu.num.statistics.random.VoigtRnd;
import matsu.num.statistics.random.WeibullRnd;
import matsu.num.statistics.random.YuleSimonRnd;
import matsu.num.statistics.random.ZetaRnd;
import matsu.num.statistics.random.arcsin.ZigguratArcsineRnd;
import matsu.num.statistics.random.beta.GammaBasedBetaRnd;
import matsu.num.statistics.random.binomial.DirichletBasedBinomialRnd;
import matsu.num.statistics.random.cat.TableBasedCategoricalRnd;
import matsu.num.statistics.random.cauchy.ZiggCauchyRnd;
import matsu.num.statistics.random.chisq.GammaTypeChiSquaredRnd;
import matsu.num.statistics.random.exp.ZiggExponentialRnd;
import matsu.num.statistics.random.fdist.BetaBasedFDistributionRnd;
import matsu.num.statistics.random.gamma.MTTypeGammaRndFactory;
import matsu.num.statistics.random.geo.InversionBasedGeoRnd;
import matsu.num.statistics.random.gumbel.UniZiggGumbelRnd;
import matsu.num.statistics.random.levy.NormalBasedLevyRnd;
import matsu.num.statistics.random.logi.ZiggLogiRnd;
import matsu.num.statistics.random.negbinomial.GammaPoissonBasedNegativeBinomialRnd;
import matsu.num.statistics.random.norm.ZiggNormalRnd;
import matsu.num.statistics.random.poi.GammaHomoProcessBasedPoissonRnd;
import matsu.num.statistics.random.staticbeta.GammaBasedStaticBetaRnd;
import matsu.num.statistics.random.staticgamma.MTTypeStaticGammaRnd;
import matsu.num.statistics.random.tdist.NormalGammaBasedTDistRnd;
import matsu.num.statistics.random.voigt.StandardImplVoigtRnd;
import matsu.num.statistics.random.weibull.GumbelBasedWeibullRnd;
import matsu.num.statistics.random.yulesimon.ExpGeometricBasedYuleSimonRnd;
import matsu.num.statistics.random.zeta.RejectionSamplingZetaRnd;

/**
 * <p>
 * このモジュールが管理する {@link RandomGeneratorType} の定数インスタンスの列挙を公開するユーティリティクラス. <br>
 * {@link RandomGeneratorType} インスタンスは全てこのクラス内に定義されている.
 * </p>
 * 
 * @author Matsuura Y.
 */
public final class GeneratorTypes {

    /**
     * 逆正弦分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<ArcsineRnd.Factory> ARCSINE_RND;
    /**
     * ベータ分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<BetaRnd.Factory> BETA_RND;
    /**
     * 二項分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<BinomialRnd.Factory> BINOMIAL_RND;
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
     * 負の二項分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<NegativeBinomialRnd.Factory> NEGATIVE_BINOMIAL_RND;
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
    /**
     * Yule-Simon 分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<YuleSimonRnd.Factory> YULE_SIMON_RND;
    /**
     * ゼータ分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<ZetaRnd.Factory> ZETA_RND;

    static {
        ARCSINE_RND = new RandomGeneratorType<>(
                "ARCSINE_RND", ArcsineRnd.Factory.class,
                p -> ZigguratArcsineRnd.createFactory(p.lib().exponentiation()));

        BETA_RND = new RandomGeneratorType<>(
                "BETA_RND", BetaRnd.Factory.class,
                p -> GammaBasedBetaRnd.createFactory(p.get(GeneratorTypes.GAMMA_RND)));

        BINOMIAL_RND = new RandomGeneratorType<>(
                "BINOMIAL_RND", BinomialRnd.Factory.class,
                p -> DirichletBasedBinomialRnd.createFactory(p.get(GeneratorTypes.GAMMA_RND)));

        CATEGORICAL_RND = new RandomGeneratorType<>(
                "CATEGORICAL_RND", CategoricalRnd.Factory.class,
                p -> TableBasedCategoricalRnd.createFactory(p.lib().exponentiation()));

        CAUCHY_RND = new RandomGeneratorType<>(
                "CAUCHY_RND", CauchyRnd.Factory.class,
                p -> ZiggCauchyRnd.createFactory(p.lib().exponentiation()));

        CHI_SQUARED_RND = new RandomGeneratorType<>(
                "CHI_SQUARED_RND", ChiSquaredRnd.Factory.class,
                p -> GammaTypeChiSquaredRnd.createFactory(p.get(GeneratorTypes.GAMMA_RND)));

        EXPONENTIAL_RND = new RandomGeneratorType<>(
                "EXPONENTIAL_RND", ExponentialRnd.Factory.class,
                p -> ZiggExponentialRnd.createFactory(p.lib().exponentiation()));

        F_DISTRIBUTION_RND = new RandomGeneratorType<>(
                "F_DISTRIBUTION_RND", FDistributionRnd.Factory.class,
                p -> BetaBasedFDistributionRnd.createFactory(p.get(GeneratorTypes.BETA_RND)));

        GAMMA_RND = new RandomGeneratorType<>(
                "GAMMA_RND", GammaRnd.Factory.class,
                p -> MTTypeGammaRndFactory.create(
                        p.lib().exponentiation(),
                        p.get(GeneratorTypes.EXPONENTIAL_RND), p.get(GeneratorTypes.NORMAL_RND)));

        GEOMETRIC_RND = new RandomGeneratorType<>(
                "GEOMETRIC_RND", GeometricRnd.Factory.class,
                p -> InversionBasedGeoRnd.createFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.EXPONENTIAL_RND)));

        GUMBEL_RND = new RandomGeneratorType<>(
                "GUMBEL_RND", GumbelRnd.Factory.class,
                p -> UniZiggGumbelRnd.createFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.EXPONENTIAL_RND)));

        LEVY_RND = new RandomGeneratorType<>(
                "LEVY_RND", LevyRnd.Factory.class,
                p -> NormalBasedLevyRnd.createFactory(p.get(GeneratorTypes.NORMAL_RND)));

        LOGISTIC_RND = new RandomGeneratorType<>(
                "LOGISTIC_RND", LogisticRnd.Factory.class,
                p -> ZiggLogiRnd.createFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.EXPONENTIAL_RND)));

        NEGATIVE_BINOMIAL_RND = new RandomGeneratorType<>(
                "NEGATIVE_BINOMIAL_RND", NegativeBinomialRnd.Factory.class,
                p -> GammaPoissonBasedNegativeBinomialRnd.createFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.GAMMA_RND)));

        NORMAL_RND = new RandomGeneratorType<>(
                "NORMAL_RND", NormalRnd.Factory.class,
                p -> ZiggNormalRnd.createFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.EXPONENTIAL_RND)));

        POISSON_RND = new RandomGeneratorType<>(
                "POISSON_RND", PoissonRnd.Factory.class,
                p -> GammaHomoProcessBasedPoissonRnd.createFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.GAMMA_RND)));

        STATIC_BETA_RND = new RandomGeneratorType<>(
                "STATIC_BETA_RND", StaticBetaRnd.Factory.class,
                p -> GammaBasedStaticBetaRnd.createFactory(p.get(GeneratorTypes.STATIC_GAMMA_RND)));

        STATIC_GAMMA_RND = new RandomGeneratorType<>(
                "STATIC_GAMMA_RND", StaticGammaRnd.Factory.class,
                p -> MTTypeStaticGammaRnd.createFactory(
                        p.lib().exponentiation(),
                        p.get(GeneratorTypes.EXPONENTIAL_RND), p.get(GeneratorTypes.NORMAL_RND)));

        T_DISTRIBUTION_RND = new RandomGeneratorType<>(
                "T_DISTRIBUTION_RND", TDistributionRnd.Factory.class,
                p -> NormalGammaBasedTDistRnd.createFactory(
                        p.lib().exponentiation(),
                        p.get(GeneratorTypes.NORMAL_RND), p.get(GeneratorTypes.GAMMA_RND)));

        VOIGT_RND = new RandomGeneratorType<>(
                "VOIGT_RND", VoigtRnd.Factory.class,
                p -> StandardImplVoigtRnd.createFactory(
                        p.get(GeneratorTypes.NORMAL_RND), p.get(GeneratorTypes.CAUCHY_RND)));

        WEIBULL_RND = new RandomGeneratorType<>(
                "WEIBULL_RND", WeibullRnd.Factory.class,
                p -> GumbelBasedWeibullRnd.createFactory(
                        p.lib().exponentiation(), p.get(GeneratorTypes.GUMBEL_RND)));

        YULE_SIMON_RND = new RandomGeneratorType<>(
                "YULE_SIMON_RND", YuleSimonRnd.Factory.class,
                p -> ExpGeometricBasedYuleSimonRnd.createFactory(
                        p.lib().exponentiation(),
                        p.get(GeneratorTypes.EXPONENTIAL_RND)));

        ZETA_RND = new RandomGeneratorType<>(
                "ZETA_RND", ZetaRnd.Factory.class,
                p -> RejectionSamplingZetaRnd.createFactory(
                        p.lib().exponentiation(),
                        p.get(GeneratorTypes.EXPONENTIAL_RND)));
    }

    private GeneratorTypes() {
        //インスタンス化不可.
        throw new AssertionError();
    }
}
