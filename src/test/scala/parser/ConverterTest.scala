package parser

import org.scalatest.FunSuite

class ConverterTest extends FunSuite {
  test("blankToDot") {
    assert(
      Converter.blankToDot(
        """.p foo
          |  . p bar
          |
          |  foo -> bar
          |
          |
          |.p pon""".stripMargin) ==
        """.p foo
          |  . p bar
          |  .
          |  foo -> bar
          |.
          |.
          |.p pon""".stripMargin
    )
  }

  test("toString") {
    assert(
      Converter.toString(
        List(".p foo")
      ) == ".p foo\n"
    )

    assert(
      Converter.toString(
        List(".p foo", ".p bar")
      ) == ".p foo\n.p bar\n"
    )
  }
}
