package scala1

object Implicits extends App {

  object implicit_conversions {
    implicit  class StringOps(str: String) {
      def trimToOption: Option[String] =
        Option(str).map(_.trim).filter(_.nonEmpty)
    }
  }

  import implicit_conversions._
  "ajhsdgjashdfgjsdf".trimToOption

}
