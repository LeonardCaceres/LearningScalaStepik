abstract class User(name: String) {
  def friends: List[User]
  def FriendsofFriends = {
    (for {
      friend <- friends
      friend2 <- friend.friends if friend2 !=this
    } yield  friend2).distinct
  }
  override def toString = name
}

lazy val oleg: User = new User("Oleg") {
  def friends: List[User] = List(kate, masha)
}

lazy val kate: User = new User("kate") {
  def friends: List[User] = List(oleg, anton)
}

lazy val masha: User = new User("masha") {
  def friends: List[User] = List(kate, anton)
}

lazy val anton: User = new User("anton") {
  def friends: List[User] = List(kate, masha)
}

oleg.FriendsofFriends
