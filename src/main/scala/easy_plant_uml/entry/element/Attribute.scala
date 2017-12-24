package easy_plant_uml.entry.element

import easy_plant_uml.entry.Depth

trait Attribute {
  def show(depth: Depth): String
}
