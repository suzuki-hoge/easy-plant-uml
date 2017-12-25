package easy_plant_uml

import java.io.{File, PrintWriter}

import easy_plant_uml.converter.Converter
import easy_plant_uml.entry.{Depth, Entry}
import easy_plant_uml.parser.EntriesParser

import scala.io.Source

object EasyPlantUml {
  def apply(inPath: String, outPath: String): Unit = {
    val result: String = getString(inPath)
    val w = new PrintWriter(outPath)
    w.write(result)
    w.close()
  }

  private def getString(inPath: String): String = toPlantUml(
    if (new File(inPath).exists) parse(inPath) else noFile(inPath)
  )

  private def toPlantUml(gotten: String): String =
    s"""@startuml
       |
       |$gotten
       |
       |@enduml
       |""".stripMargin

  private def noFile(inPath: String): String = s"""note "$inPath is not exists." as Note"""

  private def parse(inPath: String): String = {
    val lines: List[String] = Source.fromFile(inPath).getLines().toList
    val in: String = Converter.toString(Converter.blankToDot(lines))
    val parsed: Either[String, List[Entry]] = EntriesParser.parse(in)

    parsed.fold(error => s"' $error", entries => entries.map(_.show(Depth.zero)).mkString("\n"))
  }
}

object X extends App {
  EasyPlantUml("src/test/resources/nonexistent.epuml", "src/test/resources/nonexistent.puml")

  EasyPlantUml("src/test/resources/sample.epuml", "src/test/resources/sample.puml")
}
