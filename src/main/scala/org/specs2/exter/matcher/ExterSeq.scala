package org.specs2.exter.matcher

import org.specs2.matcher.{Expectable, Matcher}
import org.specs2.specification.SpecificationStructure


trait ExterSeq { self: SpecificationStructure =>

  private def seqToEnglish[A](seq: Seq[A]) = {
    seq.map(x => s"'$x'")
      .mkString(", ")
      .reverse
      .replaceFirst(" ,", " and ".reverse)
      .reverse
  }

  /** Assert that a given sequence contains all of the listed elements, regardless of order. */
  def containAll[A](values: A*) = new Matcher[Seq[A]] {
    def apply[S <: Seq[A]](ex: Expectable[S]) = {
      val subject = ex.value
      val notFound = values.filter(!subject.contains(_))

      result(
        notFound.length == 0,
        s"$subject contained ${seqToEnglish(values)}",
        s"$subject does not contain ${seqToEnglish(notFound)}",
        ex
      )
    }
  }

  /** Assert that a given sequence contains all and no more of the listed elements, regardless of order. */
  def containExactly[A](values: A*) = new Matcher[Seq[A]] {
    def apply[S <: Seq[A]](ex: Expectable[S]) = {
      val subject = ex.value
      val notFound = values.filter(!subject.contains(_))

      lazy val diff = subject.toSet.diff(values.toSet).toSeq
      lazy val failMessage =
        if (notFound.length == 0) s"$subject had extra values ${seqToEnglish(diff)}"
        else  s"$subject does not contain ${seqToEnglish(notFound)}"

      result(
        subject.length == values.length && notFound.length == 0,
        s"$subject contained exactly ${seqToEnglish(values)}",
        failMessage,
        ex
      )
    }
  }


  /** Assert that at least one of the listed elements is within the given sequence */
  def containSome[A](values: A*) = new Matcher[Seq[A]] {
    def apply[S <: Seq[A]](ex: Expectable[S]) = {
      val subject = ex.value
      val found = values.filter(subject.contains)

      result(
        found.length > 0,
        s"${seqToEnglish(found)} found in $subject",
        s"None of the expected values found (${seqToEnglish(values)}}) in $subject",
        ex
      )
    }
  }

}