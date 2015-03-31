package transport

import utest._

object ExternalTransportSpec extends TestSuite {
  implicit def transportBuilder: () => Transport = () => {
    val localTransport = new LocalTransport()
    new ExternalTransport(localTransport.dispatch, localTransport.addListener,
                          localTransport.destroy)
  }
  def tests = TransportTest.test()
}
