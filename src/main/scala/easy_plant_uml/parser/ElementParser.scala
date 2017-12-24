package easy_plant_uml.parser

import easy_plant_uml.entry.element.{Attribute, Class, Declaration, Element, ElementType, Enum, Interface, Modifier, Private, Protected, Public, Static}

trait ElementParser extends BlankParser {
  protected def element(indent: Indent): Parser[Element] = indent.s ~> et ~ space ~ identifier ~ eol ~ rep(attribute(indent.deeper)) ^^ {
    case et ~ _ ~ id ~ _ ~ attrs => Element(id, et, attrs)
  }

  private def et: Parser[ElementType] = (".c" | ".e" | ".i") ^^ {
    case ".c" => Class
    case ".e" => Enum
    case ".i" => Interface
  }

  private def attribute(indent: Indent): Parser[Attribute] = blank(indent) | declaration(indent)

  private def declaration(indent: Indent): Parser[Declaration] = indent.s ~> rep(mod) ~ space ~ ".*".r <~ eol ^^ {
    case mods ~ _ ~ value => Declaration(mods, value)
  } | indent.s ~> ".*".r <~ eol ^^ (value => Declaration(List(), value))

  private def mod: Parser[Modifier] = ("+" | "-" | "#" | "s") ^^ {
    case "+" => Public
    case "-" => Private
    case "#" => Protected
    case "s" => Static
  }
}
