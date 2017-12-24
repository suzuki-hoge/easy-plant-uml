package easy_plant_uml.entry.contractable

import org.scalatest.FunSuite
import easy_plant_uml.entry.Depth

class ContainableTest extends FunSuite {
  test("test") {
    assert(
      Containable("foo", Package, List()).show(Depth.zero) ==
        """package foo {
          |}""".stripMargin
    )

    assert(
      Containable("foo", Package, List()).show(Depth.zero.deeper) ==
        """  package foo {
          |  }""".stripMargin
    )

    val containable = Containable(
      "foo",
      Namespace,
      List(
        Containable("bar", Namespace, List())
      )
    )

    val exp =
      """  namespace foo {
        |    namespace bar {
        |    }
        |  }""".stripMargin

    assert(
      containable.show(Depth.zero.deeper) == exp
    )
  }
}
