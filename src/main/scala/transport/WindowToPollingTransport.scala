package transport

import storage.LocalStorage

class WindowToPollingTransport extends Transport {
  val crossWindowTransport = new CrossWindowTransport()
  val localPollingTransport = new PollingTransport(new LocalStorage())

  crossWindowTransport.addListener(message => {
    dispatch(message)
  })

  def dispatch(message: String) = {
    transferToListeners(message)
    localPollingTransport.dispatch(message)
    crossWindowTransport.dispatch(message)
  }

  override def destroy() = {
    localPollingTransport.destroy()
    crossWindowTransport.destroy()
  }
}
