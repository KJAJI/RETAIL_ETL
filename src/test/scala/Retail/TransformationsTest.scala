package Retail

import org.junit._
//import org.apache.spark.sql.{Row, SparkSession}
//import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

@Test
class TransformationsTest {

@Test
  def testValidContact1():Unit ={
  assert(Transformations.validContactNumber("123-456-7890") =="123-456-7890")
}
  @Test
  def testValidContact2():Unit ={
    assert(Transformations.validContactNumber("(123) 456-7890") =="(123) 456-7890")
  }

  @Test
  def testValidContact3():Unit ={
    assert(Transformations.validContactNumber("123 456 7890") =="123 456 7890")
  }

  @Test
  def testValidContact4():Unit ={
    assert(Transformations.validContactNumber("+278 456-7890") =="+278 456-7890")
  }

  @Test
  def testValidContact5():Unit ={
    assert(Transformations.validContactNumber("123.456.7890") =="123.456.7890")
  }

//@Test
//  def testRenaming(): Unit ={
//    val spark = SparkSession.builder().getOrCreate()
//
//    val arrayStructSchema = new StructType().add("customer_city",StringType)
//                                            .add("customer_email", StringType)
//                                            .add("customer_fname",StringType)
//                                            .add("customer_id",IntegerType)
//                                            .add("customer_lname",StringType)
//                                            .add("customer_password",StringType)
//                                            .add("customer_state",StringType)
//                                            .add("customer_street",StringType)
//                                            .add("customer_zipcode",StringType)
//
//    val df = spark.createDataFrame(spark.sparkContext.emptyRDD[Row], arrayStructSchema)
//
//    assert(!Transformations.renameColumns(df).schema.fields.contains(StructField("customer_fname", StringType)) &&
//           !Transformations.renameColumns(df).schema.fields.contains(StructField("customer_lname", StringType)))
//
//  }
}
