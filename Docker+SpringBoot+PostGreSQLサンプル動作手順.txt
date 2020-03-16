Docker+SpringBoot+PostGreSQL　サンプル


Gitから以下DockerSpringBoot資産をダウンロード
https://github.com/ZumWalt22/DockerSpringBootExample

※作成元資産は以下HPを参考に作成
※上記記事だと、DockerにアタッチしようとするとエラーになるためローカルでJarを生成
https://ameblo.jp/kazusa-g/entry-12535018096.html

１．JDKインストール
SpringBoot資産をビルドしJarファイルを生成するために必要
下記をインストール　（JAVA8でも動作確認済み）
jdk-11.0.6_windows-x64_bin

環境変数に以下を設定
Key：JAVA_HOME
VALUE：C:\Program Files\Java\jdk-11.0.6　（JDKのインストールされたパス）

２．ローカルでアプリをビルドしJarファイルを生成する

PowerShellでプロジェクトのディレクトリ配下に移動し、以下コマンドを実行
※PowerShellは管理者モードで起動する

> ./gradlew build

以下のフォルダにjarファイルが作成されていることを確認
C:\springBoot\hello\build\libs
hello-world-0.1.0.jar


３．ローカルでSpringBootサービスが立上がるか確認
PowerShellでプロジェクトのディレクトリ配下に移動し、以下コマンドを実行
プロジェクトのディレクトリ配下に移動し、以下コマンドを実行
> java -jar build/libs/hello-world-0.1.0.jar

以下のようなログが出力され、サービスが立ち上がっていることを確認
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.7.RELEASE)

2020-03-14 20:08:44.675  INFO 5848 --- [main] hello.hello.HelloApplication : Starting HelloApplication

４．ブラウザからサービスにアクセスできるかを確認
以下を実行し「Hello World from Docker」の文言が表示されることを確認
ブラウザから実行
http://localhost:8080

※コマンドラインから実行の場合
$ curl http://localhost:8080

※以下のエラーがでる場合「Dockerfile」ファイルから以下のコマンドを削除
Service 'app' failed to build: The command '/bin/sh -c mkdir /app' returned a non-zero code:
→RUN mkdir /app　はなくてもOKなので削除（WORKDIR /app　でない場合appフォルダが作成される）

５．Dockerコンポーズからサービスを立ち上げる
PowerShellでプロジェクトのディレクトリ配下に移動し、以下コマンドを実行
プロジェクトのディレクトリ配下に移動し、以下コマンドを実行
> docker-compose up -d

以下のように各サービスが立上がったログが出力されていることを確認
--------------------------------------------------
Successfully built fdcf0e6b4da2
Successfully tagged tiqwab/boot:0.0.1
Creating sb_db ... done
Creating sb    ... done
--------------------------------------------------

サービスが立上がっていることを確認
> docker-compose ps

Name               Command               State           Ports
-----------------------------------------------------------------------
sb      sh -c java $JAVA_OPTS -Dja ...   Up      0.0.0.0:8080->8080/tcp
sb_db   docker-entrypoint.sh postgres    Up      0.0.0.0:5432->5432/tcp


６．ブラウザからDockerサービスにアクセスできるかを確認

以下のURLにアクセスする
http://localhost:8080/

以下のように表示されていることを確認
--------------------------
Hello world!
Nameリスト

key	name
1	青木
2	山田
3	伊藤

--------------------------