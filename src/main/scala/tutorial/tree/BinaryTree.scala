package tutorial.tree

import scala.xml._

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

  def descendants(set: IntSet, level: Int = 0): Int = set match {
    case Empty() => level
    case NonEmpty(elem: Int, left: IntSet, right: IntSet) => {
      val dLeft = descendants(left, level + 1)
      val dRight = descendants(right, level + 1)
      if (dLeft > dRight) dLeft else dRight
    }
  }

  def toHtml(set: IntSet, levels: Int = 0): xml.Elem = set match {
    case Empty() =>
      <div class="binary-tree node empty"><h1>\u25A1</h1></div>
    case NonEmpty(elem: Int, left: IntSet, right: IntSet) => {
      val width = 2*(math.pow(2, descendants(set)))
      <div class="binary-tree node" style={s"width: ${width}em;"}><h1>{elem.toString}</h1><div class="binary-tree children">{toHtml(left)} {toHtml(right)}</div></div>
    }
  }

}
