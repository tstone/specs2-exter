# Exter

### Extra goodies for Specs2

----------------------------------------------------------------------

## Matchers

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
