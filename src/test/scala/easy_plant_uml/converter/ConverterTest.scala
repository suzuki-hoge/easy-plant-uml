package easy_plant_uml.converter

import org.scalatest.FunSuite

class ConverterTest extends FunSuite {
  test("blankToDot") {
    val in =
      """.p foo
        |  . p bar
        |
        |  foo -> bar
        |
        |
        |.p pon""".stripMargin

    val exp =
      """.p foo
        |  . p bar
        |  .
        |  foo -> bar
        |.
        |.
        |.p pon""".stripMargin

    assert(
      Converter.blankToDot(in) == exp
    )
  }

  test("toString") {
    val patterns = List(
      (
        List(".p foo"),

        """.p foo
          |""".stripMargin
      ),
      (
        List(".p foo", ".p bar"),

        """.p foo
          |.p bar
          |""".stripMargin
      )
    )

    patterns.foreach {
      case (in, exp) => assert(Converter.toString(in) == exp)
    }
  }
}
