package parser.entry.element

import org.scalatest.FunSuite

class ModifierTest extends FunSuite {
  test("test") {
    assert(
      Public.toString == "+"
    )

    assert(
      Protected.toString == "#"
    )

    assert(
      Private.toString == "-"
    )

    assert(
      Static.toString == "{static}"
    )
  }
}
