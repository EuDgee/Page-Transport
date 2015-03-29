package transport

import org.scalajs.dom

class PollingTransport(storage: Storage, pollingTimeout: Int = 100) extends Transport {
  val MESSAGE_KEY = "message"

  dom.setTimeout(() => {
    triggerListeners()
    dom.setTimeout(triggerListeners _, pollingTimeout)
  }, pollingTimeout)

  def triggerListeners(): Unit = {
    val message = storage.get(MESSAGE_KEY)
    println("get", message)
    if (message != null) {
      println("size!: " + listeners.size)
      transferToListeners(message)
      storage.remove(MESSAGE_KEY)
    }
  }

  def dispatch(message: String): Unit = {
    storage.set(MESSAGE_KEY, message)
    println("set", message)
  }
}
