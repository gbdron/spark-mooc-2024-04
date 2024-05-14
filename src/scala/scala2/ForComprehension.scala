package scala2.ForComprehansion

object ForComprehension {
  def main(args: Array[String]) : Unit = {
    val RGB = Seq("R", "G", "B")
    val range = Range(1, 4)
    val map  = Map("R" -> "Red", "G" -> "Green", "B"-> "Blue")
/*
    for (el <- RGB)
      println(el)

    for (el <- RGB; el1 <- range)
      println(s"$el - $el1")

    for ((key, value) <- map)
      println(s"$key - $value")
*/
    for (
      el1 <- RGB;
      el2 <- RGB;
      el3 <- RGB
    ) {
      println(s"$el1 $el2 $el3")
    }

    println("uniq")

    for (
      el1 <- RGB;
      el2 <- RGB;
      el3 <- RGB;
      if el1 != el2
      if el3 != el2 && el3 != el1
    ) {
      println(s"$el1 $el2 $el3")
    }

    case class Car(marke: String, model: String, year: Int)
    val cars = Car("VW", "Passat", 2005) :: Car("Lexus", "UX", 2019) :: Car("BMW", "i3", 2021) :: Nil
    case class Garage(name: String, index: Int)
    val garages = Garage("BMW", 1) :: Garage("Lexus", 2) :: Nil

    garages.flatMap{
      garage => cars.filter(car => car.marke == garage.name).map(car => (car.marke, car.model, garage.index))
   }.foreach(x=>println(s"${x._1} ${x._2} ${x._3}"))

    println("for comp")
    val cars2000 = for {
      car <- cars if car.year >2000
    } yield (car.marke, car.year)

    cars2000.foreach(x=>println(s"${x._1} ${x._2}"))

    val cars1: Seq[(String, String, Int)] = for {
      garage <- garages
      car <- cars
      if car.marke == garage.name
    } yield (car.marke, car.model, garage.index)

    cars1.foreach{
      case (marke, model, index) => println(s"$marke $model $index")
    }





  }

}