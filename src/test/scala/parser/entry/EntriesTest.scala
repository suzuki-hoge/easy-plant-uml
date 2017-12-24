package parser.entry

import org.scalatest.FunSuite
import parser.entry.contractable.{Containable, Namespace}
import parser.entry.element.{Class, Declaration, Element, Private}

class EntriesTest extends FunSuite {
  test("test") {
    val element = Element("Foo", Class, List(Declaration(List(Private), "FooId")))
    val blank = Blank()
    val raw = Raw("' comment")
    val containable = Containable("bar", Namespace, List(Element("Bar", Class, List())))
    val entries = Entries(List(element, blank, raw, containable))

    val exp =
      """class Foo {
        |  - FooId
        |}
        |
        |' comment
        |namespace bar {
        |  class Bar
        |}""".stripMargin

    assert(
      entries.show() == exp
    )
  }
}
