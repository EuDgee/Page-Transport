package transport

import utest._

object StorageTest {
  def test()(implicit storageBuilder: () => Storage) = TestSuite {
    "Get and set" - {
      val storage = storageBuilder()
      val values = ("key1", "value1") :: ("_sdf}", "\\\"\'}|") :: Nil
      values foreach (tuple => {
        val (key, value) = tuple
        storage.set(key, value)
        assert(storage.get(key) == Some(value))
      })
    }

    "Remove" - {
      val storage = storageBuilder()
      storage.set("key", "value")
      storage.set("key2", "value2")
      storage.remove("key")
      assert(storage.get("key") == None)
      assert(storage.get("key2") == Some("value2"))
    }

    "Clear" - {
      val storage = storageBuilder()
      storage.set("key", "value")
      storage.set("key2", "value2")
      storage.clear()
      assert(storage.get("key") == None)
      assert(storage.get("key2") == None)
    }
  }
}
