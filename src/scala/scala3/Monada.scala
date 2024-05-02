package monada


object Monad {

  class Lazy[+A](v: => A) {
    private lazy val internal = v
    def map[B](f : A=> B) = ???
    def flatMap[B] = ???
  }

  object Lazy {
    def apply[A](v: => A): Lazy[A] = new Lazy(v)

  }

}