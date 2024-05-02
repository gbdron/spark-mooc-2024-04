package scala

object App1 extends App {
  trait Printable[A] {
    def print(value: A): String
  }

  implicit object IntPrintable extends Printable[Int] {
    override def print(value: Int): String = value.toString
  }

  implicit object StringPrintable extends Printable[String] {
    override def print(value: String): String = value
  }

  def printObject[A](value: A) (implicit printable: Printable[A]) : Unit = {
    println(printable.print(value))
  }

  printObject(42)
  printObject("sjdfbjsdhfb")


}
