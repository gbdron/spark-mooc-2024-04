package scala

object patter_matching {
  //1. match on type
  val i: Any = ???
  i match {
    case v: Int => v + 1
    case sdg: List[Int] => ???
  }

  //2. struct. matching
  trait Animal {
    def name: String
    def age: Int

    def whoIam: String = this match {
      case Dog(n,_) => s"I'm dog $n"
      case Cat(n,_) => s"I'm cat $n"
    }
  }

  case class Dog(name: String, age:Int) extends Animal
  case class Cat(name: String, age:Int) extends Animal

  // match on lit
  val dog: Animal = ???

  dog match {
    case Dog("Bim", age) => ???
    case Cat(name, age) => ???
  }

  dog match {
    case Dog(name, age) if age > 5 => ???
  }


}