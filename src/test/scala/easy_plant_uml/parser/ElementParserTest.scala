package easy_plant_uml.parser

import easy_plant_uml.entry.Blank
import easy_plant_uml.entry.element.{Class, Declaration, Element, Enum, Interface, Private, Public, Static}
import org.scalatest.FunSuite

class ElementParserTest extends FunSuite {
  test("test") {
    val patterns = List(
      (
        """.i Foo
          |""".stripMargin, Indent.zero,

        Element(
          "Foo", Interface, List()
        )
      ),
      (
        """.e Foo
          |  On, Off
          |""".stripMargin, Indent.zero,

        Element(
          "Foo", Enum, List(
            Declaration(List(), "On, Off")
          )
        )
      ),
      (
        """  .c Foo
          |    - String value
          |    .
          |    +s Foo create()
          |""".stripMargin, Indent.zero.deeper,

        Element(
          "Foo", Class, List(
            Declaration(List(Private), "String value"),
            Blank(),
            Declaration(List(Public, Static), "Foo create()")
          )
        )
      )
    )

    patterns.foreach {
      case (in, indent, exp) => assert(Sut(in, indent) == exp)
    }
  }

  object Sut extends ElementParser {
    def apply(lines: String, indent: Indent): Element = parseAll(element(indent), lines).get
  }

}
