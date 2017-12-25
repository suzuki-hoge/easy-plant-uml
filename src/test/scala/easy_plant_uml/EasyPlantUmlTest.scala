package easy_plant_uml

import org.scalatest.FunSuite

import scala.io.Source

class EasyPlantUmlTest extends FunSuite {
  test("success") {
    val inPath = "src/test/resources/sample.epuml"
    val outPath = "src/test/resources/sample.puml"

    EasyPlantUml(inPath, outPath)

    val act: String = Source.fromFile(outPath).getLines().mkString("\n")

    val exp =
      """@startuml
        |
        |package foo {
        |  package bar {
        |    ' comment
        |    enum Type {
        |      On, Off
        |    }
        |  }
        |
        |  class Foo {
        |    - String value
        |
        |    + {static} Foo create()
        |  }
        |
        |  Foo -> Type
        |}
        |
        |interface Base
        |
        |Foo -up-|> Base
        |
        |namespace pon {
        |}
        |
        |@enduml""".stripMargin

    assert(exp == act)
  }

  test("no file") {
    val inPath = "src/test/resources/nonexistent.epuml"
    val outPath = "src/test/resources/nonexistent.puml"

    EasyPlantUml(inPath, outPath)

    val act: String = Source.fromFile(outPath).getLines().mkString("\n")

    val exp =
      """@startuml
        |
        |note "src/test/resources/nonexistent.epuml is not exists." as Note
        |
        |@enduml""".stripMargin

    assert(exp == act)
  }
}
