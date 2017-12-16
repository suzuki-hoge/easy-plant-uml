package parser

import scala.collection.mutable

trait Entry {
  protected type MList[A] = mutable.MutableList[A]

  def show: MList[String] = showRec(0, mutable.MutableList())

  def showRec(depth: Int, acc: MList[String]): MList[String]

  protected def indented(depth: Int, s: String): String = "  " * depth + s
}

sealed trait ContainableType

case object Package extends ContainableType

case object Namespace extends ContainableType

sealed trait ElementType

case object Object extends ElementType

case object Class extends ElementType

case object Enum extends ElementType

case class Containable(s: String, t: ContainableType, subs: List[Entry]) extends Entry {
  override def showRec(depth: Int, acc: MList[String]): MList[String] = {
    acc += indented(depth, t match {
      case Package => "package " + s + " {"
      case Namespace => "namespace " + s + " {"
    })
    subs.foreach(_.showRec(depth + 1, acc))
    acc += indented(depth, "}")
    acc
  }
}

case class Element(s: String, t: ElementType) extends Entry {
  override def showRec(depth: Int, acc: MList[String]): MList[String] = {
    acc += indented(depth, t match {
      case Object => "object " + s
      case Class => "class " + s
      case Enum => "enum " + s
    })
    acc
  }
}

case class Raw(s: String) extends Entry {
  override def showRec(depth: Int, acc: MList[String]): MList[String] = {
    acc += indented(depth, s)
    acc
  }
}
