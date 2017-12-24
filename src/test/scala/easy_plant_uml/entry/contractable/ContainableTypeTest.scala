package easy_plant_uml.entry.contractable

import org.scalatest.FunSuite

class ContainableTypeTest extends FunSuite {
  test("test") {
    assert(
      Package.toString == "package"
    )

    assert(
      Namespace.toString == "namespace"
    )
  }
}
