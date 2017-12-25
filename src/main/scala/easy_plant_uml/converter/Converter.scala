package easy_plant_uml.converter

object Converter {
  def blankToDot(lines: List[String]): List[String] = {
    def depths: List[Int] = lines.map {
      case "" => 0
      case s => s.takeWhile(_ == ' ').length / 2 + 1
    }

    def goalDepths: List[Int] = depths.reverse.scan(depths.head)((x, y) => if (y == 0) x else y).tail.reverse

    def adjusters: List[Adjuster] = depths.zip(goalDepths).map(tup => Adjuster(tup._1, tup._2))

    lines.zip(adjusters).map(tup => tup._2.f(tup._1))
  }

  private case class Adjuster(depth: Int, goalDepth: Int) {
    def f(line: String): String = depth match {
      case 0 => " " * (goalDepth * 2 - 2) + "."
      case _ => line
    }
  }

  def toString(lines: List[String]): String = {
    lines.mkString("\n") + "\n"
  }
}
