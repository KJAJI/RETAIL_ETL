package Retail

object Arguments {

    def isArgValid(args: Array[String]): Boolean ={
      if (args.length < 7) false
      else true
    }

}
