package parser.entry

import parser.entry.element.Attribute

case class Blank() extends Entry with Attribute {
  override def show(depth: Depth): String = ""
}
