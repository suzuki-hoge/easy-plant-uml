package easy_plant_uml.parser

import easy_plant_uml.entry.contractable.{Containable, Namespace, Package}
import easy_plant_uml.entry.element.{Class, Declaration, Element, Enum, Interface, Private, Public, Static}
import easy_plant_uml.entry.{Blank, Raw}
import org.scalatest.FunSuite

class EntriesParserTest extends FunSuite {
  test("success") {
    def in =
      """.p foo
        |  .p bar
        |    ' comment
        |    .e Type
        |      On, Off
        |  .
        |  .c Foo
        |    - String value
        |    .
        |    +s Foo create()
        |  .
        |  Foo -> Type
        |.
        |.i Base
        |.
        |Foo -up-|> Base
        |.
        |.n pon
        |""".stripMargin

    def exp = List(
      Containable("foo", Package, List(
        Containable("bar", Package, List(
          Raw("' comment"),
          Element("Type", Enum, List(Declaration(List(), "On, Off")))
        )),
        Blank(),
        Element("Foo", Class, List(Declaration(List(Private), "String value"), Blank(), Declaration(List(Public, Static), "Foo create()"))),
        Blank(),
        Raw("Foo -> Type")
      )),
      Blank(),
      Element("Base", Interface, List()),
      Blank(),
      Raw("Foo -up-|> Base"),
      Blank(),
      Containable("pon", Namespace, List())
    )

    assert(
      EntriesParser.parse(in) == Right(exp)
    )
  }

  test("failure") {
    assert(
      EntriesParser.parse(".p foo") == Left("parse failure on (line: 1, column: 7)")
    )
  }
}
