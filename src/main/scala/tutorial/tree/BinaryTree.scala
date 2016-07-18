package tutorial.tree

/**
 * Binary tree:
 * a set can be empty or not
 * the right node will be > of the parent
 **/

object BinaryTree {

  abstract class IntSet
  case class Empty extends IntSet
  case class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet

  def contains(set: IntSet, x: Int): Boolean = set match {
    case Empty() => false
    case NonEmpty(elem: Int, left: IntSet, right: IntSet) =>
      if (x < elem) contains(left, x)
      else if (x > elem) contains(right, x)
      else true
  }

  def incl(set: IntSet, x: Int): IntSet = set match {
    case Empty() => NonEmpty(x, new Empty, new Empty)
    case NonEmpty(elem: Int, left: IntSet, right: IntSet) =>
      if (x < elem) NonEmpty(elem, incl(left, x), right)
      else if (x > elem) new NonEmpty(elem, left, incl(right, x))
      else set
  }

  def union(first: IntSet, second: IntSet): IntSet = first match {
    case Empty() => second
    case NonEmpty(elem: Int, left: IntSet, right: IntSet) =>
      incl(union(union(left, right), second), elem)
  }

}
