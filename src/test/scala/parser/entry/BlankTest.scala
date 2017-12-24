package parser.entry

import org.scalatest.FunSuite

class BlankTest extends FunSuite {
  test("test") {
    assert(
      Blank().show(Depth.zero) == ""
    )
    assert(
      Blank().show(Depth.zero.deeper) == ""
    )
  }
}
