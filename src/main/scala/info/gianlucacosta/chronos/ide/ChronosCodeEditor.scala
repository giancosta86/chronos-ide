package info.gianlucacosta.chronos.ide

import info.gianlucacosta.omnieditor.StyledCodeEditor

/*
 *   Code editor with syntax highlighting for Chronos.
 *
 *   Inspired by a syntax highlighting example provided by RichTextFX.
 */
class ChronosCodeEditor extends StyledCodeEditor {
  private val commentPattern =
    raw"//[^\n]*" + "|" +
      raw"(?s)/\*.*?(\*/|\Z)"

  private val stringPattern = raw""""[^"\n]*"?"""

  private val numberPattern = raw"\b(\+|-)?(0|[1-9][0-9]*)(.[0-9]+)?\b|(\+|-)?inf|now|time\.v"
  private val parenthesesPattern = "[()]"
  private val bracesPattern = "[{}]"
  private val semicolonPattern = ";"

  private val keywords = Seq(
    "queue",
    "fifo",
    "lifo",
    "sorted",
    "by",
    "asc",
    "desc",
    "map",
    "event",
    "procedure",
    "disableHeapCheck",
    "create",
    "called",
    "destroy",
    "schedule",
    "at",
    "after",
    "cancel",
    "global",
    "insert",
    "into",
    "remove",
    "from",
    "call",
    "return",
    "exit",
    "if",
    "else",
    "while",
    "assert",
    "or",
    "and",
    "not",
    "get",
    "first",
    "from",
    "print",
    "println",
    "true",
    "false",
    "setRandomSeed"
  )


  private val functions = Seq(
    "isEmpty",
    "uniformRandom",
    "uniformIntRandom",
    "expRandom",
    "toDouble",
    "toInt",
    "toBool",
    "toString",
    "readDouble",
    "readInt",
    "readBool",
    "readString",
    "floor",
    "ceil"
  )


  addPattern("comment", commentPattern)
  addPattern("string", stringPattern)
  addPattern("number", numberPattern)
  addPattern("paren", parenthesesPattern)
  addPattern("brace", bracesPattern)
  addPattern("semicolon", semicolonPattern)
  addTokens("keyword", keywords: _*)
  addTokens("function", functions: _*)

  getUndoManager.forgetHistory()
  enableIndentedNewline()
  enableDynamicTabs(4)
}
