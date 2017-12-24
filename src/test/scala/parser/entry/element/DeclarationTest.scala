package parser.entry.element

import org.scalatest.FunSuite
import parser.entry.Depth

class DeclarationTest extends FunSuite {
  test("test") {
    assert(
      Declaration(List(), "String value").show(Depth.zero) == "String value"
    )

    assert(
      Declaration(List(), "String value").show(Depth.zero.deeper) == "  String value"
    )

    assert(
      Declaration(List(Private), "String value").show(Depth.zero) == "- String value"
    )

    assert(
      Declaration(List(Private, Static), "String value").show(Depth.zero) == "- {static} String value"
    )

    assert(
      Declaration(List(Static, Protected), "String value").show(Depth.zero.deeper) == "  {static} # String value"
    )
  }
}
