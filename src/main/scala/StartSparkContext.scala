package io.github.andonokar

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

trait StartSparkContext extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val conf = new SparkConf().setAppName("SparkScala").setMaster("local[*]")
  val sc = new SparkContext(conf)
}
