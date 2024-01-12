/**
 * 2024.1.9
 */
package matsu.num.statistics.random;

import matsu.num.statistics.random.voigt.VoigtRndFactory;

/**
 * <p>
 * Voigt分布に従う乱数発生器を扱う.
 * </p>
 * 
 * <p>
 * Voigt分布の確率密度関数 P(<i>x</i>) は次のとおりである
 * (<i>&sigma;</i>, <i>&gamma;</i> はパラメータ). <br>
 * 
 * P(<i>x</i>) &prop;
 * &int;<sub>-&infin;</sub><sup>&infin;</sup> d<i>t</i>
 * exp[-<i>t</i><sup>2</sup> / (2<i>&sigma;</i><sup>2</sup>)]
 * / [<i>&gamma;</i><sup>2</sup> + (<i>x</i> - <i>t</i>)<sup>2</sup>]
 * </p>
 * 
 * <p>
 * この {@linkplain VoigtRnd} インターフェースでは,
 * 2個のパラメータ (<i>&sigma;</i>, <i>&gamma;</i>)
 * を単一パラメータ <i>&alpha;</i> で代表して扱う. <br>
 * 
 * <i>&alpha;</i> を0以上1以下として,
 * (<i>&sigma;</i>, <i>&gamma;</i>) を次のように定める.
 * </p>
 * 
 * <ul>
 * <li>
 * <i>&sigma;</i> = 1 - <i>&alpha;</i>
 * </li>
 * <li>
 * <i>&gamma;</i> = <i>&alpha;</i>
 * </li>
 * </ul>
 * 
 * <p>
 * 一般の (<i>&sigma;</i>, <i>&gamma;</i>) についての乱数を得たい場合は, <br>
 * <i>&alpha;</i> = <i>&gamma;</i> / (<i>&sigma;</i> + <i>&gamma;</i>) <br>
 * と定め, 生成された乱数を (<i>&sigma;</i> + <i>&gamma;</i>) 倍すればよい.
 * </p>
 * 
 * @author Matsuura Y.
 * @version 17.4
 */
public interface VoigtRnd extends FloatingRandomGenerator {

    /**
     * <p>
     * このインスタンスのパラメータ <i>&alpha;</i> の値を返す.
     * </p>
     * 
     * @return パラメータ <i>&alpha;</i>
     */
    public abstract double alpha();

    /**
     * <p>
     * 指定したパラメータ <i>&alpha;</i> を持つVoigt分布乱数発生器を返す.
     * </p>
     * 
     * @param alpha パラメータ
     * @return パラメータ <i>&alpha;</i> のVoigt分布乱数発生器
     * @throws IllegalArgumentException 0 &le; <i>&alpha;</i> &le; 1 でない場合
     */
    public static VoigtRnd instanceOf(double alpha) {
        return VoigtRndFactory.instanceOf(alpha);
    }

}
