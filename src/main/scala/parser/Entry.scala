package parser

trait Entry

sealed trait EntryType

case object Package extends EntryType

case object Namespace extends EntryType

case object Object extends EntryType

case object Class extends EntryType

case object Enum extends EntryType

case class Containable(s: String, et: EntryType, subs: List[Entry]) extends Entry

case class Element(s: String, et: EntryType) extends Entry

case class Raw(s: String) extends Entry
