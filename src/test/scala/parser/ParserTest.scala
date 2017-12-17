package parser

import org.scalatest.FunSuite

class ParserTest extends FunSuite {
  test("entry") {
    assert(
      Parser.entries(".p foo\n") == List(Containable("foo", Package, List()))
    )
    assert(
      Parser.entries(
        """.c Foo
          |  - String value
          |  .
          |  + {static} Foo create()""".stripMargin + "\n") == List(
        Element("Foo", Class, List("- String value", "", "+ {static} Foo create()"))
      )
    )
    assert(
      Parser.entries(".p foo_2\n") == List(Containable("foo_2", Package, List()))
    )
    assert(
      Parser.entries("' comment\n") == List(Raw("' comment"))
    )
    assert(
      Parser.entries("' コメント\n") == List(Raw("' コメント"))
    )
    assert(
      Parser.entries("FooList -|> List\n") == List(Raw("FooList -|> List"))
    )
    assert(
      Parser.entries("Foo -[hidden]right-> FooId\n") == List(Raw("Foo -[hidden]right-> FooId"))
    )
    assert(
      Parser.entries(".\n") == List(Blank())
    )
  }

  test("entries") {
    def in =
      """.n foo
        |  .n bar
        |    ' comment
        |    .e Type
        |  .
        |  .c Foo
        |    - String value
        |    .
        |    + {static} Foo create()
        |  .
        |  Foo -> Type
        |.
        |.n pon
        |.
        |.o Table""".stripMargin + "\n"

    def exp = List(
      Containable("foo", Namespace, List(
        Containable("bar", Namespace, List(
          Raw("' comment"),
          Element("Type", Enum, List())
        )),
        Blank(),
        Element("Foo", Class, List("- String value", "", "+ {static} Foo create()")),
        Blank(),
        Raw("Foo -> Type")
      )),
      Blank(),
      Containable("pon", Namespace, List()),
      Blank(),
      Element("Table", Object, List())
    )

    assert(
      Parser.entries(in) == exp
    )
  }
}
