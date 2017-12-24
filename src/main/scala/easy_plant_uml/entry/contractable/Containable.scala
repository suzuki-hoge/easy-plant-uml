package easy_plant_uml.entry.contractable

import easy_plant_uml.entry.{Depth, Entry}

case class Containable(id: String, ct: ContainableType, subs: List[Entry]) extends Entry {
  override def show(depth: Depth): String = subs match {
    case List() => noSubs(depth)
    case _ => withSubs(depth)
  }

  private def withSubs(depth: Depth): String = {
    val open: String = depth.indent(s"$ct $id {")
    val joined: String = subs.map(_.show(depth.deeper)).mkString("\n")
    val close: String = depth.indent("}")

    s"""$open
       |$joined
       |$close""".stripMargin
  }

  private def noSubs(depth: Depth): String = {
    val open: String = depth.indent(s"$ct $id {")
    val close: String = depth.indent("}")

    s"""$open
       |$close""".stripMargin
  }
}
