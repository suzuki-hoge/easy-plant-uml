package easy_plant_uml.parser

import scala.util.parsing.combinator.RegexParsers

case class Indent(value: Int) extends RegexParsers {
  def deeper: Indent = Indent(value + 1)

  def s: String = "  " * value
}

object Indent {
  def zero: Indent = Indent(0)
}

