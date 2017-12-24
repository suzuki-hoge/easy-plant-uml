package parser.entry

import org.scalatest.FunSuite
import parser.entry.contractable.{Containable, Namespace}
import parser.entry.element.{Class, Declaration, Element, Private}

class EntryTest extends FunSuite {
  test("test") {
    val element = Element("Foo", Class, List(Declaration(List(Private), "FooId")))
    val blank = Blank()
    val raw = Raw("' comment")
    val entry = Containable("foo", Namespace, List(element, blank, raw))

    val exp =
      """namespace foo {
        |  class Foo {
        |    - FooId
        |  }
        |
        |  ' comment
        |}""".stripMargin

    assert(
      entry.show(Depth.zero) == exp
    )
  }
}
