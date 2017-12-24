package easy_plant_uml.parser

import scala.util.parsing.combinator.RegexParsers

trait BaseParser extends RegexParsers {
  override val skipWhitespace = false

  protected def identifier: Parser[String] = "[a-zA-Z0-9_]+".r

  protected def space: Parser[String] = " "

  protected def eol: Parser[String] = "\r\n|[\n\r\u2028\u2029\u0085]".r
}
