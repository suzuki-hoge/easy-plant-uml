package easy_plant_uml.entry

import org.scalatest.FunSuite

class DepthTest extends FunSuite {
  test("test") {
    assert(
      Depth.zero.indent("foo") == "foo"
    )
    assert(
      Depth.zero.deeper == Depth(1)
    )
    assert(
      Depth.zero.deeper.deeper.indent("foo") == "    foo"
    )
  }
}
