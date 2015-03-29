package transport

import utest._

object LocalStorageSpec extends TestSuite {
  implicit def storageBuilder: () => Storage = () => {
    val storage = new LocalStorage()
    storage.clear()
    storage
  }
  def tests = StorageTest.test()
}
