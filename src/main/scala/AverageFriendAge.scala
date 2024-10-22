package io.github.andonokar

object AverageFriendAge extends StartSparkContext {
  private def parseLine(line: String) = {
    val fields = line.split(",")
    val age = fields(2).toInt
    val numFriends = fields(3).toInt
    (age, numFriends)
  }
  val lines = sc.textFile("data/fakefriends-noheader.csv")
  private val rdd = lines.map(parseLine)
  private val totalsByAge = rdd.mapValues((_, 1)).reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))
  private val averageByAge = totalsByAge.mapValues(x => x._1 / x._2)
  averageByAge.collect().sortBy(x => x._2).foreach(println)
}
