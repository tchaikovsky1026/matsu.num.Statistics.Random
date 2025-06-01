/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.6.1
 */
package matsu.num.statistics.random.arcsin;

import org.junit.Ignore;

/**
 * 逆正弦乱数に Ziggurat 法を使うため, そのパラメータを計算するヘルパを提供する.
 * 
 * <p>
 * Ziggurat 法に用いる確率密度関数の定数倍は, <br>
 * {@literal f(x) = 1 / sqrt(2 - (1-x)^2)}
 * &emsp;
 * {@literal (0 < x < 1)} <br>
 * である.
 * </p>
 * 
 * @author Matsuura Y.
 */
@Ignore
final class ZigguratParameterPreparationHelper {

    private static final double HALF_SQRT_2 = Math.sqrt(2) * 0.5;

    public static void main(String[] args) {

        PartitionOptimizer optimizer = new PartitionOptimizer(128);

        ZigguratPartition partition = optimizer.optimize();
        partition.calc();

        System.out.println("residual: " + partition.residual());
        System.out.println("v: " + partition.v);
        System.out.println("x:");
        for (double x : partition.x) {
            System.out.println(x);
        }
        System.out.println();

        System.out.println("f:");
        for (double f : partition.f) {
            System.out.println(f);
        }
        System.out.println();
    }

    private static final class PartitionOptimizer {

        private final int n;

        /**
         */
        PartitionOptimizer(int n) {
            super();
            this.n = n;
        }

        ZigguratPartition optimize() {
            /*
             * 第1段階: vの範囲を見つける
             */
            double[] range = identifyRange();
            double v_min = range[0];
            double v_max = range[1];

            double epsilonR = 1E-13;
            while ((v_max - v_min) > epsilonR * v_max) {
                double v_mid = v_min + (v_max - v_min) * 0.5;

                ZigguratPartition partition = new ZigguratPartition(n, v_mid);
                partition.calc();
                double resi = partition.residual();
                if (resi >= 0d) {
                    v_min = v_mid;
                } else {
                    v_max = v_mid;
                }
            }

            return new ZigguratPartition(n, v_max);
        }

        /**
         * 探索を行うべき範囲を特定する.
         * 
         * @return [v_min, v_max]
         */
        private double[] identifyRange() {
            double v_ini = 1d;
            ZigguratPartition zigguratPartition = new ZigguratPartition(n, v_ini);
            zigguratPartition.calc();
            double resi_ini = zigguratPartition.residual();

            if (resi_ini >= 0d) {
                // vを大きい方に動かす
                double multiplier = 10d;

                double v_min = v_ini;
                double v_max = multiplier * v_min;
                while (true) {
                    ZigguratPartition partition = new ZigguratPartition(n, v_max);
                    partition.calc();
                    double resi = partition.residual();
                    if (resi <= 0d) {
                        break;
                    }

                    v_min = v_max;
                    v_max = multiplier * v_min;
                }

                return new double[] { v_min, v_max };
            } else {
                // vを小さい方に動かす
                double multiplier = 0.1d;

                double v_max = v_ini;
                double v_min = multiplier * v_max;
                while (true) {
                    ZigguratPartition partition = new ZigguratPartition(n, v_min);
                    partition.calc();
                    double resi = partition.residual();
                    if (resi >= 0d) {
                        break;
                    }

                    v_max = v_min;
                    v_min = multiplier * v_max;
                }

                return new double[] { v_min, v_max };
            }
        }
    }

    private static final class ZigguratPartition {
        private final int n;
        private final double v;

        /**
         * length = n
         */
        double[] x;

        /**
         * length = n + 1
         */
        double[] f;

        ZigguratPartition(int n, double v) {
            super();
            this.n = n;
            this.v = v;
        }

        void calc() {
            x = new double[n + 1];
            f = new double[n];

            x[n] = 1d;
            x[n - 1] = 1d;
            f[n - 1] = v;

            for (int i = n - 2; i >= 1; i--) {
                f[i] = nextF(f[i + 1], x[i + 1]);
                x[i] = invF(f[i]);
            }

            f[0] = 1d;
            x[0] = 0d;
        }

        private double nextF(double preF, double preX) {
            double nextF = preF + v / (preX + Double.MIN_NORMAL);
            return nextF >= 1d
                    ? 1d
                    : nextF;
        }

        /**
         * f は0から1でなければならない.
         */
        private double invF(double f) {
            if (f <= HALF_SQRT_2) {
                return 1d;
            }

            double out = 1d - Math.sqrt(2d - 1 / (f * f));
            return Double.isNaN(out) ? 1d : out;
        }

        /**
         * {@literal (f0 - f1) * (x1 - x0) - v} <br>
         * の値.
         */
        double residual() {
            return (f[0] - f[1]) * (x[1] - x[0]) - v;
        }
    }
}
