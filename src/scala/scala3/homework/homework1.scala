package homework

import scala.util.Random

class BallsExperiment {
    // набор шаров
    private var balls = 0 :: 0 :: 0 :: 1 :: 1 :: 1 :: Nil

    // функция вынимает случайный шар из корзины
    private def getRandomAndRemove(): Int = {
        val index = Random.nextInt(balls.length)
        val ball = balls(index)
        balls = balls.take(index) ++ balls.drop(index + 1)
        
        return ball
    }

    // показать оставшиеся шары в корзине
    def showBalls{
        println(balls.mkString(", "))
    }

    def isFirstBlackSecondWhite(): Option[Boolean] = {
        if (balls.length < 2) return None

        Some(getRandomAndRemove == 0 && getRandomAndRemove == 1)
        
    }
}

object BallsTest {
  def main(args: Array[String]): Unit = {
    val count = 10000
    val listOfExperiments: List[BallsExperiment] = List.fill(count)(new BallsExperiment)
    val countOfExperiments = listOfExperiments.map(_.isFirstBlackSecondWhite())
    val countOfPositiveExperiments: Float = countOfExperiments.count(_ == Some(true))
    println(countOfPositiveExperiments / count)

  }
}