import java.nio.ByteBuffer
import java.util.Calendar

import com.amazonaws.auth.{AWSCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.internal.StaticCredentialsProvider
import com.amazonaws.regions.{Region, Regions}
import com.amazonaws.services.kinesis.model.{PutRecordRequest, PutRecordResult}
import com.amazonaws.services.kinesis.{AmazonKinesis, AmazonKinesisClient}
import org.apache.commons.lang.RandomStringUtils

object PutMain {
  val accessKeyId = System.getProperty("accessKeyId")
  val secretAccessKey = System.getProperty("secretAccessKey")

  val appName = "kinesis-test-app"
  val streamName = "kinesis-test-stream"

  val initialPosition = "LATEST"
  val region = "ap-northeast-1"

  def main(args: Array[String]): Unit = {
    val credentialsProvider: AWSCredentialsProvider = new StaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretAccessKey))

    val kinesis: AmazonKinesis = new AmazonKinesisClient(credentialsProvider)
    kinesis.setRegion(Region.getRegion(Regions.AP_NORTHEAST_1))

    while (true) {
      val key = RandomStringUtils.randomAlphanumeric(10)
      val data = "KEY_" + Calendar.getInstance().getTime().getTime() + ":" + key

      val request: PutRecordRequest = new PutRecordRequest()
      request.setStreamName(streamName)
      request.setData(ByteBuffer.wrap(data.getBytes("UTF-8")))
      request.setPartitionKey(key)
      val putRecord: PutRecordResult = kinesis.putRecord(request)

      println("key:{} ,record:{}", key, data, putRecord)
      println("--------")
      Thread.sleep(5000)

    }
  }
}



