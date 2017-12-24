package parser.entry.contractable

sealed trait ContainableType {
  def toString: String
}

case object Package extends ContainableType {
  override def toString: String = "package"
}

case object Namespace extends ContainableType {
  override def toString: String = "namespace"
}
