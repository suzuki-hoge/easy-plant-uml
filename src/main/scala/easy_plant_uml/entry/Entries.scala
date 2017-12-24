package easy_plant_uml.entry

case class Entries(values: List[Entry]) {
  def show(): String = values.map(_.show(Depth.zero)).mkString("\n")
}
