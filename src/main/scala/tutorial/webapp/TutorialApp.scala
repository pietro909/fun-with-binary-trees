package tutorial.webapp

import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.document
import tutorial.tree.BinaryTree._
import scala.xml

object TutorialApp extends JSApp {

  def main(): Unit = {
    appendPar(document.body, "Hello, world!")
    val a = NonEmpty(2, Empty(), Empty())
    val i = incl(incl(a, 3), 7)
    val ne = NonEmpty(12, NonEmpty(1, Empty(), Empty()), Empty())
    val s = toHtml(union(i, ne))
    appendXml(document.body, s)
    println(s)
  }

  private def doSpaces(max: Int)(n: Int) = "  " * (max - n)

  def displayTree(tree: IntSet): String = {
    def traverse(node: IntSet, depth: Int): List[(String, Int)] =
      node match {
        case Empty() => (".", depth) :: List((" ", depth + 1), (" ", depth + 1))
        case NonEmpty(elem: Int, left: IntSet, right: IntSet) =>
          (elem.toString, depth) :: (traverse(left, depth + 1) ::: traverse(right, depth + 1))
      }
    val nodes = traverse(tree, 1).groupBy(_._2).map(e => (e._1, e._2.map(_._1).mkString("   "*e._1))).toList.sorted //.mkString("\n")
    def doS = doSpaces(nodes.last._1)_
    val result = nodes.map(r => doS(r._1) + r._2).mkString("\n")
    result.toString
  }

  def appendXml(targetNode: dom.Node, content: xml.Elem): dom.Node = {
    val newNode = document.createElement("section")
    newNode.innerHTML = content.toString
    targetNode.appendChild(newNode)
    targetNode
  }

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }

}
