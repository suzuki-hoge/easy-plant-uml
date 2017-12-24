package easy_plant_uml.entry

case class Raw(value: String) extends Entry {
  override def show(depth: Depth): String = depth.indent(value)
}
