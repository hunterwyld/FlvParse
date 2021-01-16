## FlvParse

FlvParse is a desktop app which shows Flv file structures, supporting macOS/Windows/Linux. It could be used to show detailed information and binary data of various Flv tags and fields.

FlvParse是一款Flv文件分析工具，支持macOS/Windows/Linux，可以展示Flv各个包、字段的详细信息和二进制数据。

### Download
For macOS, an executable is available [here](https://github.com/hunterwyld/FlvParse/releases/download/v1.0.0/FlvParse.app.zip).

For other OS,
```
git clone https://github.com/hunterwyld/FlvParse.git
cd FlvParse
mvn clean package
java -jar target/flvparse.jar
```

> Note that JRE 1.7+ is required to run the app

### Snapshot
![snapshot](etc/snapshot.png)

> Thanks to [jar2app](https://github.com/Jorl17/jar2app) which makes it easy for converting a jar file to macOS app.