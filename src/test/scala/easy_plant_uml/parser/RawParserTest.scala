package easy_plant_uml.parser

import easy_plant_uml.entry.Raw
import org.scalatest.FunSuite

class RawParserTest extends FunSuite {
  test("test") {
    val patterns = List(
      (
        """' comment
          |""".stripMargin, Indent.zero,

        Raw("' comment")
      ),
      (
        """' コメント
          |""".stripMargin, Indent.zero,

        Raw("' コメント")
      ),
      (
        """FooList -|> List
          |""".stripMargin, Indent.zero,

        Raw("FooList -|> List")
      ),
      (
        """  Foo -[hidden]right-> FooId
          |""".stripMargin, Indent.zero.deeper,

        Raw("Foo -[hidden]right-> FooId")
      )
    )

    patterns.foreach {
      case (in, indent, exp) => assert(Sut(in, indent) == exp)
    }
  }

  object Sut extends RawParser {
    def apply(lines: String, indent: Indent): Raw = parseAll(raw(indent), lines).get
  }

}
