package transport

import utest._

object LocalTransportSpec extends TestSuite {
  implicit def transportBuilder: () => Transport = () => new LocalTransport()
  def tests = TransportTest.test()
}
