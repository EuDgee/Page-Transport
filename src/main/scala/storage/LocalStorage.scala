package storage

import org.scalajs.dom

class LocalStorage extends Storage {
  def get(key: String) = {
    val value = dom.localStorage.getItem(key)
    value match {
      case null => None
      case s => Some(s)
    }
  }
  def set(key: String, value: String) = dom.localStorage.setItem(key, value)
  def remove(key: String) = dom.localStorage.removeItem(key)
  def clear() = dom.localStorage.clear()
}
