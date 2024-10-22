package io.github.andonokar

object AmountByCustomer extends StartSparkContext {
  def transformString(row: String) = {
    val array = row.split(",")
    (array(0).toLong, array(2).toDouble)
  }

  val rdd = sc.textFile("data/customer-orders.csv")
  val transRdd = rdd.map(transformString)
  val reducedRdd = transRdd.reduceByKey(_+_).sortBy(_._2)
  reducedRdd.collect().foreach(println)
}
