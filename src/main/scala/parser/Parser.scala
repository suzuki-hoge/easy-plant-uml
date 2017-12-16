package parser

import scala.util.parsing.combinator.JavaTokenParsers

object Parser extends JavaTokenParsers {
  override val skipWhitespace = false

  private def space: Parser[String] = " "

  private def deeper(indent: String): String = indent + "  "

  private def eol: Parser[String] = "\r\n|[\n\r\u2028\u2029\u0085]".r

  private def identifier: Parser[String] = "[a-zA-Z_]+".r

  private def entry(indent: String): Parser[Entry] = element(indent) | containable(indent)

  private def element(indent: String): Parser[Element] = indent ~> (".o" | ".c" | ".e") ~ space ~ identifier <~ eol ^^ {
    case ".o" ~ _ ~ s => Element(s, Object)
    case ".c" ~ _ ~ s => Element(s, Class)
    case ".e" ~ _ ~ s => Element(s, Enum)
  }

  private def containable(indent: String): Parser[Containable] = indent ~> (".p" | ".n") ~ space ~ identifier ~ eol ~ rep(entry(deeper(indent))) ^^ {
    case ".p" ~ _ ~ s ~ _ ~ subs => Containable(s, Package, subs)
    case ".n" ~ _ ~ s ~ _ ~ subs => Containable(s, Namespace, subs)
  }

  def entries(lines: String): ParseResult[List[Entry]] = parseAll(rep(entry("")), lines + "\n")
}
