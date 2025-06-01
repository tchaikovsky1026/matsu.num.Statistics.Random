/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */

/*
 * 2025.5.31
 */
package matsu.num.statistics.random.arcsin;

import matsu.num.statistics.random.ArcsineRnd;
import matsu.num.statistics.random.BaseRandom;

/**
 * 逆関数法による, 逆正弦乱数発生器.
 * 
 * @author Matsuura Y.
 * @deprecated このクラスは使用されていない
 */
@Deprecated
public final class InversionBasedArcsineRnd extends SkeletalArcsineRnd {

    /**
     * 非公開の唯一のコンストラクタ.
     */
    private InversionBasedArcsineRnd() {
        super();
    }

    @Override
    public double nextRandom(BaseRandom random) {
        double y = Math.sin(Math.PI * 0.5 * random.nextDouble());
        return y * y;
    }

    /**
     * {@link matsu.num.statistics.random.ArcsineRnd.Factory} を生成する.
     * 
     * @return 逆正弦乱数のファクトリ
     */
    public static ArcsineRnd.Factory createFactory() {
        return new ArcsineRndFactory(new InversionBasedArcsineRnd());
    }
}
