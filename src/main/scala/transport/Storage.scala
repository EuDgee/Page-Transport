package transport

trait Storage {
  def get(key: String): String
  def set(key: String, value: String): Unit
  def remove(key: String): Unit
  def clear(): Unit
}
