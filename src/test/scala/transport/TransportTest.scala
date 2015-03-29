package transport

import java.util.concurrent.TimeoutException

import org.scalajs.dom
import transport.Transport._
import utest._
import utest.ExecutionContext.RunNow

import scala.concurrent.{Future, Promise}

object TransportTest {
  def listener(promise: Promise[Unit], message: String): Listener = (received) => {
    promise.success {
      assert(received == message)
    }
  }

  def createPromise(name: String): Promise[Unit] = {
    val promise = Promise[Unit]()

    val timer = dom.setTimeout(
      () => promise.failure(new TimeoutException("Listener for " + name + " hasn`t been called.")),
      1000)

    promise.future foreach ((_) => dom.clearTimeout(timer))
    promise
  }

  def test()(implicit transportBuilder: (() => Transport)) = TestSuite {
    "Listener get called for a dispatch" - {
      val testedTransport = transportBuilder()
      val message = "message1"

      val promise = createPromise("promise")

      testedTransport.addListener(listener(promise, message))
      testedTransport.dispatch(message)
      promise.future
    }

    "Multiple listeners get called for a dispatch" - {
      val testedTransport = transportBuilder()
      val message = "msdfklsnd gks45\\dsa"

      val promise1 = createPromise("promise1")
      val promise2 = createPromise("promise2")

      testedTransport.addListener(listener(promise1, message))
      testedTransport.addListener(listener(promise2, message))
      assert(testedTransport.listeners.size == 2)
      println("size: " + testedTransport.listeners.size)
      testedTransport.dispatch(message)

      Future.sequence(List(promise1.future, promise2.future))
    }
  }
}
