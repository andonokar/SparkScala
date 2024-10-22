package io.github.andonokar
import org.apache.spark.sql.functions.col

object DataSetAPI extends StartSparkSession {
  import spark.implicits._
  case class Person(id: Int, name: String, age: Int, friends: Int)
  case class Age(age: Int)
  case class Name(name: String)
  val dfPeople = spark
    .read
    .format("csv")
    .option("header", "true")
    .option("inferSchema", "true")
    .load("data/fakefriends.csv")
    .as[Person]
  dfPeople.printSchema()
  val select = dfPeople.map(person => Name(person.name))
  select.explain("cost")
  val select2 = dfPeople.select($"name").as[Name]
  select2.explain("cost")
  val filter = dfPeople.filter(_.age <= 21)
  filter.explain()
  val grouped = dfPeople.groupBy("age").count()
  grouped.explain("cost")
  val grouped2 = dfPeople.groupByKey(person => Age(person.age)).count()
  grouped2.explain("cost")
  val select3 = dfPeople.select(dfPeople.col("name"), (dfPeople("age") + 10).alias("age"))
  select3.show()

}
