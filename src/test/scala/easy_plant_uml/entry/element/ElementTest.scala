package easy_plant_uml.entry.element

import org.scalatest.FunSuite
import easy_plant_uml.entry.{Blank, Depth}

class ElementTest extends FunSuite {
  test("test") {
    assert(
      Element("FooId", Interface, List()).show(Depth.zero) == "interface FooId"
    )

    assert(
      Element("FooType", Enum, List()).show(Depth.zero.deeper) == "  enum FooType"
    )

    val element = Element(
      "Foo",
      Class,
      List(
        Declaration(List(Private), "FooId"),
        Blank(),
        Declaration(List(Public, Static), "Foo create()")
      )
    )

    val exp =
      """  class Foo {
        |    - FooId
        |
        |    + {static} Foo create()
        |  }""".stripMargin

    assert(
      element.show(Depth.zero.deeper) == exp
    )
  }
}
