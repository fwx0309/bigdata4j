package org.fwx.spark.core.test

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema
import org.apache.spark.sql.types.{DataType, DataTypes, StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}

import java.util
import java.util.{Collections, Comparator, Random}

/**
 * @ClassName GroupTest 
 * @Description TODO
 * @Author Fwx
 * @Date 2024/11/19 8:59
 * @Version 1.0
 */
object GroupTest {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
    sparkConf.setIfMissing("spark.app.name","GroupBy")
    sparkConf.setIfMissing("spark.master","local[*]")

    val sc = new SparkContext(sparkConf)
    // 生成rdd数据
    val array: Array[(String, String)] = Array(("30", "zhangsan"), ("60", "lisi"), ("20", "wangwu"), ("40", "zhaoliu"), ("90", "tianqi"), ("30", "zhangsan1"))

    val value:RDD[(String, String)] = sc.parallelize(array, 2)

    val row:RDD[GenericRowWithSchema] = value.map(d => {
      val types = new util.ArrayList[StructField]()
      types.add(DataTypes.createStructField("id", StringType, true))
      types.add(DataTypes.createStructField("name", StringType, true))
      val structType = DataTypes.createStructType(types)

      types.add(DataTypes.createStructField("age", StringType, true))

      println("**********:"+types)

      val random = new Random()
      val i = random.nextInt(80)

      new GenericRowWithSchema(Array(d._1, d._2,i), structType)
    })



    //row.foreach(println)

    row.map(r=>{
      val key = r.getAs[String]("id")
      println("----"+key)
      (key,r)
    }).groupByKey().collect().foreach(println)
  }

}
