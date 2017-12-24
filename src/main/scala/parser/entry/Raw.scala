package parser.entry

case class Raw(value: String) extends Entry {
  override def show(depth: Depth): String = depth.indent(value)
}
