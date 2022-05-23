package org.fwx.spark.core.test.demo

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Sample {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
    sparkConf.setIfMissing("spark.app.name",this.getClass.getSimpleName)
    sparkConf.setIfMissing("spark.master","local[*]")

    val sc = new SparkContext(sparkConf)
    // 生成rdd数据
    val array = 1 to 30

    val rdd: RDD[Int] = sc.parallelize(array,2)

    /**
     * withReplacement:
     *  true:抽样放回，fraction取值[0,无穷)
     *  false:抽样不放回，fraction取值[0,1]
     * fraction:数据抽取比率，大概的一个比率，抽出的数据量又浮动
     */
    val resultRdd: RDD[Int] = rdd.sample(false, 0.2)
    resultRdd.collect().foreach(println)

  }
}
