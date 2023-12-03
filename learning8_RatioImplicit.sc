import Ordering.Implicits._

final case class Ratio private[Ratio](num: BigInt, den: BigInt)

object Ratio {
  def make(num: BigInt, den: BigInt): Ratio = {
    val gcd = num.gcd(den)
    Ratio(num / gcd, den / gcd)
  }
  implicit val ordering: Ordering[Ratio] = new Ordering[Ratio] {
    override def compare(x: Ratio, y: Ratio): Int = {
      Ordering[BigInt].compare(x.num * y.den, x.den * y.num)
    }
  }
}

implicit class RatioOps[T](val num: T) {
  def \\(den: BigInt)(implicit f: T => BigInt): Ratio = Ratio.make(num, den)
}

Ratio.make(2,4)

BigInt(2) \\ 4

(2 \\ 4) < (3 \\ 5)

val list = List(1 \\ 2, 2 \\ 5, 3\\7)
