package transport

import utest._

object CrossWindowTransportSpec extends TestSuite {
  implicit def transportBuilder: () => Transport = () => new CrossWindowTransport()
  def tests = TransportTest.test()
}
