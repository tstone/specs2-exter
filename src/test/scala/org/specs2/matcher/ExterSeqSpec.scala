package org.specs2.matcher

import org.specs2.exter.ExterSeq
import org.specs2.mutable.SpecificationLike


class ExterSeqSpec extends SpecificationLike with ExterSeq {

  "containsAll" should {
    "fail if at least one element isn't found" in {
      val subject = Seq(2, 3, 4, 5)
      val r = containAll(1, 2, 3).apply(Expectable(subject))
      r.isSuccess must beFalse
    }

    "succeed if all elements are found, regardless of order" in {
      val subject = Seq(3, 2, 1)
      val r = containAll(1, 2, 3).apply(Expectable(subject))
      r.isSuccess must beTrue
    }

    "succeed if the test subject contains more elements" in {
      val subject = Seq(5, 4, 3, 2, 1)
      val r = containAll(1, 2, 3).apply(Expectable(subject))
      r.isSuccess must beTrue
    }

    "report which it failed on" in {
      val subject = Seq(1, 2)
      val r = containAll(1, 2, 3, 4).apply(Expectable(subject))
      r.message must contain("does not contain '3' and '4'")
    }
  }


  "containExactly" should {
    "fail if at least one element isn't found" in {
      val subject = Seq(2, 3, 4, 5)
      val r = containExactly(1, 2, 3).apply(Expectable(subject))
      r.isSuccess must beFalse
    }

    "succeed if the test subject contains more elements" in {
      val subject = Seq(5, 4, 3, 2, 1)
      val r = containExactly(1, 2, 3).apply(Expectable(subject))
      r.isSuccess must beFalse
    }

    "succeed if all elements are found, regardless of order" in {
      val subject = Seq(3, 2, 1)
      val r = containExactly(1, 2, 3).apply(Expectable(subject))
      r.isSuccess must beTrue
    }

    "report which it failed on" in {
      val subject = Seq(1, 2, 4, 5)
      val r = containExactly(1, 2, 3, 4).apply(Expectable(subject))
      r.message must contain("does not contain '3'")
    }

    "report when there are extra values" in {
      val subject = Seq(5, 4, 3, 2, 1)
      val r = containExactly(1, 2, 3, 4).apply(Expectable(subject))
      r.message must contain("had extra values '5'")
    }
  }

}