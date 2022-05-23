package org.fwx.spark.core.test.demo

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object GroupBy {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
    sparkConf.setIfMissing("spark.app.name","GroupBy")
    sparkConf.setIfMissing("spark.master","local[*]")

    val sc = new SparkContext(sparkConf)
    // 生成rdd数据
    val array: Array[Int] = Array(30, 60, 20, 40, 90, 81)

    val rdd: RDD[Int] = sc.parallelize(array,2)

    rdd.groupBy(x=>{x}).collect().foreach(println)
    rdd.groupBy(x=>{x%3}).collect().foreach(println)
  }
}
