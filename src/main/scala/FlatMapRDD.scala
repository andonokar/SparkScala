package io.github.andonokar

object FlatMapRDD extends StartSparkContext {
  val lines = sc.textFile("data/book.txt")
  val words = lines.flatMap(_.split("\\W+"))
  val lowerWords = words.map(_.toLowerCase())
  val countWords = lowerWords.map((_, 1)).reduceByKey(_+_).sortBy(_._2, ascending = false)
  countWords.collect().foreach(println)
}
