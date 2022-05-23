package org.fwx.spark.core.test.demo

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object MapPartitionWithIndex {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
    sparkConf.setIfMissing("spark.app.name","MapPartitionWithIndex")
    sparkConf.setIfMissing("spark.master","local[*]")

    val sc = new SparkContext(sparkConf)
    // 生成rdd数据
    val array: Array[Int] = Array(30, 60, 20, 40, 90, 81)

    val rdd: RDD[Int] = sc.parallelize(array,2)

    rdd.mapPartitionsWithIndex((index,iter)=>{
      iter.map(d=>{
        (index,d)
      })
    }).collect().foreach(println)

    sc.stop()
  }
}
