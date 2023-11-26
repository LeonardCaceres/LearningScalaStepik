final class DiffListImpl[A](listFunc: List[A] => List[A])
  extends DiffList[A](listFunc) {
  def prepend(s: List[A]): DiffListImpl[A] =
    new DiffListImpl[A](listFunc andThen (s ++ _))


  def append(s: List[A]): DiffListImpl[A] =
    new DiffListImpl[A](listFunc andThen (_ ++ s))

  def result: List[A] = listFunc(Nil)
}
