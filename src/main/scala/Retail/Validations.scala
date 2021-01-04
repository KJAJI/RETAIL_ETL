package Retail

object Validations {

  def validEmailAddressRegex(email:String):Boolean ={

    if("\\A(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)\\Z".r.findFirstIn(email).isEmpty) false else true

  }

  def validContactNumberRegex(number:String): Boolean = {
    if("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s./0-9]*$".r.findFirstIn(number).isEmpty) false else true
  }

  def isAllDigits(svalue: String) :Boolean = svalue forall Character.isDigit

  def validIDNumber(id:String):Boolean = {

    var correct = true

    if ((id.length != 13) || !isAllDigits(id)) {
       correct = false
    }
    var d = -1
    var a = 0
    var b = 0
   for( i <- 0 to 5)
   {
     a += id(2 * i).toInt
   }
   for(i <- 0 to 5)
   {
     b = b*10 + id(2*i+1).toInt
   }
   b *= 2
   var c = 0
   do {
     c += b % 10
     b = b / 10
   } while ( b > 0)
   c += a
   d = 10 - (c % 10)

   if (d == -1) {
     correct = false
   }
   correct
 }

  def isNullOrEmpty(txt: String): Boolean = {
    if(txt!=null && !txt.isEmpty) false else true
  }

}
