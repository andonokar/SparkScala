package io.github.andonokar

object DataSetSQL extends StartSparkSession {
  import spark.implicits._
  case class Person(id: Int, name: String, age: Int, friends: Int)
  val dfPeople = spark
    .read
    .format("csv")
    .option("header", "true")
    .option("inferSchema", "true")
    .load("data/fakefriends.csv")
    .as[Person]
  dfPeople.createOrReplaceTempView("people")
  val teenagers = spark.sql("SELECT * FROM people WHERE age >= 13 AND age <= 19")
  teenagers.collect().foreach(println)
}
