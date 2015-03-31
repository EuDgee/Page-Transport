package transport

class ExternalTransport(send: String => Unit,
                        subscribe: (String => Unit) => Unit,
                        destroyTransport: () => Unit) extends Transport {

  subscribe(listener)

  def listener(message: String): Unit = {
    transferToListeners(message)
  }

  def dispatch(message: String) = {
    send(message)
  }

  override def destroy() = {
    destroyTransport()
  }
}
