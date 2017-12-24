package parser

import parser.entry.contractable.{Containable, Namespace, Package}
import parser.entry.element.{Class, Element, Enum, Interface}
import parser.entry.{Blank, Entry, Raw}

import scala.util.parsing.combinator.RegexParsers

object Parser extends RegexParsers {
  override val skipWhitespace = false

  private def space: Parser[String] = " "

  private def deeper(indent: String): String = indent + "  "

  private def eol: Parser[String] = "\r\n|[\n\r\u2028\u2029\u0085]".r

  private def identifier: Parser[String] = "[a-zA-Z0-9_]+".r

  private def entry(indent: String): Parser[Entry] = blank(indent) | containable(indent) | element(indent) | raw(indent)

  private def containable(indent: String): Parser[Containable] =
    indent ~> (".p" | ".n") ~ space ~ identifier ~ eol ~ rep(entry(deeper(indent))) ^^ {
      case ".p" ~ _ ~ id ~ _ ~ subs => Containable(id, Package, subs)
      case ".n" ~ _ ~ id ~ _ ~ subs => Containable(id, Namespace, subs)
    }

  private def element(indent: String): Parser[Element] =
    indent ~> (".c" | ".e" | ".i") ~ space ~ identifier ~ eol ~ rep(attributes(deeper(indent))) ^^ {
      case ".c" ~ _ ~ id ~ _ ~ attrs => Element(id, Class, List())
      case ".e" ~ _ ~ id ~ _ ~ attrs => Element(id, Enum, List())
      case ".i" ~ _ ~ id ~ _ ~ attrs => Element(id, Interface, List())
    }

  private def attributes(indent: String): Parser[String] = (indent ~> "^[^.].*".r <~ eol) | (indent ~> "." <~ eol ^^ (it => ""))

  private def raw(indent: String): Parser[Raw] = indent ~> ".*".r <~ eol ^^ Raw

  private def blank(indent: String): Parser[Blank] = indent ~> "." <~ eol ^^ (_ => Blank())

  def entries(lines: String): List[Entry] = parseAll(rep(entry("")), lines).get
}
