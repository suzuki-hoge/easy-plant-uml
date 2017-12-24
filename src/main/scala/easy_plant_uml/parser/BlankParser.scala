package easy_plant_uml.parser

import easy_plant_uml.entry.Blank
import easy_plant_uml.entry.element.Attribute

trait BlankParser extends BaseParser {
  protected def blank(indent: Indent): Parser[Blank] = indent.s ~> "." <~ eol ^^ (_ => Blank())
}
