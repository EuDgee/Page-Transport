package transport

import org.scalajs.dom

class CrossWindowTransport extends Transport {
  val ORIGIN = "*"
  val EVENT = "message"
  var destroyed = false
  val listener = (event: dom.Event) => {
    if (!destroyed) event match {
      case e: dom.MessageEvent => transferToListeners(e.data.toString)
    }
  }

  dom.window.addEventListener(EVENT, this.listener)

  def dispatch(message: String) = {
    dom.window.postMessage(message, ORIGIN)
  }

  override def destroy() = {
    dom.window.removeEventListener(EVENT, this.listener)
    destroyed = true
  }
}
