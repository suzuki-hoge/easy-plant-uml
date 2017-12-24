package easy_plant_uml.parser

import easy_plant_uml.entry.Entry

object EntriesParser extends App with EntryParser {
  def parse(lines: String): Either[String, List[Entry]] = parseAll(entries, lines) match {
    case Success(result, next) => Right(result)
    case NoSuccess(message, next) => Left(s"parse failure on (line: ${next.pos.line}, column: ${next.pos.column})")
  }

  private def entries: Parser[List[Entry]] = rep(entry(Indent.zero))
}
