package easy_plant_uml.parser

import easy_plant_uml.entry.Entry
import easy_plant_uml.entry.contractable.{Containable, ContainableType, Namespace, Package}

trait EntryParser extends BaseParser with BlankParser with ElementParser with RawParser {
  protected def entry(indent: Indent): Parser[Entry] = blank(indent) | containable(indent) | element(indent) | raw(indent)

  private def containable(indent: Indent): Parser[Containable] = indent.s ~> ct ~ space ~ identifier ~ eol ~ rep(entry(indent.deeper)) ^^ {
    case ct ~ _ ~ id ~ _ ~ subs => Containable(id, ct, subs)
  }

  private def ct: Parser[ContainableType] = (".p" | ".n") ^^ {
    case ".p" => Package
    case ".n" => Namespace
  }
}
