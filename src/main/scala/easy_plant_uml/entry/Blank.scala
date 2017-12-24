package easy_plant_uml.entry

import easy_plant_uml.entry.element.Attribute

case class Blank() extends Entry with Attribute {
  override def show(depth: Depth): String = ""
}
