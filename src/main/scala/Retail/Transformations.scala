package Retail

import org.apache.spark.sql.functions._
import org.apache.spark.sql.DataFrame

object Transformations{

  def addPartitionDate(df:DataFrame,columnName: String): DataFrame = {
    val addedColumnDF = df.withColumn(columnName, lit(current_date()))
    addedColumnDF
  }

  def renameColumns(df: DataFrame): DataFrame = {
    val renamedDF = df.withColumnRenamed("customer_fname","customer_first_name")
                      .withColumnRenamed("customer_lname","customer_last_name")
    renamedDF
  }

  def joinTables(customerDF:DataFrame,ordersDF:DataFrame,orderItemsDF:DataFrame,
                 productsDF:DataFrame,categoryDF:DataFrame,departmentDF:DataFrame):DataFrame = {

    val retailDF = customerDF.join(ordersDF,
      customerDF("customer_id") === ordersDF("order_customer_id"),
      "left").join(orderItemsDF,
      ordersDF("order_id") === orderItemsDF("order_item_order_id"),
      "left").join(productsDF,
      productsDF("product_id") === orderItemsDF("order_item_product_id"),
      "left").join(categoryDF,
      categoryDF("category_id") === productsDF("product_category_id"),
      "left").join(broadcast(departmentDF),
      departmentDF("department_id") === categoryDF("category_department_id"),
      "left")

    retailDF
  }

  def validEmailAddress(email:String):String ={
    if(Validations.validEmailAddressRegex(email)) email else " "
  }

  def validContactNumber(number:String): String = {
      if(Validations.validContactNumberRegex(number)) number else " "
  }

  def validIDNumber(id:String):String = {
   if(Validations.validIDNumber(id)) id else " "
 }
}
