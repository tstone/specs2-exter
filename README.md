# Exter

A collection of matchers and language enhancements to add a bit of polish to your specs2 specs.


------------------------------------------------------------------------


## Build & Use

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

------------------------------------------------------------------------


## Matchers

For all matchers, add the trait `Exter` to the specification class.  To pick and choose matchers, add the trait for the matchers desired.


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
