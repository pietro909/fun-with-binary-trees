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
