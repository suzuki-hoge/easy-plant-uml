package easy_plant_uml.converter

object Converter {
  def blankToDot(lines: String): String = {
    def depths: List[Int] = lines.split("\n").map {
      case "" => 0
      case s => s.takeWhile(_ == ' ').length / 2 + 1
    }.toList

    def goalDepths: List[Int] = depths.reverse.scan(depths.head)((x, y) => if (y == 0) x else y).tail.reverse

    def adjusters: List[Adjuster] = depths.zip(goalDepths).map(tup => Adjuster(tup._1, tup._2)).toList

    lines.split("\n").toList.zip(adjusters).map(tup => tup._2.f(tup._1)).mkString("\n")
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
