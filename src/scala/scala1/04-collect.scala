package  scala1

object Collect extends App {
  case class Car(marke: String, model: String, year: Int)
  val cars = List(Car("Vw", "id4", 2021), "sdgdfg", 1, Car("Lexus", "UX", 2019))

  cars.collect {
    case x: Car => x.model
    case x: String => "111"
    case x: Int => x.toString
  }.foreach(x=>println(x))
}