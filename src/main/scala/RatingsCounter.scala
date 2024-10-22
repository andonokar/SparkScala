package io.github.andonokar

object RatingsCounter extends StartSparkContext {
  private val lines = sc.textFile("data/ml-100k/u.data")
  private val ratings = lines.map(x => x.split("\t")(2))
  private val results = ratings.countByValue()
  private val sortedResults = results.toSeq.sortBy(_._1)
  sortedResults.foreach(println)
}
