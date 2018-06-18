AspectJ-sample
================

AspectJ で log を出すためのサンプル


Table of Contents
-----------------

* [Description](#description)
* [Requirements](#requirements)
* [Usage](#usage)
* [Install](#install)
* [Contributing](#contributing)
* [Support and Migration](#support-and-migration)
* [License](#license)

Description
-----------

JavaでAOPをやってみる  
具体的には、 log 出力を共通化するために AspectJ を使ってみる

Requirements
------------

このプロジェクトを実行するには以下が必要です:

* [JDK][java] 1.8+
* [Gradle][gradle] 4.8
* [gradle-aspectj][gradle-aspectj] 2.0



Usage
-----

```sh
gradle test
```

でテストを動かした際に AspectJ を利用したクラスがログを出力するのを確認できます

Install
-------

```sh
git clone https://github.com/eveoh/gradle-aspectj.git
```

Contributing
------------

修正が必要であればプルリクエストを切ってください
プルリク前に以下が実行出来る事を確認してください

```sh
gradle test
```

Support and Migration
---------------------

None

License
-------

MIT

[gradle]: https://gradle.org
[gradle-aspectj]: https://github.com/eveoh/gradle-aspectj
[java]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html