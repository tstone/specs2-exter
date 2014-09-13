package org.specs2.matcher

import org.specs2.exter.matcher.ExterSeq
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


  "containSome" should {
    "fail if none of the elements are found" in {
      val subject = Seq(4, 5, 6)
      val r = containSome(7, 8, 9).apply(Expectable(subject))
      r.isSuccess must beFalse
    }

    "succeed if at least one element is found" in {
      val subject = Seq(2, 4, 6)
      val r = containSome(6, 8, 10).apply(Expectable(subject))
      r.isSuccess must beTrue
    }

    "succeed if at all elements are found" in {
      val subject = Seq(6, 4, 2)
      val r = containSome(2, 4, 6).apply(Expectable(subject))
      r.isSuccess must beTrue
    }

    "report which value was found" in {
      val subject = Seq(4, 5, 6)
      val r = not(containSome(5, 6, 7)).apply(Expectable(subject))
      r.message must contain("'5' and '6' found in")
    }
  }

}