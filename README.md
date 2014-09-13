# Exter

A collection of matchers and language enhancements to add a bit of polish to your specs2 specs.


## Build & Installation

Not yet on maven central.

##### 1.) Clone repository

```shell
$ git clone --depth 10 git@github.com:tstone/specs2-exter.git
```

##### 2.) Build

```shell
$ cd specs2-exter
$ sbt package
```

##### 3.) Copy jar to your projet's lib folder

```shell
$ cp target/scala-2.11/specs2-exter_2.11-<version>.jar ../your-project/lib
```

## Notifiers

#### Minimal (notifier)

A mocha-inspired runner that prints only dots for examples executed.  Errors are shown in an abbreviated format.

![screenshot](https://raw.githubusercontent.com/tstone/specs2-exter/master/doc/minimal-notifier-ss.png)

##### Use

Add the following to your `build.sbt` or `Build.scala`:

```scala
testOptions in Test += Tests.Argument("notifier org.specs2.exter.notifier.Minimal")
```


## Matchers

##### Use

Mixin the matcher trait with the Specification class to get access to matchers.

```
Exter      // all matchers
ExterSeq   // only matchers for Seq
```

##### Example:

```scala
import org.specs2.exter.matcher.Exter

class FooSpec extends SpecificationLike with Exter {
  // ...
}
```

### Included Matchers

#### containAll (Seq)

Assert that a given sequence contains all of the listed elements, regardless of order.

```scala
val seq = Seq(3, 1, 5, 4, 2)
seq must containAll(1, 2, 3) // true
seq must containAll(4, 5, 6) // false (missing 6)
seq must containAll(6, 7, 8) // false (missing all)
```

#### containExactly (Seq)

Assert that a given sequence contains all and no more of the listed elements, regardless of order.

```scala
val seq = Seq(3, 2, 1)
seq must containExactly(1, 2, 3) // true
seq must containExactly(1, 2)    // false
seq must containExactly(3, 4, 5) // false
```
#### containSome (Seq)

Assert that at least one of the listed elements is within the given sequence

```scala
val seq = Seq(2, 4, 6)
seq must containSome(6, 8, 10)  // true
seq must containSome(6, 4, 2)   // true
seq must containSome(8, 10, 12) // false
```