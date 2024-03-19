/**
 * 2024.3.22
 */
package matsu.num.statistics.random.cauchy;

import matsu.num.statistics.random.CauchyRnd;
import matsu.num.statistics.random.lib.Exponentiation;

/**
 * このパッケージに用意された {@link CauchyRnd} 実装のインスタンス生成を扱う.
 * 
 * @author Matsuura Y.
 * @version 19.0
 */
public final class ZiggCauchyRndFactory implements CauchyRnd.Factory {

    private final CauchyRnd instance;

    public ZiggCauchyRndFactory(Exponentiation exponentiation) {
        super();
        
        /* CauchyRndは状態を持たないので, ここで生成. */
        this.instance = new ZiggCauchyRnd(exponentiation);
    }

    @Override
    public CauchyRnd instance() {
        return this.instance;
    }

    @Override
    public String toString() {
        return "CauchyRnd.Factory";
    }
}
