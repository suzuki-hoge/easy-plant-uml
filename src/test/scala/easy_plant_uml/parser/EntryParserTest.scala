package easy_plant_uml.parser

import easy_plant_uml.entry.contractable.{Containable, Package}
import easy_plant_uml.entry.element.{Class, Element}
import easy_plant_uml.entry.{Entry, Raw}
import org.scalatest.FunSuite

class EntryParserTest extends FunSuite {

  test("test") {
    val patterns = List(
      (
        """.p foo
          |""".stripMargin, Indent.zero,

        Containable("foo", Package, List())
      ),
      (
        """  .p foo
          |    .c Foo
          |""".stripMargin, Indent.zero.deeper,

        Containable(
          "foo", Package, List(
            Element("Foo", Class, List())
          )
        )
      ),
      (
        """' comment
          |""".stripMargin, Indent.zero,

        Raw("' comment")
      )
    )

    patterns.foreach {
      case (in, indent, exp) => assert(Sut(in, indent) == exp)
    }
  }

  object Sut extends EntryParser {
    def apply(lines: String, indent: Indent): Entry = parseAll(entry(indent), lines).get
  }

}
