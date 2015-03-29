package transport

class LocalTransport extends Transport {
  def dispatch(message: String) = transferToListeners(message)
}
