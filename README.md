# matsu.num.Statistics.Random

![Version](https://img.shields.io/badge/version-26.2.0-blue)
![Java](https://img.shields.io/badge/Java-17%2B-brightgreen)
![License](https://img.shields.io/badge/license-MIT-green)
[![Javadoc](https://img.shields.io/badge/docs-Javadoc-blue)](https://tchaikovsky1026.github.io/matsu.num.Statistics.Random/)
[![SemVer](https://img.shields.io/badge/SemVer-2.0.0-blue)](https://semver.org/spec/v2.0.0.html)

`matsu.num.Statistics.Random` は Java 言語向けの数値計算における, 乱数生成を扱うライブラリである.  
現在のリリースバージョンは `26.2.0` であり, Java 17 に準拠する.  
このバージョンにおいて, 次がサポートされている.

- 確率分布に従う乱数生成とそのファクトリに関するインターフェース &middot; 実装
    - 基本乱数インターフェース (依存注入に用いる)
        - `boolean`, `int`, `long`, `double`
        - 標準指数分布
        - 標準正規分布  
        (これらは, Java 標準APIの機能を利用する.)
    - 連続分布
        - 逆正弦分布
        - ベータ分布
        - 標準 Cauchy 分布
        - カイ二乗分布
        - F 分布
        - 標準ガンマ分布
        - 標準 Gumbel 分布
        - 標準 L&eacute;vy 分布
        - 標準ロジスティック分布
        - 標準 Planck 分布
        - Student の t 分布
        - Voigt 分布
        - 標準 Weibull 分布
    - 離散分布
        - 二項分布
        - カテゴリカル分布
        - 幾何分布
        - 対数級数分布
        - 負の二項分布
        - Poisson 分布
        - Yule-Simon 分布
        - ゼータ分布
- 混合分布モデルに従う乱数発生

## Documentation
- API Documentation (Javadoc):  
https://tchaikovsky1026.github.io/matsu.num.Statistics.Random/  
This site contains the public API reference generated from source code.  
The documentation is generated from the latest release.
- Technical notes and supplementary documentation are available in the `note` directory.

## History
更新履歴は history.txt を参照のこと.

## License

This project is licensed under the MIT License, see the LICENSE.txt file for details.
