package scala1

object PartialFunction {
  val parseRange: PartialFunction[Any, Int] = {
    case x: Int if  x > 10 => x+1
    case x: Int if x < 4 => x-1
    case x: String if x.startsWith("sd") => 111
  }

  def main(args: Array[String]): Unit = {
    List("sdg", 14, 15, 3,9, "sfgsdfh").collect(parseRange).foreach(println)

  }


}