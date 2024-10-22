package io.github.andonokar
import scala.math.min

object FilterWeather extends StartSparkContext {
  def parseLine(line: String) = {
    val fields = line.split(",")
    val stationID = fields(0)
    val entryType = fields(2)
    val temperature = fields(3).toDouble * 0.1
    (stationID, entryType, temperature)
  }
  val lines = sc.textFile("data/1800.csv")
  val parsedLines = lines.map(parseLine)
  val minTemps = parsedLines.filter(_._2 == "TMIN")
  val stationTemps = minTemps.map(x => (x._1, x._3))
  val minTempsByStation = stationTemps.reduceByKey((x, y) => min(x, y))
  minTempsByStation.collect().foreach(println)
}
