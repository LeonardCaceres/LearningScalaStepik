sealed trait Node {
  def values: List[String]
  def +:(value: String): Node
  def ++(node: Node): Node = {
    Branch(this, node)
  }
  def :+(value: String): Node = {
    this match {
      case Leaf(valueL) => Branch(Leaf(valueL), Leaf(value))
      case Branch(left, right) => Branch(left, right :+ value)
    }
  }
}

case class Branch(left: Node, right: Node) extends Node {
  def values: List[String] = left.values ++ right.values
  def +:(value: String) = Branch(value +: left, right)
}
case class Leaf(value: String) extends Node {
  def values: List[String] = List(value)
  def +:(value: String) = Branch(Leaf(value), this)
}

val tree = Branch(Branch(Leaf("one"),Leaf("two")),Leaf("three"))
tree.values
val tree2 = "zero" +: tree
tree2.values
(tree ++ tree2 :+ "four").values
