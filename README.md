# my-kinesis-consumer-scala-sample

Kinesisとamazon-kinesis-clientを使って、Kinesisを通してデータをやり取りするScalaのサンプル。

PUTしてTAILする仕組みを作った

* PUT: http://qiita.com/okuda_h/items/9fc1b271b63775ca7636#3-1
* TAIL: http://qiita.com/oshiro/items/fac2bea668fb4e8c8cfb

```sh
$ aws kinesis create-stream --stream-name kinesis-test-stream --shard-count 1
$ cd my-kinesis-consumer-scala-sample
$ activator run -DaccessKeyId=XXX -DsecretAccessKey=XXX
# ...2つ出てくるmain classの両方共実行する(順不同)
```

5秒おきにKinesis Streamにデータをputするので、都度tailしてloggingしている。
