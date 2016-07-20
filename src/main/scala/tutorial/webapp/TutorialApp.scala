package tutorial.webapp

import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.document
import tutorial.tree.BinaryTree._
import scala.xml

object TutorialApp extends JSApp {

  def main(): Unit = {
    appendPar(document.body, "Hello, world!")
    val a = NonEmpty(2, NonEmpty(5, Empty(), Empty()), NonEmpty(6, Empty(), Empty()))
    val i = incl(incl(a, 3), 7)
    val w = NonEmpty(0, NonEmpty(76, Empty(), Empty()), NonEmpty(10, NonEmpty(15, Empty(), Empty()), NonEmpty(46, Empty(), Empty())))
    val ne = NonEmpty(12, NonEmpty(1, Empty(), Empty()), Empty())
    val s = toHtml(union(w, union(i, ne)))
    val s2 = toHtml(a)
    appendXml(document.body, s)
    println(s)
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
