package parser.entry

case class Depth(value: Int) {
  def indent(s: String): String = "  " * value + s

  def deeper: Depth = Depth(value + 1)
}

object Depth {
  def zero: Depth = Depth(0)
}
