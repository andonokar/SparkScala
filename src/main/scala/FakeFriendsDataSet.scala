package io.github.andonokar
import org.apache.spark.sql.functions._

object FakeFriendsDataSet extends StartSparkSession {
  import spark.implicits._
  case class Person(id: Int, name: String, age: Int, friends: Int)
  case class FriendsByAge(age: Int, friends: Double)
  val dfPeople = spark
    .read
    .format("csv")
    .option("header", "true")
    .option("inferSchema", "true")
    .load("data/fakefriends.csv")
    .as[Person]

  val finaldf = dfPeople.groupBy("age").agg(avg("friends").alias("friends")).as[FriendsByAge]
  finaldf.show()
}
