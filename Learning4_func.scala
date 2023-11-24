//def sumEven(n: Int): Long = {
//  def go(i: Int, acc: Long): Long =
//    if(i > n) acc
//    else go(i + 2, acc + i)
//
//  go(2, acc = 0)
//}
//sumEven(100)
//sumEven(10000)
//sumEven(100000)

//def fibs(num: Int) : Int = {
//  if (num == 1) 1
//  else if (num == 2) 1
//  else fibs(num - 1) + fibs(num - 2)
//}

//def sendGift(currentAmount: Int, gift: Int) = {
//  if (currentAmount >= 500)
//    currentAmount + gift
//  else
//    currentAmount
//}
//
//val accountAmounts = List(100, 200, 500, 300, 700)


//val add1 = (_: Int) + 1
//
//val calc42 = (f: Int => Int) => f(v1=42 )
//calc42(add1)
//calc42(_ + 5)
//
//def sumTo(x: Int): Int = if(x == 0) 0 else x + sumTo(x - 1)
//
//calc42(sumTo)
////calc42(x: Int => if (x == 0) 0 else x + _)
//
//def fix(f: (=> Int => Int) => Int => Int): Int => Int = f(fix(f))
//
//fix(rec => x => if(x == 0) 0 else x + rec(x - 1))(v1=7)
//
//calc42(fix(rec => x => if(x == 0) 0 else x + rec(x - 1)))
//
//val mul3 = 3*(_: Double)
//val pow2 = (x: Double) => x*x
//
////println((mul3 compose[Double] pow2 _)(5))
//
//println((pow2.andThen[Double] _ )(mul3)(5))
//
//println((mul3 andThen pow2)(5))
//
//println(pow2.compose(mul3)(5))

//def calc42M[A]: (Int => A) => A = f => f(v1=42000000)
//calc42M(i => s"number is $i")
//
//def tailRec[A, B](iter: A=>A,
//                  comb: (B, A) => B,
//                  cond: A => Boolean)(start: A, init: B): B = {
//  def go(x: A, acc: B): B = {
//    if (cond(x)) go(iter(x), comb(acc,x)) else acc
//  }
//  go(start, init)
//}
//
//calc42M(n => tailRec[Int, Long](_ - 1, _ + _, _ >= 0)(n, 0))

//import scala.annotation.tailrec
//@tailrec
//def fibs(n: Int, f1: BigInt = 0, f2: BigInt = 1): BigInt = {
//  if (n == 1)
//    f2
//  else {
//    fibs(n-1,f2,f1+f2)
//  }
//}
//println(fibs(100))
//
//def middle[T](data: Iterable[T]): T = {
//  val sz: Int = data.size
//  if (sz % 2 == 0 && data.nonEmpty) {
//    val tmp  = data.splitAt(sz / 2)
//    tmp._2.head
//  } else if (sz % 2 != 0 && data.nonEmpty) {
//    val tmp = data.splitAt((sz - 1) / 2)
//
//    tmp._2.head
//  } else {
//    None.asInstanceOf[T]
//  }
//}
//
//println(middle("Scala"))
//println(middle(Seq(1, 7, 5, 9)))
//require(middle("Scala") == 'a')
//require(middle(Seq(1, 7, 5, 9)) == 5)
