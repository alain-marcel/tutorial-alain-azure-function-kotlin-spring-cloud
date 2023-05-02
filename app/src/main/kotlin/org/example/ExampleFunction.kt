package org.example

import org.springframework.stereotype.Component

@Component(ExampleFunction.FUNCTION_NAME)
class ExampleFunction : java.util.function.Function<String, String> {

  override fun apply(name: String): String {
    return "Hello from " + name
  }

  companion object {

    const val FUNCTION_NAME = "HttpTrigger-Java"
  }
}
