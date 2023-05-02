package org.example

import com.microsoft.azure.functions.*
import com.microsoft.azure.functions.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

/**
 * Azure Functions with HTTP Trigger.
 */

@Component
class ExampleAzureFunction {

  @Autowired
  lateinit var exampleFunction: ExampleFunction

  /**
   * This function listens at endpoint "/api/HttpTrigger-Java". Two ways to invoke it using "curl" command in bash:
   * 1. curl -d "HTTP Body" {your host}/api/HttpTrigger-Java&code={your function key}
   * 2. curl "{your host}/api/HttpTrigger-Java?name=HTTP%20Query&code={your function key}"
   * Function Key is not needed when running locally, it is used to invoke function deployed to Azure.
   * More details: https://aka.ms/functions_authorization_keys
   */
  @FunctionName(ExampleFunction.FUNCTION_NAME)
  fun execute(
    @HttpTrigger(
      name = "req",
      methods = [HttpMethod.GET, HttpMethod.POST],
      authLevel = AuthorizationLevel.FUNCTION
    )
    request: HttpRequestMessage<Optional<String>>,
    context: ExecutionContext
  ): HttpResponseMessage {

    context.logger.info("HTTP trigger processed a ${request.httpMethod.name} request.")

    val query = request.queryParameters["name"]
    val name = request.body.orElse(query)

    println("================================ HELLO ========================")
    name?.let {
      return request
        .createResponseBuilder(HttpStatus.OK)
        .body(this.exampleFunction.apply(name))
        .build()
    }

    return request
      .createResponseBuilder(HttpStatus.BAD_REQUEST)
      .body("Please pass a name on the query string or in the request body")
      .build()
  }

}
