package scala.scala1

class MyThread extends  Thread {
  override def run(): Unit =
    {
      println("thread" + Thread.currentThread().getName() + " is running")
    }
}

object Test{
  def main(args: Array[String]): Unit = {
    for (x<- 1 to 5){
      var th = new MyThread()
      th.setName(x.toString())
      th.start()
    }
  }
}