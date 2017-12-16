package parser

import scala.util.parsing.combinator.JavaTokenParsers

object Parser extends JavaTokenParsers {
  override val skipWhitespace = false

  private def space: Parser[String] = " "

  private def deeper(indent: String): String = indent + "  "

  private def eol: Parser[String] = "\r\n|[\n\r\u2028\u2029\u0085]".r

  private def identifier: Parser[String] = "[a-zA-Z0-9_]+".r

  private def entry(indent: String): Parser[Entry] = element(indent) | containable(indent) | raw(indent)

  private def containable(indent: String): Parser[Containable] = indent ~> (".p" | ".n") ~ space ~ identifier ~ eol ~ rep(entry(deeper(indent))) ^^ {
    case ".p" ~ _ ~ s ~ _ ~ subs => Containable(s, Package, subs)
    case ".n" ~ _ ~ s ~ _ ~ subs => Containable(s, Namespace, subs)
  }

  private def element(indent: String): Parser[Element] = indent ~> (".o" | ".c" | ".e") ~ space ~ identifier <~ eol ^^ {
    case ".o" ~ _ ~ s => Element(s, Object)
    case ".c" ~ _ ~ s => Element(s, Class)
    case ".e" ~ _ ~ s => Element(s, Enum)
  }

  private def raw(indent: String): Parser[Raw] = indent ~> ".*".r <~ eol ^^ Raw

  def entries(lines: String): List[Entry] = parseAll(rep(entry("")), lines + "\n").get
}
