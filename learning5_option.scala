val string = "scala + [stepik] = love"
string.indexOf("[")
string.indexOf("-")
def IndexOf(s: String, pattern: String): Option[Int] = {
  Option(s.indexOf(pattern)).filter(_ >= 0)
}

IndexOf(string, "[")
IndexOf(string, "7")

def Brackets(s: String): Option[(Int, Int)] = {
  IndexOf(s, "[").flatMap{ opening => IndexOf(s,"]").filter(_ > opening).map(closing => (opening, closing))}
}
Brackets(string)
Brackets("sasasasa] adadaad[ dadad")

def CutBrakets(s: String) : Option[String] = {
  Brackets(s).map{case (opening, closing) => s.substring(opening + 1, closing)}
}
CutBrakets(string)

def foo(list: List[Int]): Int = {
  list.find(x => x % 3 == 0).map(answer => answer * 2).getOrElse(0)
}
