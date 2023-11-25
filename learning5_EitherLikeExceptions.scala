import Math.sqrt
//default
def func(x: Double, y: Double): Double = {
  (sqrt(x) + sqrt(y)) / sqrt(x + y)
}
func(4,5)
//funcE(-1, 2)  // CE
//funcE(0, 0)   // CE
//////////////////////////////////////////////////////////////
def sqrtE(x: Double): Either[String, Double] = {
  if (x < 0) Left(s"$x < 0 !") else Right(sqrt(x))
}
def divE(x: Double, y: Double): Either[String, Double] = {
  if (y == 0) Left("zero division!") else Right(x / y)
}

def funcE(x: Double, y: Double): Either[String, Double] = {
  sqrtE(x).flatMap {
    sx => sqrtE(y).flatMap{
      sy => sqrtE(x + y).flatMap(sxy => divE(sx + sy, sxy))
    }
  }
}
funcE(4,5) // == finc(4,5)
funcE(-1, 2)
funcE(0, 0)
