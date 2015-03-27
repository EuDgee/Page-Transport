package com.eudgee

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import org.scalajs.jquery.jQuery
import org.scalajs.dom
import dom.document

object Template extends JSApp {
  def main(): Unit = {
    jQuery(setupUI _)
  }

  def addClickedMessage(): Unit = {
    jQuery("body").append("<p>You clicked the button!</p>")
  }

  def setupUI(): Unit = {
    jQuery("<button type='button'>Click me!</button>")
      .click(addClickedMessage _)
      .appendTo(jQuery("body"))
    jQuery("body").append("<p>Hello World</p>")
  }
}
