package easy_plant_uml.entry.element

import easy_plant_uml.entry.Depth

case class Declaration(mods: List[Modifier], value: String) extends Attribute {
  override def show(depth: Depth): String = mods match {
    case List() => depth.indent(value)
    case _ =>
      val joined = mods.mkString(" ")
      depth.indent(s"$joined $value")
  }
}
