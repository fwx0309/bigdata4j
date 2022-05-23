package org.fwx.spark.core.test.demo

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 从scala集合中读取数据
 */
object DataSourceArray {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
    sparkConf.setIfMissing("spark.app.name","DataSourceArray")
    sparkConf.setIfMissing("spark.master","local[*]")

    val sc = new SparkContext(sparkConf)

    // 生成rdd数据
    val array: Array[Int] = Array(30, 60, 20, 40, 90, 81)
    // parallelize
//    val rdd: RDD[Int] = sc.parallelize(array,2)
    // makeRDD
    val rdd: RDD[Int] = sc.makeRDD(array, 2)

    // 转换算子

    // 行动算子
    val resultRdd: Array[Int] = rdd.collect()

    // 打印结果
    resultRdd.foreach(println(_))

    sc.stop()
  }
}
