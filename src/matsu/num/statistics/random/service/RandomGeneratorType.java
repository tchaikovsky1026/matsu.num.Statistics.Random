/**
 * 2024.2.21
 */
package matsu.num.statistics.random.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import matsu.num.statistics.random.BetaRnd;
import matsu.num.statistics.random.CategoricalRnd;
import matsu.num.statistics.random.CauthyRnd;
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
import matsu.num.statistics.random.service.beta.GammaBasedBetaRndFactory;
import matsu.num.statistics.random.service.cat.TableBasedCategoricalRndFactory;
import matsu.num.statistics.random.service.cauthy.ZiggCauthyRndFactory;
import matsu.num.statistics.random.service.chisq.GammaTypeChiSquaredRndFactory;
import matsu.num.statistics.random.service.exp.ZiggExponentialRndFactory;
import matsu.num.statistics.random.service.fdist.BetaBasedFDistributionRndFactory;
import matsu.num.statistics.random.service.gamma.MTTypeGammaRndFactory;
import matsu.num.statistics.random.service.geo.InversionBasedGeoRndFactory;
import matsu.num.statistics.random.service.gumbel.UniZiggGumbelRndFactory;
import matsu.num.statistics.random.service.levy.NormalBasedLevyRndFactory;
import matsu.num.statistics.random.service.logi.ZiggLogiRndFactory;
import matsu.num.statistics.random.service.norm.ZiggNormalRndFactory;
import matsu.num.statistics.random.service.poi.GammaHomoProcessBasedPoissonRndFactory;
import matsu.num.statistics.random.service.staticbeta.GammaBasedStaticBetaRndFactory;
import matsu.num.statistics.random.service.staticgamma.MTTypeStaticGammaRndFactory;
import matsu.num.statistics.random.service.tdist.NormalGammaBasedTDistRndFactory;
import matsu.num.statistics.random.service.voigt.StandardImplVoigtRndFactory;
import matsu.num.statistics.random.service.weibull.GumbelBasedWeibullRndFactory;

/**
 * このモジュールが提供する乱数生成器のタイプ.
 * 
 * @author Matsuura Y.
 * @version 18.1
 * @param <T> このタイプが返却する乱数発生器ファクトリの型,
 *            {@link CommonLib} 以外の状態を持たないことが保証されている.
 */
public final class RandomGeneratorType<T extends RandomGeneratorFactory> {

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
     * 標準Cauthy分布に従う乱数を表す.
     */
    public static final RandomGeneratorType<CauthyRnd.Factory> CAUTHY_RND;
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

        BETA_RND = new RandomGeneratorType<>(
                BetaRnd.Factory.class, GammaBasedBetaRndFactory::new, "BETA_RND");
        list.add(BETA_RND);

        CATEGORICAL_RND = new RandomGeneratorType<>(
                CategoricalRnd.Factory.class, TableBasedCategoricalRndFactory::new, "CATEGORICAL_RND");
        list.add(CATEGORICAL_RND);

        CAUTHY_RND = new RandomGeneratorType<>(
                CauthyRnd.Factory.class, ZiggCauthyRndFactory::new, "CAUTHY_RND");
        list.add(CAUTHY_RND);

        CHI_SQUARED_RND = new RandomGeneratorType<>(
                ChiSquaredRnd.Factory.class, GammaTypeChiSquaredRndFactory::new, "CHI_SQUARED_RND");
        list.add(CHI_SQUARED_RND);

        EXPONENTIAL_RND = new RandomGeneratorType<>(
                ExponentialRnd.Factory.class, ZiggExponentialRndFactory::new, "EXPONENTIAL_RND");
        list.add(EXPONENTIAL_RND);

        F_DISTRIBUTION_RND = new RandomGeneratorType<>(
                FDistributionRnd.Factory.class, BetaBasedFDistributionRndFactory::new, "F_DISTRIBUTION_RND");
        list.add(F_DISTRIBUTION_RND);

        GAMMA_RND = new RandomGeneratorType<>(
                GammaRnd.Factory.class, MTTypeGammaRndFactory::new, "GAMMA_RND");
        list.add(GAMMA_RND);

        GEOMETRIC_RND = new RandomGeneratorType<>(
                GeometricRnd.Factory.class, InversionBasedGeoRndFactory::new, "GEOMETRIC_RND");
        list.add(GEOMETRIC_RND);

        GUMBEL_RND = new RandomGeneratorType<>(
                GumbelRnd.Factory.class, UniZiggGumbelRndFactory::new, "GUMBEL_RND");
        list.add(GUMBEL_RND);

        LEVY_RND = new RandomGeneratorType<>(
                LevyRnd.Factory.class, NormalBasedLevyRndFactory::new, "LEVY_RND");
        list.add(LEVY_RND);

        LOGISTIC_RND = new RandomGeneratorType<>(
                LogisticRnd.Factory.class, ZiggLogiRndFactory::new, "LOGISTIC_RND");
        list.add(LOGISTIC_RND);

        NORMAL_RND = new RandomGeneratorType<>(
                NormalRnd.Factory.class, ZiggNormalRndFactory::new, "NORMAL_RND");
        list.add(NORMAL_RND);

        POISSON_RND = new RandomGeneratorType<>(
                PoissonRnd.Factory.class, GammaHomoProcessBasedPoissonRndFactory::new, "POISSON_RND");
        list.add(POISSON_RND);

        STATIC_BETA_RND = new RandomGeneratorType<>(
                StaticBetaRnd.Factory.class, GammaBasedStaticBetaRndFactory::new, "STATIC_BETA_RND");
        list.add(STATIC_BETA_RND);

        STATIC_GAMMA_RND = new RandomGeneratorType<>(
                StaticGammaRnd.Factory.class, MTTypeStaticGammaRndFactory::new, "STATIC_GAMMA_RND");
        list.add(STATIC_GAMMA_RND);

        T_DISTRIBUTION_RND = new RandomGeneratorType<>(
                TDistributionRnd.Factory.class, NormalGammaBasedTDistRndFactory::new, "T_DISTRIBUTION_RND");
        list.add(T_DISTRIBUTION_RND);

        VOIGT_RND = new RandomGeneratorType<>(
                VoigtRnd.Factory.class, StandardImplVoigtRndFactory::new, "VOIGT_RND");
        list.add(VOIGT_RND);

        WEIBULL_RND = new RandomGeneratorType<>(
                WeibullRnd.Factory.class, GumbelBasedWeibullRndFactory::new, "WEIBULL_RND");
        list.add(WEIBULL_RND);

        values = Collections.unmodifiableList(list);
    }

    private final Class<T> factoryClass;
    private final Function<RandomGeneratorFactoryProvider, T> getter;
    private final String typeName;

    private RandomGeneratorType(Class<T> factoryClass, Function<RandomGeneratorFactoryProvider, T> getter,
            String typeName) {
        this.factoryClass = Objects.requireNonNull(factoryClass);
        this.getter = Objects.requireNonNull(getter);
        this.typeName = Objects.requireNonNull(typeName);
    }

    T cast(Object obj) {
        return this.factoryClass.cast(obj);
    }

    T get(RandomGeneratorFactoryProvider provider) {
        Objects.requireNonNull(provider);
        return this.getter.apply(provider);
    }

    /**
     * このタイプの文字列表現を返す.
     * 
     * @return タイプの文字列表現
     */
    @Override
    public String toString() {
        return this.typeName;
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

}
