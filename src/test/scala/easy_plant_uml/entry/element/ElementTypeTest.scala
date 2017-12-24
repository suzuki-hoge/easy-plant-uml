package easy_plant_uml.entry.element

import org.scalatest.FunSuite

class ElementTypeTest extends FunSuite {
  test("test") {
    assert(
      Class.toString == "class"
    )

    assert(
      Enum.toString == "enum"
    )

    assert(
      Interface.toString == "interface"
    )
  }
}
