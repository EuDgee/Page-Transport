package storage

trait Storage {
  def get(key: String): Option[String]
  def set(key: String, value: String): Unit
  def remove(key: String): Unit
  def clear(): Unit
}
