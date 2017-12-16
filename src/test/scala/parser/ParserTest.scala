package parser

import org.scalatest.FunSuite

class ParserTest extends FunSuite {
  test("entry") {
    assert(
      Parser.entries(".p foo").getOrElse("error") == List(Containable("foo", Package, List()))
    )
    assert(
      Parser.entries(".c Foo").getOrElse("error") == List(Element("Foo", Class))
    )
  }

  test("entries") {
    def in =
      """.n foo
        |  .c Id
        |  .n bar
        |    .e Type
        |.n pon
        |.o Table""".stripMargin
    def exp = List(
      Containable("foo", Namespace, List(
        Element("Id", Class),
        Containable("bar", Namespace, List(
          Element("Type", Enum)
        ))
      )),
      Containable("pon", Namespace, List()),
      Element("Table", Object)
    )
    assert(
      Parser.entries(in).getOrElse("error") == exp
    )
  }

  test("error") {
    assert(
      Parser.entries(". foo").getOrElse("error") == "error"
    )
  }
}
