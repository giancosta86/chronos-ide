package info.gianlucacosta.chronos.ide

import java.io.File
import java.net.URL

import info.gianlucacosta.chronos.chronos_ide.{ArtifactInfo => IdeInfo}
import info.gianlucacosta.chronos.interpreter.Interpreter
import info.gianlucacosta.chronos.parser.BasicAstBuilder
import info.gianlucacosta.chronos.parser.exceptions.ParsingException
import info.gianlucacosta.chronos.{ArtifactInfo => Language}
import info.gianlucacosta.helios.concurrency.AtomicStringBuilder
import info.gianlucacosta.helios.desktop.DesktopUtils
import info.gianlucacosta.omnieditor.{AppStrategy, StyledCodeEditor}

import scalafx.stage.FileChooser


class IdeStrategy extends AppStrategy {
  private lazy val aboutBox = new AboutBox

  override def title: String =
    IdeInfo.name


  override def createSourceFileChooser() = {
    val fileChooser = new FileChooser

    fileChooser.extensionFilters.setAll(
      new FileChooser.ExtensionFilter("Chronos source file", "*.chronos")
    )

    fileChooser
  }


  override def createCodeEditor(): StyledCodeEditor = {
    val codeEditor = new ChronosCodeEditor

    val exampleCode = getExampleCode
    codeEditor.setText(exampleCode)

    codeEditor.getUndoManager.forgetHistory()

    codeEditor
  }


  private def getExampleCode: String =
    io.Source.fromInputStream(
      getClass.getResourceAsStream("example.chronos")
    ).mkString


  override def getSavedSourceFile(sourceFileChooser: FileChooser, selectedFile: File): File =
    selectedFile


  override def settingsSupported: Boolean = false

  override def showSettings(): Unit = ()


  override def run(programCode: String, outputBuffer: AtomicStringBuilder): Unit = {
    val input = new IdeInput
    val output = new IdeOutput(outputBuffer)

    try {
      val program = BasicAstBuilder.buildAST(programCode)
      val interpreter = new Interpreter(input, output)
      interpreter.run(program)
    } catch {
      case ex: InterruptedException => throw ex
      case ex: ParsingException => output.printException(ex)
      case ex: Exception =>
        output.printException(ex)
        ex.printStackTrace(System.err)
    }
  }


  override def syntaxCss: URL =
    getClass.getResource("chronos-syntax.css")


  override def showOnlineReference(): Unit =
    DesktopUtils.openBrowser(s"${Language.website}/wiki")

  override def showAboutWindow(): Unit =
    aboutBox.showAndWait()
}