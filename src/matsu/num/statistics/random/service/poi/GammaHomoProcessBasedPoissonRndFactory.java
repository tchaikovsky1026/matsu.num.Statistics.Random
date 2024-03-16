/**
 * 2024.2.23
 */
package matsu.num.statistics.random.service.poi;

import matsu.num.statistics.random.GammaRnd;
import matsu.num.statistics.random.PoissonRnd;
import matsu.num.statistics.random.service.Exponentiation;
import matsu.num.statistics.random.service.RandomGeneratorFactoryProvider;
import matsu.num.statistics.random.service.RandomGeneratorType;

/**
 * ガンマ分布乱数発生器を利用した, Poisson分布に従う乱数発生器のファクトリ.
 * 
 * @author Matsuura Y.
 * @version 18.2
 */
public final class GammaHomoProcessBasedPoissonRndFactory implements PoissonRnd.Factory {

    /**
     * 形状パラメータが1, 2, 4, ... のガンマ乱数生成器.
     */
    private final GammaRnd[] gammaRnds;

    private final Exponentiation exponentiation;

    public GammaHomoProcessBasedPoissonRndFactory(RandomGeneratorFactoryProvider provider) {
        super();

        this.exponentiation = provider.lib().exponentiation();
        this.gammaRnds = new GammaRnd[GammaHomoProcessBasedPoissonRnd.GAMMA_RND_BIT + 1];
        GammaRnd.Factory factory = provider.get(RandomGeneratorType.GAMMA_RND);
        int k = 1;
        for (int i = 0; i < GammaHomoProcessBasedPoissonRnd.GAMMA_RND_BIT + 1; i++) {
            gammaRnds[i] = factory.instanceOf(k);
            k = k * 2;
        }
    }

    @Override
    public boolean acceptsParameter(double lambda) {
        return PoissonRnd.LOWER_LIMIT_LAMBDA <= lambda
                && lambda <= PoissonRnd.UPPER_LIMIT_LAMBDA;
    }

    @Override
    public PoissonRnd instanceOf(double lambda) {
        if (!this.acceptsParameter(lambda)) {
            throw new IllegalArgumentException(String.format("パラメータ不正:lambda=%s", lambda));
        }
        return new GammaHomoProcessBasedPoissonRnd(lambda, this.gammaRnds, this.exponentiation);
    }

    @Override
    public String toString() {
        return "PoissonRnd.Factory";
    }
}
