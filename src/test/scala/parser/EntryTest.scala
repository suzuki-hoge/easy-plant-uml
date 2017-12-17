package parser

import org.scalatest.FunSuite

import scala.collection.mutable

class EntryTest extends FunSuite {
  test("entry") {
    assert(
      Element("Foo", Class, List()).show == mutable.MutableList("class Foo")
    )
    assert(
      Element("Foo", Class, List("- String value", "+ {static} Foo create()")).show == mutable.MutableList("class Foo {", "  - String value", "  + {static} Foo create()", "}")
    )
    assert(
      Containable("foo", Package, List()).show == mutable.MutableList("package foo {", "}")
    )
    assert(
      Raw("' comment").show == mutable.MutableList("' comment")
    )
  }

  test("entries") {
    def entries = List(
      Containable("foo", Namespace, List(
        Containable("bar", Namespace, List(
          Raw("' comment"),
          Element("Type", Enum, List())
        )),
        Element("Foo", Class, List("- String value", "+ {static} Foo create()")),
        Raw("Foo -> Type")
      )),
      Containable("pon", Namespace, List()),
      Element("Table", Object, List())
    )

    assert(
      entries.map(_.show).flatten == List(
        "namespace foo {",
        "  namespace bar {",
        "    ' comment",
        "    enum Type",
        "  }",
        "  class Foo {",
        "    - String value",
        "    + {static} Foo create()",
        "  }",
        "  Foo -> Type",
        "}",
        "namespace pon {",
        "}",
        "object Table"
      )
    )
  }
}