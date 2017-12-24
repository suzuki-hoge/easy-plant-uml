package easy_plant_uml.entry.element

sealed trait ElementType {
  def toString: String
}

case object Class extends ElementType {
  override def toString: String = "class"
}

case object Enum extends ElementType {
  override def toString: String = "enum"
}

case object Interface extends ElementType {
  override def toString: String = "interface"
}
