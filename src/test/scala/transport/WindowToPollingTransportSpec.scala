package transport

import utest._

object WindowToPollingTransportSpec extends TestSuite {
  implicit def transportBuilder: () => Transport = () => new WindowToPollingTransport()
  def tests = TransportTest.test()
}
