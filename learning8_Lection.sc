final case class MultiMap[Key, Value](items: Map[Key, List[Value]]) {
  override def toString: String =
    s"Multimap(${
      items.map { case (key, values) =>
        s"$key -> ${values.mkString(", ")}"
      }.mkString("; ")})"

  def add(key: Key, value: Value): MultiMap[Key, Value] =
    MultiMap(items + (key -> (items.get(key) match {
      case Some(values) => value :: values
      case None => List(value)
    })))

  def map[B](f: Value => B): MultiMap[Key, B] = {MultiMap(items.view.mapValues(_.map(f)).toMap)}

}

object MultiMap {
  def apply[Key, Value](items: (Key, Value)*): MultiMap[Key, Value] = {
    MultiMap(items.groupBy(_._1).view.mapValues(_.map(_._2).toList).toMap)
  }
}

val dict =  MultiMap(
  "apple" -> "fruit",
  "pear" -> "fruit",
  "carrot" -> "yellow",
  "apple" -> "delicious",
  "carrot" -> "vegetable"
)

dict.add("pear", "delicious").add("melon", "yellow")


dict.map(_.toUpperCase())

dict.map(_.length)
