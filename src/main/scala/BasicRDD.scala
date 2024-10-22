package io.github.andonokar
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.log4j.{Level, Logger}

object BasicRDD {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("Simple Spark App").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val numbers = List(1, 2, 3, 4, 5)
    val numbersRDD = sc.parallelize(numbers)

    val doubledNumbersRDD = numbersRDD.map(x => x * x)

    val doubledNumbers = doubledNumbersRDD.collect()
    println("Squared Numbers: " + doubledNumbers.mkString(", "))

    sc.stop()
  }
}