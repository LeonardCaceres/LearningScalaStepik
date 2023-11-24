////////// counts log x : x > 0;
val log: PartialFunction[Double, Double] = {
   case x if (x > 0) => Math.log(x)
}
////////// count discount
case class Jar(name: String, value: Int, price: Double)  
def discount: PartialFunction[Jar, String] =  {
  case Jar(x, y, z) if (y >= 5 && y <= 10) => x.toString + " " + (z * 0.05).toString
  case Jar(x, y, z) if (y > 10) => x.toString + " " + (z * 0.1).toString
}
