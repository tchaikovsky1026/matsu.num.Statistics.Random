/*
 * Copyright © 2024 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
package matsu.num.statistics.random.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * {@linkplain RandomGeneratorFactoryProvider} のテスト.
 */
@RunWith(Enclosed.class)
public class GeneratorTypesTest {

    public static final Class<?> TEST_CLASS = GeneratorTypes.class;

    @RunWith(Theories.class)
    public static class 複数回の戻り値のテスト {

        @DataPoints
        public static Collection<RandomGeneratorType<?>> types = values();

        private static RandomGeneratorFactoryProvider provider;

        @BeforeClass
        public static void before_プロバイダを準備する() {
            provider = RandomGeneratorFactoryProvider.byDefaultLib();
        }

        @Theory
        public void test_複数回のファクトリの取得では同一インスタンスを返す(RandomGeneratorType<?> type) {
            /*
             * このテストは実装の詳細に依存している
             */
            assertThat(provider.get(type) == provider.get(type), is(true));
        }
    }

    @RunWith(Theories.class)
    public static class ファクトリ生成のテスト {

        @DataPoints
        public static Collection<RandomGeneratorType<?>> types = values();

        private static RandomGeneratorFactoryProvider provider;

        @BeforeClass
        public static void before_プロバイダを準備する() {
            provider = RandomGeneratorFactoryProvider.byDefaultLib();
        }

        @Theory
        public void test_ファクトリが生成できることを検証する(RandomGeneratorType<?> type)
                throws InterruptedException, ExecutionException {

            //網羅的にファクトリの生成を行う
            //ファクトリに循環依存がある場合, タイムアウトする.

            long timeoutAsSeconds = 5;

            ExecutorService service = Executors.newCachedThreadPool();
            Future<?> submit = service.submit(() -> provider.get(type));
            service.shutdown();
            try {
                submit.get(timeoutAsSeconds, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                throw new AssertionError(String.format("タイムアウトしました: %s秒", timeoutAsSeconds));
            }
        }
    }

    public static class 列挙表示 {

        @Test
        public void test_表示() {
            System.out.println(TEST_CLASS.getName());
            for (Object o : values()) {
                System.out.println(o);
            }
            System.out.println();
        }
    }

    /**
     * {@link GeneratorTypes} のすべての公開定数を含んだコレクションを返す.
     * 
     * @return コレクション
     */
    static Collection<RandomGeneratorType<?>> values() {
        List<RandomGeneratorType<?>> constantFieldList = new ArrayList<>();

        @SuppressWarnings("rawtypes")
        Class<RandomGeneratorType> clazz = RandomGeneratorType.class;

        for (Field f : GeneratorTypes.class.getFields()) {
            if ((f.getModifiers() & Modifier.STATIC) == 0) {
                continue;
            }
            try {
                constantFieldList.add(clazz.cast(f.get(null)));
            } catch (IllegalAccessException | ClassCastException ignore) {
                //無関係なフィールドなら無視する
            }
        }

        return constantFieldList;
    }
}
