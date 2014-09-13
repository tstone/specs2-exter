package org.specs2.exter.notifier

import org.specs2.reporter.Notifier
import org.specs2.execute.Details
import org.specs2.text.AnsiColors

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.{Stack => MStack}
import scala.io.AnsiColor._


class Minimal extends Notifier {

  protected case class Error(name: String, context: String, message: String, location: String, th: Throwable)

  private val errors: ListBuffer[Error] = ListBuffer.empty
  private val contexts: MStack[String] = MStack[String]()
  private var counter = 0

  def specStart(title: String, location: String) = print("\n  ".white)
  def specEnd(title: String, location: String) = {
    print("\n\n")
    if (errors.length > 0) printFail()
    else printSuccess()
  }

  def contextStart(name: String, location: String) = contexts.push(name)
  def contextEnd(name: String, location: String) = contexts.pop()
  def text(text: String, location: String) = Unit
  def exampleStarted(name: String, location: String) = Unit
  def exampleFailure(name: String, message: String, location: String, f: Throwable, details: Details, duration: Long) = onError(name, message, location, f)
  def exampleError  (name: String, message: String, location: String, f: Throwable, duration: Long) = onError(name, message, location, f)
  def exampleSuccess(name: String, duration: Long) = printDot(CYAN)
  def exampleSkipped(name: String, message: String, duration: Long) = printDot(YELLOW)
  def examplePending(name: String, message: String, duration: Long) = printDot(YELLOW)

  // --- Helpers ---

  protected def onError(name: String, message: String, location: String, th: Throwable) = {
    val context = contexts.toList match {
      case Nil      => ""
      case c :: Nil => c
      case cs       => cs.reverse.mkString(", ") + ","
    }
    errors.append(Error(name, context, message, location, th))
    printDot(RED)
  }

  protected implicit class Colors(s: String) {
    def magenta = AnsiColors.color(s, MAGENTA)
    def yellow = AnsiColors.color(s, YELLOW)
    def white = AnsiColors.color(s, WHITE)
    def black = AnsiColors.color(s, BLACK)
    def blue = AnsiColors.color(s, BLUE)
    def whiteBackground = AnsiColors.color(s, WHITE_B)
    def cyan = AnsiColors.color(s, CYAN)
    def red = AnsiColors.color(s, RED)
  }

  protected def spaces(count: Int) = (1 to count).map(_ => " ").mkString


  // --- Printers ---

  protected def printDot(color: String) = {
    counter += 1
    if (counter == 80) {
      print("\n  ")
      counter = 0
    }
    print(AnsiColors.color(".", color))
  }

  private def printSuccess() = println("  SUCCESS".magenta + "\n")

  private def printFail() = {
    println(s"  FAILURE:\n".red)
    val errorsCountLen = errors.length.toString.length

    errors.zipWithIndex.foreach { case(err, i) =>
      val index = i + 1
      println(s"    $index.) ${err.context} ${err.name} (${err.location})")
      println(spaces(errorsCountLen + 7) + err.message.yellow + "\n")
    }

    print("\n")
  }

}