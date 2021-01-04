package Retail

import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession
//import org.apache.spark.sql.types.StructField

object Retail_ETL {

  def main(args:Array[String]): Unit = {

    //<editor-fold desc="Input Paths">

    val productsPath = args(0)
    val ordersPath = args(1)
    val orderItemsPath = args(2)
    val departmentPath = args(3)
    val customerPath = args(4)
    val categoryPath = args(5)
    val outputPath = args(6)
//    val db_name = args(7)
//    val table_name = args(8)
//    val warehouseLocation = args(9)

    //</editor-fold>

    //<editor-fold desc="Create Spark Session">

    val spark = SparkSession.builder
      .appName("Retail ETL Application")
      .master("local[*]")
      .enableHiveSupport()
//      .config("spark.sql.warehouse.dir", warehouseLocation)
      .getOrCreate

    //</editor-fold>

    //<editor-fold desc = "Create DataFrames">

    var customerDF = spark.emptyDataFrame
    var productsDF = spark.emptyDataFrame
    var ordersDF = spark.emptyDataFrame
    var orderItemsDF = spark.emptyDataFrame
    var departmentDF = spark.emptyDataFrame
    var categoryDF = spark.emptyDataFrame

    val thread = new Thread {
      override def run() {
        productsDF = spark.read.format("csv")
          .option("header", "true")
          .load(productsPath)

        ordersDF = spark.read.format("csv")
          .option("header", "true")
          .load(ordersPath)

        orderItemsDF = spark.read.format("json")
          .option("header", "true")
          .load(orderItemsPath)

        departmentDF = spark.read.format("json")
          .option("header", "true")
          .load(departmentPath)

        customerDF = spark.read.format("json")
          .option("header", "true")
          .load(customerPath)

        categoryDF = spark.read.format("json")
          .option("header", "true")
          .load(categoryPath)
      }
    }
    thread.start()
    thread.join()

    //</editor-fold>

    //<editor-fold desc="Join DataFrames">

    val retailDF = Transformations.joinTables(Transformations.renameColumns(customerDF),ordersDF,
                                              orderItemsDF, productsDF,categoryDF,departmentDF)

    //</editor-fold>

    //<editor-fold desc="Add Column">

    val retailFinalDF = retailDF.withColumn("partition_date", lit(current_date()))
    retailFinalDF.printSchema
    retailFinalDF.show

    //</editor-fold>

    //<editor-fold desc="Write Output File">

    retailFinalDF.coalesce(2).write
                .option("header","true")
                .mode("overwrite")
                .csv(outputPath)

    //</editor-fold>

    //<editor-fold desc="Hive Tables">

//    import java.util
//    import java.util.stream.Collectors
//
//    val my_schema = retailFinalDF.schema
//    val columns = util.Arrays.stream(my_schema.fields)
//                             .map((field: StructField) => field.name + " " + field.dataType.typeName)
//                             .collect(Collectors.joining(","))
//
//    if(spark.catalog.tableExists(db_name,table_name))
//    {
//      spark.sql(s"MSCK REPAIR TABLE '$table_name'")
//      spark.sql(s"REFRESH TABLE '$table_name'")
//
//    }else{
//
//      spark.sql(s"CREATE EXTERNAL TABLE '$table_name'('$columns') PARTITIONED BY partition_date STORED AS PARQUET LOCATION '$outputPath'")
//    }
    //</editor-fold>

  }

}