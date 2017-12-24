package easy_plant_uml.parser

import easy_plant_uml.entry.Raw

trait RawParser extends BaseParser {
  protected def raw(indent: Indent): Parser[Raw] = indent.s ~> ".*".r <~ eol ^^ Raw
}
