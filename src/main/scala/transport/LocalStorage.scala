package transport

import org.scalajs.dom

class LocalStorage extends Storage {
  def get(key: String) = dom.localStorage.getItem(key)
  def set(key: String, value: String) = dom.localStorage.setItem(key, value)
  def remove(key: String) = dom.localStorage.removeItem(key)
  def clear() = dom.localStorage.clear()
}
