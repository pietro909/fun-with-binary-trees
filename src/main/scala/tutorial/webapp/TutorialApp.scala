package tutorial.webapp

import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.document
import tutorial.tree.BinaryTree._
import scala.xml
import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom.html

object TutorialApp extends JSApp {

  var theTree: IntSet = Empty()

  def main(): Unit = {
    val a = union(Empty(), NonEmpty(2, NonEmpty(5, Empty(), Empty()), NonEmpty(6, Empty(), Empty())))
    val i = incl(incl(a, 3), 7)
    val w = NonEmpty(0, NonEmpty(76, Empty(), Empty()), NonEmpty(10, NonEmpty(15, Empty(), Empty()), NonEmpty(46, Empty(), Empty())))
    theTree = union(NonEmpty(12, NonEmpty(1, Empty(), Empty()), Empty()), w)
    drawTree(theTree)
  }

  def drawTree(tree: IntSet) = {
    val content = toHtml(tree).toString
    val newNode = document.createElement("section")
    newNode.innerHTML = content
    new dom.ext.PimpedNodeList(document.body.querySelectorAll("section")).foreach( document.body.removeChild )
    document.body.appendChild(newNode)
  }

  @JSExport
  def addNode() = {
    val nodeValue = document.getElementById("nodeValue").asInstanceOf[html.Input].value.toInt
    theTree = incl(theTree, nodeValue)
    drawTree(theTree)
  }

}
