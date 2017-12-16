package parser

import org.scalatest.FunSuite

class ParserTest extends FunSuite {
  test("entry") {
    assert(
      Parser.entries(".p foo") == List(Containable("foo", Package, List()))
    )
    assert(
      Parser.entries(".c Foo") == List(Element("Foo", Class))
    )
    assert(
      Parser.entries(".p foo_2") == List(Containable("foo_2", Package, List()))
    )
    assert(
      Parser.entries("' comment") == List(Raw("' comment"))
    )
    assert(
      Parser.entries("' コメント") == List(Raw("' コメント"))
    )
    assert(
      Parser.entries("FooList -|> List") == List(Raw("FooList -|> List"))
    )
    assert(
      Parser.entries("Foo -[hidden]right-> FooId") == List(Raw("Foo -[hidden]right-> FooId"))
    )
  }

  test("entries") {
    def in =
      """.n foo
        |  .n bar
        |    ' comment
        |    .e Type
        |  .c Foo
        |  Foo -> Type
        |.n pon
        |.o Table""".stripMargin

    def exp = List(
      Containable("foo", Namespace, List(
        Containable("bar", Namespace, List(
          Raw("' comment"),
          Element("Type", Enum)
        )),
        Element("Foo", Class),
        Raw("Foo -> Type")
      )),
      Containable("pon", Namespace, List()),
      Element("Table", Object)
    )

    assert(
      Parser.entries(in) == exp
    )
  }
}
