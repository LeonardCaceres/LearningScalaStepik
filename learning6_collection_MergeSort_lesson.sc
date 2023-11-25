import scala.util.Random
import scala.collection.immutable.Nil

def merge(as: List[Int], bs: List[Int], acc: List[Int] = Nil): List[Int] = {
  as match {
    case List() => acc.reverse ++ bs
    case a +: restA => bs match {
      case List() => acc.reverse ++ as
      case b +: restB => if (a < b) merge(restA, bs, a :: acc)
      else merge(as, restB, b :: acc)
    }
  }
}

merge(List(2, 5, 6), List(1, 4, 9))

def MergeSort(as: List[Int]): List[Int] = as match {
  case Nil | (_ :: Nil) => as
  case _ =>
    val (left, right) = as.splitAt(as.length / 2)
    val leftSorted = MergeSort(left)
    val rightSorted = MergeSort(right)
    merge(leftSorted, rightSorted)
}
val randomList = List.fill(Random.nextInt(1000000))(Random.nextInt(1000))
val list = List(2,5,7,1,4)
MergeSort(list) == list.sorted
MergeSort(randomList) == randomList.sorted
