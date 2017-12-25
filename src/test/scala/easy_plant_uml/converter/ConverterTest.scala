package easy_plant_uml.converter

import org.scalatest.FunSuite

class ConverterTest extends FunSuite {
  test("blankToDot") {
    val in = List(
      ".p foo",
      "  . p bar",
      "",
      "  foo -> bar",
      "",
      "",
      ".p pon"
    )

    val exp = List(
      ".p foo",
      "  . p bar",
      "  .",
      "  foo -> bar",
      ".",
      ".",
      ".p pon"
    )

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
