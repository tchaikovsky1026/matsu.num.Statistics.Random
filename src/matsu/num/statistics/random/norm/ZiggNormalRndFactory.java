/**
 * 2024.3.16
 */
package matsu.num.statistics.random.norm;

import matsu.num.statistics.random.ExponentialRnd;
import matsu.num.statistics.random.NormalRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * このパッケージに用意された {@link NormalRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 19.0
 */
public final class ZiggNormalRndFactory implements NormalRnd.Factory {

    private final NormalRnd normalRnd;
    
    public ZiggNormalRndFactory(Exponentiation exponentiation, ExponentialRnd.Factory exponentialRndFactory) {
        super();
        
        /* NormalRndは状態を持たないのでここで生成. */
        this.normalRnd = new ZiggNormalRnd(exponentiation, exponentialRndFactory);
    }

    @Override
    public NormalRnd instance() {
        return this.normalRnd;
    }

    @Override
    public String toString() {
        return "NormalRnd.Factory";
    }
}
