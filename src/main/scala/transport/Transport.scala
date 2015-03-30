package transport

import transport.Transport._

object Transport {
  type Listener = String => Unit
}

trait Transport {
  var listeners: List[Listener] = List()

  def dispatch(message: String): Unit
  def addListener(listener: Listener): Unit = listeners ::= listener
  def transferToListeners(message: String) = listeners foreach (_(message))
  def destroy(): Unit = {}
}
