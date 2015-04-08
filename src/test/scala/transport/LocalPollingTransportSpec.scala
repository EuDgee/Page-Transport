package transport

import utest._
import storage.LocalStorage

object LocalPollingTransportSpec extends TestSuite {
  implicit def transportBuilder: () => Transport = () => {
    val storage = new LocalStorage()
    storage.clear()
    new PollingTransport(storage)
  }
  def tests = TransportTest.test()
}
