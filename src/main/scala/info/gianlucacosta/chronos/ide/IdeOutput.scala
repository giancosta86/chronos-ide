package info.gianlucacosta.chronos.ide

import info.gianlucacosta.chronos.interpreter.Output
import info.gianlucacosta.chronos.interpreter.atoms.Atom
import info.gianlucacosta.helios.concurrency.AtomicStringBuilder


class IdeOutput(outputBuffer: AtomicStringBuilder) extends Output {
  override def print(atom: Atom): Unit = {
    outputBuffer.print(atom.toString)
  }

  override def println(atom: Atom): Unit = {
    outputBuffer.println(atom.toString)
  }

  override def printException(exception: Exception): Unit = {
    outputBuffer.println("** ERROR **")
    outputBuffer.println(exception.getMessage)
  }
}
