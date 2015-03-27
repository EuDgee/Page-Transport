package com.eudgee

import utest._
import org.scalajs.jquery.jQuery

object HelloTest extends TestSuite {
  Template.setupUI()

  def tests = TestSuite {
    'HelloWorld {
      assert(jQuery("p:contains('Hello World')").length == 1)
    }

    'ButtonClick {
      def messageCount = jQuery("p:contains('You clicked the button!')").length

      val button = jQuery("button:contains('Click me!')")
      assert(button.length == 1)
      assert(messageCount == 0)

      for (i <- 1 to 5) {
        button.click()
        assert(messageCount == i)
      }
    }

    'AddTheCode {
      assert(false)
    }
  }
}
