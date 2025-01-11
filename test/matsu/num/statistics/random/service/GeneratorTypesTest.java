/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.service;

import static matsu.num.statistics.random.service.GeneratorTypes.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

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

/**
 * {@linkplain RandomGeneratorFactoryProvider} のテスト.
 * 
 * @author Matsuura Y.
 */
@RunWith(Enclosed.class)
public class GeneratorTypesTest {

    public static final Class<?> TEST_CLASS = GeneratorTypes.class;

    @RunWith(Theories.class)
    public static class 戻り値型のテスト {

        private RandomGeneratorFactoryProvider provider;

        @DataPoints
        public static RandomGeneratorType<?>[] TYPES;

        public static Map<RandomGeneratorType<?>, Class<?>> MAP;

        @BeforeClass
        public static void beforeAll_detaを準備する() {
            MAP = new HashMap<>();

            MAP.put(BETA_RND, BetaRnd.Factory.class);
            MAP.put(CATEGORICAL_RND, CategoricalRnd.Factory.class);
            MAP.put(CAUCHY_RND, CauchyRnd.Factory.class);
            MAP.put(CHI_SQUARED_RND, ChiSquaredRnd.Factory.class);
            MAP.put(EXPONENTIAL_RND, ExponentialRnd.Factory.class);
            MAP.put(F_DISTRIBUTION_RND, FDistributionRnd.Factory.class);
            MAP.put(GAMMA_RND, GammaRnd.Factory.class);
            MAP.put(GEOMETRIC_RND, GeometricRnd.Factory.class);
            MAP.put(GUMBEL_RND, GumbelRnd.Factory.class);
            MAP.put(LEVY_RND, LevyRnd.Factory.class);
            MAP.put(LOGISTIC_RND, LogisticRnd.Factory.class);
            MAP.put(NORMAL_RND, NormalRnd.Factory.class);
            MAP.put(POISSON_RND, PoissonRnd.Factory.class);
            MAP.put(STATIC_BETA_RND, StaticBetaRnd.Factory.class);
            MAP.put(STATIC_GAMMA_RND, StaticGammaRnd.Factory.class);
            MAP.put(T_DISTRIBUTION_RND, TDistributionRnd.Factory.class);
            MAP.put(VOIGT_RND, VoigtRnd.Factory.class);
            MAP.put(WEIBULL_RND, WeibullRnd.Factory.class);

            TYPES = MAP.keySet().toArray(new RandomGeneratorType[0]);
        }

        @Before
        public void before_プロバイダを準備する() {
            this.provider = RandomGeneratorFactoryProvider.byDefaultLib();
        }

        @Theory
        public void test_キャストできるかを検証(RandomGeneratorType<?> type) {
            try {
                Object factory = provider.get(type);
                MAP.get(type).cast(factory);
            } catch (ClassCastException e) {
                throw new AssertionError("戻り値が不正:" + type);
            }
        }
    }

    public static class 複数回の戻り値のテスト {

        private RandomGeneratorFactoryProvider provider;

        @Before
        public void before_プロバイダを準備する() {
            this.provider = RandomGeneratorFactoryProvider.byDefaultLib();
        }

        @Test
        public void test_複数回のファクトリの取得では同一インスタンスを返す() {
            /*
             * このテストは実装の詳細に依存している
             */
            assertThat(provider.get(GAMMA_RND) == provider.get(GAMMA_RND), is(true));
        }
    }

    public static class ファクトリ生成のテスト {

        @Test
        public void test_ファクトリが生成できることを検証する() throws InterruptedException, ExecutionException {
            final RandomGeneratorFactoryProvider provider =
                    RandomGeneratorFactoryProvider.byDefaultLib();

            //網羅的にファクトリの生成を行う
            //ファクトリに循環依存がある場合, タイムアウトする.

            long timeoutAsSeconds = 5;

            ExecutorService service = Executors.newCachedThreadPool();
            Future<?> submit = service.submit(() -> GeneratorTypes.values().forEach(provider::get));
            service.shutdown();
            try {
                submit.get(timeoutAsSeconds, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                throw new AssertionError(String.format("タイムアウトしました: %s秒", timeoutAsSeconds));
            }
        }
    }
}
