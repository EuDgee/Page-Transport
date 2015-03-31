package transport

import storage.LocalStorage
import utest._

object LocalPollingTransportSpec extends TestSuite {
  implicit def transportBuilder: () => Transport = () => {
    val storage = new LocalStorage()
    storage.clear()
    new PollingTransport(storage)
  }
  def tests = TransportTest.test()
}
