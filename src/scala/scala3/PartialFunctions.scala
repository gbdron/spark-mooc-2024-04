package scala3

object PartialFunctionsExample extends App {
  // определение частичной функции
  val evenNumberToString: PartialFunction[Any, String] = {
    case x if x.isInstanceOf[Int] && x.asInstanceOf[Int] % 2 == 0 => s"$x is even"
  }

  // проверим , где частичная функция определена
  println(evenNumberToString.isDefinedAt(2)) // true
  println(evenNumberToString.isDefinedAt(3)) // false

  //применение частичной функции
  if (evenNumberToString.isDefinedAt(4)){
    println(evenNumberToString(4))
  }

  //использование orElse для обьединения с другими частичными функциями
  val numberToString: PartialFunction[Any, String] = evenNumberToString.orElse {
    case x if x.isInstanceOf[String] => s"$x is String"
    case x => s"$x is odd"
  }

  println(numberToString(2))
  println(numberToString(3))
  println(numberToString("sdf"))
  println("____________________")
  List("asd",14, 15,3,9,"sdgfdfh").collect(numberToString).foreach(println)





}
