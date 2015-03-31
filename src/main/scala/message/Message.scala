package message

import upickle._

object Message {
  def deserialize(serialized: String) = read[Message](serialized)
}

case class Message(event: String, data: String) {
  def serialize: String = write(this)
}
