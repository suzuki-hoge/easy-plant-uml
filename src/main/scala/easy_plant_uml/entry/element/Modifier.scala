package easy_plant_uml.entry.element

sealed trait Modifier {
  def toString: String
}

case object Public extends Modifier {
  override def toString: String = "+"
}

case object Protected extends Modifier {
  override def toString: String = "#"
}

case object Private extends Modifier {
  override def toString: String = "-"
}

case object Static extends Modifier {
  override def toString: String = "{static}"
}
