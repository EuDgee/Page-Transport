package transport

import utest._

object MessagePackerSpec extends TestSuite {
  def tests = TestSuite {
    "Pack and unpack events" - {
      val message1 = Message("event", "data")
      val message2 = Message("{symbo_} ls\\/", "{}}//\n sdf")

      assert(message1 == Message.deserialize(message1.serialize))
      assert(message2 == Message.deserialize(message2.serialize))
    }
  }
}
