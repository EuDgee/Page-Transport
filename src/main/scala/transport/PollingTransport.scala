package transport

import org.scalajs.dom
import storage.Storage
import scala.scalajs.js.Math
import upickle._

class PollingTransport(storage: Storage, pollingTimeout: Int = 100) extends Transport {
  val PREFIX = "p"
  val IDS_KEY = PREFIX + "msgs"

  val timer = dom.setInterval(() => {
    triggerListeners()
  }, pollingTimeout)

  def triggerListeners(): Unit = {
    storage.get(IDS_KEY).foreach(messages => {
      read[List[String]](messages).foreach((id) => {
        storage.get(id).foreach(message => {
          dom.setTimeout(() => transferToListeners(message), 0)
          storage.remove(id)
        })
      })
      storage.remove(IDS_KEY)
    })
  }

  def dispatch(message: String): Unit = {
    val messageIds = storage.get(IDS_KEY) match {
      case None => List()
      case Some(s) => read[List[String]](s)
    }

    val id = PREFIX + Math.random().toString.substring(2, 8)
    storage.set(id, message)
    storage.set(IDS_KEY, write(id :: messageIds))
  }

  override def destroy() = {
    dom.clearInterval(timer)
  }
}
