package org.fwx.spark.core.test

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema
import org.apache.spark.sql.types.{DataTypes, StringType, StructField}
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
object GroupTest2 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
    sparkConf.setIfMissing("spark.app.name","GroupBy")
    sparkConf.setIfMissing("spark.master","local[*]")

    val sc = new SparkContext(sparkConf)
    // 生成rdd数据
    val array: Array[(String, String, String)] = Array(("30", "zhangsan","18"), ("30", "lisi","19"), ("20", "wangwu","20"), ("40", "zhaoliu","21"), ("90", "tianqi","22"), ("30", "zhangsan1","23"))

    val value:RDD[(String, String, String)] = sc.parallelize(array, 2)

    val row:RDD[GenericRowWithSchema] = value.map(d => {
      val types = new util.ArrayList[StructField]()
      types.add(DataTypes.createStructField("id", StringType, true))
      types.add(DataTypes.createStructField("name", StringType, true))
      types.add(DataTypes.createStructField("age", StringType, true))
      val structType = DataTypes.createStructType(types)

      val fwx = structType.fieldIndex("fwx")
      println(fwx)

      println("**********:"+types)

      new GenericRowWithSchema(Array(d._1, d._2,d._3), structType)
    })


    row.map(r=>{
      val key = r.getAs[String]("id")
      println("----"+key)
      (key,r)
    }).groupByKey().collect().foreach(g=>{
      val value1 = g._2

      val rowsList = new util.ArrayList[Row]()
      for (elem <- value1) {
        rowsList.add(elem)
      }

      Collections.sort(rowsList,new Comparator[Row] {
        override def compare(o1: Row, o2: Row): Int = {
          val age1 = o1.getAs[String]("age")
          val age2 = o2.getAs[String]("age")
          -age1.compareTo(age2)
        }
      })

      println("======="+rowsList)
    })
  }

}
