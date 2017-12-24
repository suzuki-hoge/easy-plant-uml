package easy_plant_uml.parser

import easy_plant_uml.entry.Blank
import org.scalatest.FunSuite

class BlankParserTest extends FunSuite {
  test("test") {
    val patterns = List(
      (
        """.
          |""".stripMargin, Indent.zero,

        Blank()
      ),
      (
        """  .
          |""".stripMargin, Indent.zero.deeper,

        Blank()
      )
    )

    patterns.foreach {
      case (in, indent, exp) => assert(Sut(in, indent) == exp)
    }
  }

  object Sut extends BlankParser {
    def apply(lines: String, indent: Indent): Blank = parseAll(blank(indent), lines).get
  }

}
