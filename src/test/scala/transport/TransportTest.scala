package transport

import java.util.concurrent.TimeoutException

import org.scalajs.dom
import transport.Transport._
import utest._
import utest.ExecutionContext.RunNow

import scala.concurrent.{Future, Promise}

object TransportTest {
  def listener(promise: Promise[Unit], message: String): Listener = (received) => {
    promise.success {assert(received == message)}
  }

  def createPromise(name: String): Promise[Unit] = {
    val promise = Promise[Unit]()

    val timer = dom.setTimeout(() => promise.failure(
        new TimeoutException("Listener for " + name + " hasn`t been called.")),
        1000)

    promise.future foreach ((_) => dom.clearTimeout(timer))
    promise
  }

  def finishTest(transport: Transport, future: Future[_]) = {
    future.map((_) => transport.destroy())
  }

  def test()(implicit transportBuilder: (() => Transport)) = TestSuite {
    "A listener get called for a dispatch" - {
      val transport = transportBuilder()
      val message = "message1"

      val promise = createPromise("promise")

      transport.addListener(listener(promise, message))
      transport.dispatch(message)
      finishTest(transport, promise.future)
    }

    "Multiple listeners get called for a dispatch" - {
      val transport = transportBuilder()
      val message = "messago"

      val promise1 = createPromise("promise1")
      val promise2 = createPromise("promise2")

      assert(transport.listeners.size == 0)
      transport.addListener(listener(promise1, message))
      transport.addListener(listener(promise2, message))
      assert(transport.listeners.size == 2)
      transport.dispatch(message)

      finishTest(transport,
          Future.sequence(List(promise2.future, promise2.future)))
    }

    "A listener get called for each dispatch" - {
      val transport = transportBuilder()
      val messages = ("mesago1", createPromise("first time")) ::
          ("another-one2", createPromise("second time")) :: Nil

      def listener(received: String): Unit = {
        messages.foreach((m) => {
          val (message, promise) = m
          if (message == received) {
            promise.success {}
          }
        })
      }

      transport.addListener(listener)
      val futures = messages.map((tuple) => {
        val (message, promise) = tuple
        transport.dispatch(message)
        promise.future
      })

      Future.sequence(futures)
    }
  }
}
