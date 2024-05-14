package monadrules

object MonadaRules {
  def sqrtF(x: Int): Option[Int] = Some(x*x)
  def incF(x:Int): Option[Int] = Some(x+1)

  //1. left
  //monad.apply(x).flatMap(f) == f(x)
  def leftUnitLaw(): Unit = {
    val x = 5
    val monad:Option[Int] = Some(x)
    val result = monad.flatMap(sqrtF) == sqrtF(x)
    println(result)
  }

  //2. right
  // monad(x).flatMap(monad.apply) == monad
  def rightUnitLaw(): Unit = {
    val x = 5
    val monad:Option[Int] = Some(x)
    val result = monad.flatMap(x=>Some(x)) == monad
    println(result)
  }
  //3
  def associationLaw():Unit = {
    val x = 5
    val monad:Option[Int] = Some(5)
    val left = monad flatMap sqrtF flatMap incF
    val right = monad flatMap(x=> sqrtF(x) flatMap incF)
    assert(left == right)
  }

  def main(args: Array[String]): Unit = {
    leftUnitLaw
    rightUnitLaw
    associationLaw
  }


}