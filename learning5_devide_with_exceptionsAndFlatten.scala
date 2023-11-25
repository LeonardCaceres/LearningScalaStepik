//flatten - delete None elements
//def flatten(options: List[Option[Int]]): List[Int] = 
//  options.collect {
//    case Some(x) => x
//  }

//Реализуйте операцию деления divide(p: (Int, Int))(q: (Int, Int)): Either[String, (Int, Int)], возвращающую результат деления p на q или текст ошибки.
//Проверьте корректность входных данных, если входные дроби неправильные, верните ошибку Left("Invalid input"). Если делитель нулевой, верните Left("Zero divisor").
//Если в результате получилась неправильная дробь, ошибка Left("Improper result").
import Math.abs
def divide(p1: (Int, Int))(q1: (Int, Int)): Either[String, (Int, Int)] = {
  var count_minus = 0
  if (p1._1 * p1._2 * q1._1 * q1._2 < 0) {
    count_minus = 1
  }
  var p = (abs(p1._1), abs(p1._2))
  var q = (abs(q1._1), abs(q1._2))
  if (p._2 == 0 || q._2 == 0 || q._1 == 0) Left("Zero divisor")
  else if (p._1 >= p._2 || q._1 >= q._2) Left("Invalid input") else {
    val gcdP = BigInt(p._1).gcd(p._2).toInt
    val newP: (Int, Int) = (p._1 / gcdP, p._2 / gcdP)
    val gcdQ = BigInt(q._1).gcd(q._2).toInt
    val newQ: (Int, Int) = (q._1 / gcdQ, q._2 / gcdQ)
    val answer = (newP._1 * newQ._2, newP._2 * newQ._1)
//    println(newP._1, newP._2)
//    println(newQ._1, newQ._2)
//    println(answer._1, answer._2)
    val gcdAns = BigInt(answer._1).gcd(answer._2).toInt
    val newanswer: (Int, Int) = (answer._1 / gcdAns, answer._2 / gcdAns)
    if (newanswer._1 >= newanswer._2) Left("Improper result") else
      if (count_minus == 0) Right(newanswer._1, newanswer._2) else Right(-newanswer._1, newanswer._2)
  }
}
