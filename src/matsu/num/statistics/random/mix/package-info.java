/*
 * Copyright © 2025 Matsuura Y.
 * 
 * This software is released under the MIT License.
 * http://opensource.org/licenses/mit-license.php
 */
/**
 * 混合分布モデルに従う乱数を扱うパッケージ.
 * 
 * <p>
 * コンポーネントとなる確率モデルが複数あり, サンプルを生成する確率モデルを確率的に選択する,
 * というものが混合モデルである. <br>
 * これは, 確率質量関数あるいは確率密度関数が, コンポーネントの関数の線形結合で表現されることを表す.
 * </p>
 * 
 * <p>
 * P(<i>x</i>) = &Sigma;<sub><i>k</i> = 0</sub><sup><i>n</i> - 1</sup>
 * <i>&phi;</i><sub><i>k</i></sub>
 * P<sub><i>k</i></sub>(<i>x</i>) <br>
 * P(<i>x</i>): 混合モデルの確率質量関数／確率密度関数 <br>
 * P<sub><i>k</i></sub>(<i>x</i>): コンポーネントの確率質量関数／確率密度関数 <br>
 * <i>&phi;</i><sub><i>k</i></sub>: 重み,
 * &emsp;&Sigma;<sub><i>k</i> = 0</sub><sup><i>n</i> - 1</sup>
 * <i>&phi;</i><sub><i>k</i></sub> = 1
 * </p>
 */
package matsu.num.statistics.random.mix;
