package easy_plant_uml.parser

import easy_plant_uml.entry.Entry

object EntriesParser extends EntryParser {
  def parse(lines: String): List[Entry] = parseAll(entries, lines).get

  private def entries: Parser[List[Entry]] = rep(entry(Indent.zero))
}
