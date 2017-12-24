package parser.entry.element

import parser.entry.Depth

trait Attribute {
  def show(depth: Depth): String
}
