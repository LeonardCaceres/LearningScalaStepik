def euclid(a: Int, b: Int): (Int, Int) = {
  if (b > a) euclid(b, a)
  else if (b == 0) (1, 0)
  else {
    val d = a / b
    val r = a % b
    val (x, y) = euclid(b, r)
    (y, x - d * y)
  }
}

euclid(7, 4)
