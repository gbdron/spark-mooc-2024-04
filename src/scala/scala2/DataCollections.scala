package  scala2.DataCollections

object DataObject {
  def main(args: Array[String]): Unit = {
    val demoCollectionList: List[String] = "line 1" :: "line 2" :: "line 3" :: Nil
    val demoCollectionSet = ("line 1" :: "line 2" :: "line 3" :: "line 3" :: Nil).toSet
    val demoCollection1 = ("line 1" :: "line 2" :: "line 3" :: "line 3" :: Nil).groupBy(x=>x).map(x=>x._1)
//    demoCollection1.foreach(x => println(x))
//    println(demoCollection1.mkString(", "))
    val iter = demoCollection1.iterator
    while (iter.hasNext){
      println(iter.next)
    }

    // fold, foldleft, foldritght
    val demoCollection = 1::2::3::Nil
    //fold
    println(s"fold result : ${demoCollection.fold(0)((x,y) => x + y)}")
    // fold left
    println(s"fold left : ${demoCollection.foldLeft(0)((x,y) => x + y)}")
    // reduce
    println(s"reduce result:  ${demoCollection.reduce((x,y) => x + y)}")

    val test: List[List[Int]] = List(1,2,3,4,5) :: List(1,50,3) :: List(1,2) :: Nil
    test.filter(x => x.reduce((y,z) => y + z) > 10).foreach(x=> println(x.mkString("::")))



  }

}