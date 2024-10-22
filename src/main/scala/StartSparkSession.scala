package io.github.andonokar

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

trait StartSparkSession extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder()
    .appName("SparkScala")
    .master("local[*]")
    .getOrCreate()
}
