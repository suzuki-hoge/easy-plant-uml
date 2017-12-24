package easy_plant_uml.entry

import org.scalatest.FunSuite
import easy_plant_uml.entry.contractable.{Containable, Namespace}
import easy_plant_uml.entry.element.{Class, Declaration, Element, Private}

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
