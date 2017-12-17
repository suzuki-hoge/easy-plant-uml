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

case class Containable(id: String, t: ContainableType, subs: List[Entry]) extends Entry {
  override def showRec(depth: Int, acc: MList[String]): MList[String] = {
    acc += indented(depth, t match {
      case Package => "package " + id + " {"
      case Namespace => "namespace " + id + " {"
    })
    subs.foreach(_.showRec(depth + 1, acc))
    acc += indented(depth, "}")
    acc
  }
}

case class Element(id: String, t: ElementType, attrs: List[String]) extends Entry {
  override def showRec(depth: Int, acc: MList[String]): MList[String] = {
    def t = this.t match {
      case Object => "object "
      case Class => "class "
      case Enum => "enum "
    }
    if (attrs.isEmpty) noAttrs(depth, t, acc) else withAttrs(depth, t, acc)
  }

  private def noAttrs(depth: Int, t: String, acc: MList[String]): MList[String] = {
    acc += indented(depth, t + id)
    acc
  }

  private def withAttrs(depth: Int, t: String, acc: MList[String]): MList[String] = {
    acc += indented(depth, t + id + " {")
    attrs.foreach {
      case s if s.trim.isEmpty => acc += ""
      case s => acc += indented(depth + 1, s)
    }
    acc += indented(depth, "}")
    acc
  }
}

case class Raw(id: String) extends Entry {
  override def showRec(depth: Int, acc: MList[String]): MList[String] = {
    acc += indented(depth, id)
    acc
  }
}

case class Blank() extends Entry {
  override def showRec(depth: Int, acc: MList[String]): MList[String] = {
    acc += ""
    acc
  }
}
