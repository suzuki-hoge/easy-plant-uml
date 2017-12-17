package parser

import scala.util.parsing.combinator.JavaTokenParsers

object Parser extends JavaTokenParsers {
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
    indent ~> (".o" | ".c" | ".e") ~ space ~ identifier ~ eol ~ rep(attributes(deeper(indent))) ^^ {
      case ".o" ~ _ ~ id ~ _ ~ attrs => Element(id, Object, attrs)
      case ".c" ~ _ ~ id ~ _ ~ attrs => Element(id, Class, attrs)
      case ".e" ~ _ ~ id ~ _ ~ attrs => Element(id, Enum, attrs)
    }

  private def attributes(indent: String): Parser[String] = (indent ~> "^[^.].*".r <~ eol) | (indent ~> "." <~ eol ^^ (it => ""))

  private def raw(indent: String): Parser[Raw] = indent ~> ".*".r <~ eol ^^ Raw

  private def blank(indent: String): Parser[Blank] = indent ~> "." <~ eol ^^ (_ => Blank())

  def entries(lines: String): List[Entry] = parseAll(rep(entry("")), lines).get
}
