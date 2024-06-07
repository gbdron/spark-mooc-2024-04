package sparkdataset
import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

class dataset {
  //1. RDD
  val sc = new SparkContext("local[*]", "RDDExample")
  val data = sc.parallelize(Seq(1,2,3,4,5))

  //2. DataFrame
  val spark = SparkSession.builder().appName("dataset").getOrCreate()
  import spark.implicits._
  val data = Seq(("test1", 1),("test2", 2),("test3", 3),("test4", 4))
  val df = data.toDF("Column1", "Column2")

  case class TestDSData(Column1: String, Columns2: Int)
  val dataForDS = Seq(TestDSData("test1", 1),TestDSData("test2", 2),TestDSData("test3", 3),TestDSData("test4", 4))
  val ds = dataForDS.toDS()

  // фильтрация данных
  val filteredDS = ds.filter(_.Column2 > 2)

  // Группировка данных
  val groupedDS = ds.groupBy("Column1").count()

  // Агрегация данных
  val maxColumn2 = ds.agg(max("Column2"))

  //сортировка данных
  val sortedDS = ds.sort($"Column2".desc)

  // обьединение DataSet
  val combineDS = ds.union(filteredDS)

  // SQL
  //1. create temporal view
  ds.createOrReplaceTempView("table1")

  //2 execute the query
  val res = spark.sql("SELECT * FROM table1 WHERE Column2>2")

  // convert DS to RDD and back
  val rdd = ds.rdd

  val newDS = spark.createDataset(rdd)(Encoders.product[TestDSData])

  //write dataset in parquet format
  ds.write.parquet("path/to...")

  //read dataset from Parquet
  val parquetDS = spark.read.parquet("path/to...")


  // encoders
  import org.apache.spark.sql.{Encoder, Encoders}
  case class Person(name: String, age: Int)

  //create encoder for this case class
  val personEncoder: Encoder[Person] = Encoders.product[Person]

  //usage
  val person:Person = Person("John", 40)
  val encoderPerson: Array[Byte] = personEncoder.toRow(person).asInstanceOf[Array[Byte]]
  val decodedPerson: Person = personEncoder.fromRow(encoderPerson)


  // use the encoders with dataset
  val data = Seq(Person("John", 40),Person("John1", 40),Person("John2", 40))
  val ds:DataSet[Person] = data.toDS()

  val filteredDS = ds.filter(_.age > 30)
  val maxAge = ds.agg(max("age"))

  val rdd = ds.rdd
  val newDS = spark.createDataset(rdd)(Encoders.product[Person])

  // users encoders
  import org.apache.spark.sql.Encoder
  import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder


  val customEncoder: Encoder[T] = ExpressionEncoder()
  val encoderData: Array[Byte] = customEncoder.toRow(data).asInstanceOf[Array[Byte]]
  // decode
  val decodeData: T = customEncoder.fromRow(encoderData)

}


import org.apache.spark.sql.{SparkSession, DataFrame}

object SparkSQLExample extends App {
  val spark = SparkSession.builder().appName("SparkSQLExample").getOrCreate
  import spark.implicits._

  //read from csv
  case class Person(name: String, age: Int)
  val data = Seq(Person("John", 40),Person("John1", 40),Person("John2", 40))
  val ds = data.toDS()

  ds.createOrReplaceTempView("people")

  val result = spark.sql("SELECT * from people where age > 30")

  result.show()
}
