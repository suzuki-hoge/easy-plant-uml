package easy_plant_uml.entry

import org.scalatest.FunSuite

class RawTest extends FunSuite {
  test("test") {
    assert(
      Raw("Foo -> FooId").show(Depth.zero) == "Foo -> FooId"
    )
    assert(
      Raw("Foo -> FooId").show(Depth.zero.deeper) == "  Foo -> FooId"
    )
  }
}
