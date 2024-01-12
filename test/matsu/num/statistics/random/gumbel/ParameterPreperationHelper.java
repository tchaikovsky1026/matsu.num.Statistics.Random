/**
 * 2024.1.9
 */
package matsu.num.statistics.random.gumbel;

import org.junit.Ignore;

/**
 * Gumbel分布のUnimodal-Zigguratを実行するためのパラメータを計算するヘルパ.
 * 
 * @author Matsuura Y.
 * @version 17.5
 */
@Ignore
public final class ParameterPreperationHelper {

    public static void main(String[] args) {
        int n = 128;

        UniformZiggurat ziggurat = new UniformZigguratConstructor(n, 0d, 1).getZiggurat();
        new UniformZigguratPresentation(ziggurat).display();
    }

    private static final class UniformZigguratPresentation {
        private final UniformZiggurat ziggurat;

        UniformZigguratPresentation(UniformZiggurat ziggurat) {
            super();
            this.ziggurat = ziggurat;
        }

        void display() {
            System.out.println("int n = " + ziggurat.n + ";");
            System.out.println("double f_r = " + ziggurat.fr + ";");
            System.out.println("double r_lower = " + ziggurat.rm + ";");
            System.out.println("double r_upper = " + ziggurat.rM + ";");
            System.out.println();

            System.out.println("double v_lower = " + ziggurat.vm + ";");
            System.out.println("double v_upper = " + ziggurat.vM + ";");
            System.out.println("double v = " + ziggurat.v + ";");
            System.out.println();

            System.out.println("double[] x_lower = {");
            for (int i = 0; i < ziggurat.n; i++) {
                System.out.print(ziggurat.xmi[i] + ",");
                if (i % 4 == 3) {
                    System.out.println();
                }
            }
            System.out.println(ziggurat.xn_m);
            System.out.println("};");
            System.out.println();

            System.out.println("double[] x_upper = {");
            for (int i = 0; i < ziggurat.n; i++) {
                System.out.print(ziggurat.xMi[i] + ",");
                if (i % 4 == 3) {
                    System.out.println();
                }
            }
            System.out.println(ziggurat.xn_M);
            System.out.println("};");
            System.out.println();

            System.out.println("double[] f = {");
            for (int i = 0; i < ziggurat.n; i++) {
                System.out.print(ziggurat.fi[i] + ",");
                if (i % 4 == 3) {
                    System.out.println();
                }
            }
            System.out.println(0d);
            System.out.println("};");
            System.out.println();
        }

    }

    private static final class UniformZigguratConstructor {

        private final int n;
        private final double maxFr;
        private final double minFr;

        UniformZigguratConstructor(int n, double minFr, double maxFr) {
            super();
            this.n = n;
            this.maxFr = maxFr;
            this.minFr = minFr;
        }

        UniformZiggurat getZiggurat() {
            double upperFr = maxFr;
            double lowerFr = minFr;

            UniformZiggurat out = new UniformZiggurat(n, lowerFr);

            for (int c = 0, c_max = 10000; c < c_max; c++) {
                double fr = (upperFr + lowerFr) * 0.5;

                out = new UniformZiggurat(n, fr);
                double res = out.getRes();

                if (Double.isNaN(res)) {
                    upperFr = fr;
                } else if (res < 0) {
                    upperFr = fr;
                } else {
                    lowerFr = fr;
                }
            }

            return out;
        }

    }

    private static final class UniformZiggurat {

        private final int n;
        private final double fr;

        double rm;
        double rM;
        double vm;
        double vM;
        double v;

        double[] fi;
        double[] xmi;
        double[] xMi;

        double xn_m;
        double xn_M;

        UniformZiggurat(int n, double fr) {
            super();
            this.n = n;
            this.fr = fr;
            execute();
        }

        void execute() {
            rm = GumbelFunction.invFunc_m(fr);
            rM = GumbelFunction.invFunc_p(fr);

            vm = Math.exp(-Math.exp(-rm));
            vM = Math.expm1(Math.exp(-rM))
                    / Math.exp(Math.exp(-rM));
            v = vm + vM + (rM - rm) * fr;

            fi = new double[n];
            xmi = new double[n];
            xMi = new double[n];

            fi[n - 1] = fr;
            xmi[n - 1] = rm;
            xMi[n - 1] = rM;

            for (int i = n - 2; i >= 1; i--) {
                fi[i] = fi[i + 1] + v / (xMi[i + 1] - xmi[i + 1]);
                xmi[i] = GumbelFunction.invFunc_m(fi[i]);
                xMi[i] = GumbelFunction.invFunc_p(fi[i]);
            }

            fi[0] = 1 / Math.E;
            xmi[0] = 0d;
            xMi[0] = 0d;

            xn_m = rm - vm / fr;
            xn_M = rM + vM / fr;
        }

        /**
         * 領域N0の残差: <br>
         * (x1(M) - x1(m) (f0 - f1) - v<br>
         * の値を返す.
         * f1が構成できなかった場合はNaN
         * 
         * @return
         */
        double getRes() {
            return (xMi[1] - xmi[1]) * (fi[0] - fi[1]) - v;
        }

    }

    private static final class GumbelFunction {

        static double invFunc_p(double f) {
            return -Math.log(-lambertWp(-f));
        }

        static double invFunc_m(double f) {
            return -Math.log(-lambertWm(-f));
        }
        
        /**
         * ランベルトW関数の主枝:W_0.
         */
        private static double lambertWp(double z) {
            final double negInvE = -1 / Math.E;
            final double threshold = 1E-11;
            if (z < negInvE) {
                return Double.NaN;
            }
            double z_p_invE = z - negInvE;
            if (z_p_invE < threshold) {
                return -1 + Math.sqrt((2 * Math.E) * z_p_invE);
            }
            if (z < 10) {
                double w0 = z < 0 ? -1 + Math.sqrt((2 * Math.E) * z_p_invE) : Math.log(z + 1);
                final int iteration_z0 = 10;
                for (int i = 0; i < iteration_z0; i++) {
                    double inv1pW0 = 1 / (1 + w0);
                    double L = (w0 - z * Math.exp(-w0)) * inv1pW0;
                    w0 -= L * (1 + L * (2 + w0) * (0.5 * inv1pW0));
                }
                return w0;
            } else {
                final int iteration_z10 = 10;
                double logZ = Math.log(z);
                double w0 = logZ;
                for (int i = 0; i < iteration_z10; i++) {
                    double inv1pW0 = 1 / (1 + w0);
                    double L = (w0 - logZ + Math.log(w0)) * inv1pW0;
                    w0 -= (w0 * L) * (1 - L * (0.5 * inv1pW0));
                }
                return w0;
            }
        }

        /**
         * ランベルトW関数の分枝:W_{-1}.
         */
        private static double lambertWm(double z) {
            final double negInvE = -1 / Math.E;
            final double threshold = 1E-11;
            if (z < negInvE || z >= 0) {
                return Double.NaN;
            }
            double z_p_invE = z - negInvE;
            if (z_p_invE < threshold) {
                return -1 - Math.sqrt((2 * Math.E) * z_p_invE);
            }
            final double z_w2 = -0.270670566;
            if (z < z_w2) {
                double w0 = -1 - Math.sqrt((2 * Math.E) * z_p_invE);
                final int iteration_z0 = 10;
                for (int i = 0; i < iteration_z0; i++) {
                    double inv1pW0 = 1 / (1 + w0);
                    double L = (w0 - z * Math.exp(-w0)) * inv1pW0;
                    w0 -= L * (1 + L * (2 + w0) * (0.5 * inv1pW0));
                }
                return w0;
            } else {
                final int iteration_z10 = 10;
                double logMinusZ = Math.log(-z);
                double w0 = logMinusZ - 1;
                for (int i = 0; i < iteration_z10; i++) {
                    double inv1pW0 = 1 / (1 + w0);
                    double L = (w0 - logMinusZ + Math.log(-w0)) * inv1pW0;
                    w0 -= (w0 * L) * (1 - L * (0.5 * inv1pW0));
                }
                return w0;
            }
        }
    }
}
