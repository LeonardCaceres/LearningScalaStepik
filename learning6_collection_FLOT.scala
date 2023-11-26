object Naval {
  type Point = (Int, Int) // Клетка корабля - пара координат этой клетки на поле
  type Field = Vector[Vector[Boolean]] // Игровое поле - двумерный массив, хранящий для каждой ячейки булево значение - стоит ли на этой клетке корабль?
  type Ship = List[Point] // Корабль как список точек
  type Fleet = Map[String, Ship] // Множество всех кораблей на поле как ассоциативный массив. По строковому ключу (имени корабля) находится список точек корабля
  type Game = (Field, Fleet) // Игровое поле и список кораблей
}

object Main {
  def main(args: Array[String]) {
    import scala.io.StdIn
    import Naval.{Point, Field, Ship, Fleet, Game}
    var field2 = Vector(
      Vector(false, false, false, false, false, false, false, false, false, false),
      Vector(false, false, false, false, false, false, false, false, false, false),
      Vector(false, false, false, false, false, false, false, false, false, false),
      Vector(false, false, false, false, false, false, false, false, false, false),
      Vector(false, false, false, false, false, false, false, false, false, false),
      Vector(false, false, false, false, false, false, false, false, false, false),
      Vector(false, false, false, false, false, false, false, false, false, false),
      Vector(false, false, false, false, false, false, false, false, false, false),
      Vector(false, false, false, false, false, false, false, false, false, false),
      Vector(false, false, false, false, false, false, false, false, false, false)
    )
    def validateShip(ship: Ship): Boolean = {
      if (ship.size == 1) {
        return true
      }
      var fir = false
      var sec = false
      if(ship.size > 1) {
        if (ship(0)._1 == ship(1)._1) {
          fir = true
        }
        if (ship(0)._2 == ship(1)._2) {
          sec = true
        }
      }
      if (sec && fir) {
        return false
      }
      if (fir) {
        for(i <- 1 to ship.size - 1) {
          if(Math.abs(ship(i)._2 - ship(i - 1)._2) != 1 || ship(i)._1 != ship(i - 1)._1) {
            return false
          }
        }
      } else if (sec) {
        for (i <- 1 until ship.size) {
          if (Math.abs(ship(i)._1 - ship(i - 1)._1) != 1 || ship(i)._2 != ship(i - 1)._2) {
            return false
          }
        }
      }
      true
    }
    def validatePosition(ship: Ship, field: Field): Boolean = {
      for (i <- 0 until ship.size) {
        val x = ship(i)._1
        val y = ship(i)._2
        if (field(x)(y)) {
          return false
        }
      }
      return true
    }

    def enrichFleet(fleet: Fleet, name: String, ship: Ship): Fleet = {
      val mapa = fleet + (name -> ship)
      println(name)
      mapa
    }

    def markUsedCells(field2: Field, ship: Ship): Field = {
      var field = field2
      for (i <- 0 until ship.size) {
        val x = ship(i)._1
        val y = ship(i)._2
        field = field.updated(x, field(x).updated(y, true))
        if (y - 1 >= 0) {
          field = field.updated(x, field(x).updated(y - 1, true))
        }
        if (y + 1 <= 9) {
          field = field.updated(x, field(x).updated(y + 1, true))
        }
        if (x - 1 >= 0) {
          field = field.updated(x - 1, field(x - 1).updated(y, true))
          if (y - 1 >= 0) {
            field = field.updated(x - 1, field(x - 1).updated(y - 1, true))
          }
          if (y + 1 <= 9) {
            field = field.updated(x - 1, field(x - 1).updated(y + 1, true))
          }
        }
        if (x + 1 <= 9) {
          field = field.updated(x + 1, field(x + 1).updated(y, true))
          if (y - 1 >= 0) {
            field = field.updated(x + 1, field(x + 1).updated(y - 1, true))
          }
          if (y + 1 <= 9) {
            field = field.updated(x + 1, field(x + 1).updated(y - 1, true))
          }
        }
      }
      return field
    }

    def tryAddShip(game: Game, name: String, ship: Ship): Game = {
      if (validateShip(ship)) {
        if (validatePosition(ship, game._1)) {
          enrichFleet(game._2, name, ship)
          val field = markUsedCells(game._1, ship)
          return (field, game._2)
        } else {
          return game
        }
      } else {
        return game
      }
    }

    var fleet: Map[String, Ship] = Map()
    val n = StdIn.readInt()
    for (i <- 1 to n) {
      val line = StdIn.readLine()
      val name = line.split(" ")(0)
      val q = line.split(" ")(1).toInt
      var list: List[(Int, Int)] = List()
      for (j <- 0 until q) {
        val line2 = StdIn.readLine().split(" ")
        val s1 = line2(0).toInt
        val s2 = line2(1).toInt
        list = list ++ List((s1,s2))
      }
      val game_ans = tryAddShip((field2, fleet), name, list)
      field2 = game_ans._1
      fleet = game_ans._2

    }
  }
}
