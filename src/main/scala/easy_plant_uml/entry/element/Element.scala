package easy_plant_uml.entry.element

import easy_plant_uml.entry.{Depth, Entry}

case class Element(id: String, et: ElementType, attrs: List[Attribute]) extends Entry {
  override def show(depth: Depth): String = attrs match {
    case List() => noAttrs(depth)
    case _ => withAttrs(depth)
  }

  private def withAttrs(depth: Depth): String = {
    val open: String = depth.indent(s"$et $id {")
    val joined: String = attrs.map(_.show(depth.deeper)).mkString("\n")
    val close: String = depth.indent("}")

    s"""$open
       |$joined
       |$close""".stripMargin
  }

  private def noAttrs(depth: Depth): String = depth.indent(s"$et $id")
}
