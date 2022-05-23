package org.fwx.spark.core.test

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
    sparkConf.setAppName("WordCount")
    sparkConf.setIfMissing("spark.master","local[*]")

    // 数据输入路径
    val inputPath = args(0)

    val sc = new SparkContext(sparkConf)
    val rdd: RDD[String] = sc.textFile(inputPath)

    val partitioner: Option[Partitioner] = rdd.partitioner
    println(partitioner)

    val resultRdd = rdd.flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
    resultRdd.collect().foreach(println(_))

    sc.stop()
  }
}
