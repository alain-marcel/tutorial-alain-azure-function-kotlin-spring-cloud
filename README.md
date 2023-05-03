
This project is a tutorial for using Azure Gradle Plugin with spring cloud azure.


- It is a multi-project, with only one module called app.
- The code is written in kotlin language
- There are two branches:
  - main :
    - It does not use spring-cloud
    - Message is generated  BUT gradle task azureFunctionsPackage is ok
  - spring-cloud-azure
    - It uses spring cloud
    - Message is generated BUT gradle task azureFunctionsPackage is ko
- I use gradle 8.1.1, kotlin 1.8.21, plugin com.microsoft.azure.azurefunctions 1.12.1

Download the project from git.
Open the project with Intellij for example.


# 1 Branch main

### Procedure to reproduce issue

- Switch to main branch (by default).
- Start gradle task : azurefunctions/azureFunctionsPackage
- Notes:
  - The message is generated but task is ok :
  - gradle task azureFunctionsRun allows to locally execute the azure function, and it is ok
    The azure function respond "Hello: bob!" to the http request http://localhost:7071/api/HttpTrigger-Java?name=bob

### Log

```
14:24:51: Executing 'azureFunctionsPackage'...


> Configure project :app
default messager has already been registered

> Task :app:processResources NO-SOURCE
> Task :app:compileKotlin
> Task :app:compileJava NO-SOURCE
> Task :app:classes UP-TO-DATE
> Task :app:jar
> Task :app:assemble

> Task :app:azureFunctionsPackage
The following dependencies could not be found, please check whether related modules have been packaged
 	[C:\Dac\Work\Projects\tutorial-alain-azure-function-kotlin-spring-cloud\app\build\classes\java\main]

Step 1 of 8: Searching for Azure Functions entry points
1 Azure Functions entry point(s) found.

Step 2 of 8: Generating Azure Functions configurations
Generation done.

Step 3 of 8: Validating generated configurations
Validation done.

Step 4 of 8: Saving host.json
Successfully saved to C:\Dac\Work\Projects\tutorial-alain-azure-function-kotlin-spring-cloud\app\build\azure-functions\app-1.0-SNAPSHOT\host.json

Step 5 of 8: Saving local.settings.json
Successfully saved to C:\Dac\Work\Projects\tutorial-alain-azure-function-kotlin-spring-cloud\app\build\azure-functions\app-1.0-SNAPSHOT\local.settings.json

Step 6 of 8: Saving configurations to function.json
Starting processing function: HttpTrigger-Java
Successfully saved to C:\Dac\Work\Projects\tutorial-alain-azure-function-kotlin-spring-cloud\app\build\azure-functions\app-1.0-SNAPSHOT\HttpTrigger-Java\function.json

Step 7 of 8: Copying JARs to staging directory: C:\Dac\Work\Projects\tutorial-alain-azure-function-kotlin-spring-cloud\app\build\azure-functions\app-1.0-SNAPSHOT
Copied successfully.

Step 8 of 8: Installing function extensions if needed
Skip install Function extension for HTTP Trigger Functions
Successfully built Azure Functions.

BUILD SUCCESSFUL in 9s
3 actionable tasks: 3 executed
14:25:01: Execution finished 'azureFunctionsPackage'.
```

# 2 Branch spring-cloud-azure

### Procedure to reproduce issue

- Switch to spring-cloud-azure branch.
- Start gradle task : azurefunctions/azureFunctionsRun
- Note :
  - There are two issues
    - the generated message
    - the application is running but when the http GET request (http://localhost:7071/api/HttpTrigger-Java?name=bob) is sent
      then following exception is thrown:
      UninitializedPropertyAccessException: lateinit property exampleFunction has not been initialized


### Log

```
14:16:14: Executing 'azureFunctionsRun'...


> Configure project :app
default messager has already been registered

> Task :app:compileKotlin UP-TO-DATE
> Task :app:compileJava NO-SOURCE
> Task :app:processResources UP-TO-DATE
> Task :app:classes UP-TO-DATE
> Task :app:thinPom UP-TO-DATE
> Task :app:bootJarMainClassName UP-TO-DATE
> Task :app:bootJar
> Task :app:jar UP-TO-DATE
> Task :app:thinJar
> Task :app:assemble

> Task :app:azureFunctionsPackage
The following dependencies could not be found, please check whether related modules have been packaged
 	[C:\Dac\Work\Projects\tutorial-alain-azure-function-kotlin-spring-cloud\app\build\classes\java\main]

Step 1 of 8: Searching for Azure Functions entry points
1 Azure Functions entry point(s) found.

Step 2 of 8: Generating Azure Functions configurations
Generation done.

Step 3 of 8: Validating generated configurations
Validation done.

Step 4 of 8: Saving host.json
Successfully saved to C:\Dac\Work\Projects\tutorial-alain-azure-function-kotlin-spring-cloud\app\build\azure-functions\app-1.0-SNAPSHOT\host.json

Step 5 of 8: Saving local.settings.json
Successfully saved to C:\Dac\Work\Projects\tutorial-alain-azure-function-kotlin-spring-cloud\app\build\azure-functions\app-1.0-SNAPSHOT\local.settings.json

Step 6 of 8: Saving configurations to function.json
Starting processing function: HttpTrigger-Java
Successfully saved to C:\Dac\Work\Projects\tutorial-alain-azure-function-kotlin-spring-cloud\app\build\azure-functions\app-1.0-SNAPSHOT\HttpTrigger-Java\function.json

Step 7 of 8: Copying JARs to staging directory: C:\Dac\Work\Projects\tutorial-alain-azure-function-kotlin-spring-cloud\app\build\azure-functions\app-1.0-SNAPSHOT
Copied successfully.

Step 8 of 8: Installing function extensions if needed
Skip install Function extension for HTTP Trigger Functions
Successfully built Azure Functions.

> Task :app:azureFunctionsPackageZip
default to NULL OperationContext, because operation or its action operation is null:null
The following dependencies could not be found, please check whether related modules have been packaged
 	[C:\Dac\Work\Projects\tutorial-alain-azure-function-kotlin-spring-cloud\app\build\classes\java\main]
default to NULL OperationContext, because operation or its action operation is null:null
Azure Function App's staging directory found at: C:\Dac\Work\Projects\tutorial-alain-azure-function-kotlin-spring-cloud\app\build\azure-functions\app-1.0-SNAPSHOT
default to NULL OperationContext, because operation or its action operation is null:null
Build zip from staging folder successfully: C:\Dac\Work\Projects\tutorial-alain-azure-function-kotlin-spring-cloud\app\build\azure-functions\app-1.0-SNAPSHOT.zip

> Task :app:azureFunctionsRun
The following dependencies could not be found, please check whether related modules have been packaged
 	[C:\Dac\Work\Projects\tutorial-alain-azure-function-kotlin-spring-cloud\app\build\classes\java\main]
Azure Function App's staging directory found at: C:\Dac\Work\Projects\tutorial-alain-azure-function-kotlin-spring-cloud\app\build\azure-functions\app-1.0-SNAPSHOT

Azure Functions Core Tools
Core Tools Version:       4.0.4915 Commit hash: N/A  (64-bit)
Function Runtime Version: 4.14.0.19631

[2023-05-03T12:16:23.968Z] OpenJDK 64-Bit Server VM warning: Options -Xverify:none and -noverify were deprecated in JDK 13 and will likely be removed in a future release.

Functions:

	HttpTrigger-Java: [GET,POST] http://localhost:7071/api/HttpTrigger-Java

For detailed output, run func with --verbose flag.
[2023-05-03T12:16:24.031Z] Listening for transport dt_socket at address: 5005
[2023-05-03T12:16:26.508Z] Worker process started and initialized.
[2023-05-03T12:16:28.696Z] Host lock lease acquired by instance ID '00000000000000000000000053D20CB4'.
[2023-05-03T12:16:30.138Z] Executing 'Functions.HttpTrigger-Java' (Reason='This function was programmatically called via the host APIs.', Id=6f52057c-39d8-449c-a5b1-88b31eee2037)
[2023-05-03T12:16:30.275Z] ================================ HELLO ========================
[2023-05-03T12:16:30.289Z] HTTP trigger processed a GET request.
[2023-05-03T12:16:30.328Z] Executed 'Functions.HttpTrigger-Java' (Failed, Id=6f52057c-39d8-449c-a5b1-88b31eee2037, Duration=220ms)
[2023-05-03T12:16:30.328Z] System.Private.CoreLib: Exception while executing function: Functions.HttpTrigger-Java. System.Private.CoreLib: Result: Failure
Exception: UninitializedPropertyAccessException: lateinit property exampleFunction has not been initialized
Stack: java.lang.reflect.InvocationTargetException
[2023-05-03T12:16:30.328Z] 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
[2023-05-03T12:16:30.328Z] 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
[2023-05-03T12:16:30.328Z] 	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
[2023-05-03T12:16:30.328Z] 	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
[2023-05-03T12:16:30.328Z] 	at com.microsoft.azure.functions.worker.broker.JavaMethodInvokeInfo.invoke(JavaMethodInvokeInfo.java:22)
[2023-05-03T12:16:30.328Z] 	at com.microsoft.azure.functions.worker.broker.EnhancedJavaMethodExecutorImpl.execute(EnhancedJavaMethodExecutorImpl.java:22)
[2023-05-03T12:16:30.329Z] 	at com.microsoft.azure.functions.worker.chain.FunctionExecutionMiddleware.invoke(FunctionExecutionMiddleware.java:19)
[2023-05-03T12:16:30.329Z] 	at com.microsoft.azure.functions.worker.chain.InvocationChain.doNext(InvocationChain.java:21)
[2023-05-03T12:16:30.329Z] 	at com.microsoft.azure.functions.worker.broker.JavaFunctionBroker.invokeMethod(JavaFunctionBroker.java:80)
[2023-05-03T12:16:30.329Z] 	at com.microsoft.azure.functions.worker.handler.InvocationRequestHandler.execute(InvocationRequestHandler.java:37)
[2023-05-03T12:16:30.329Z] 	at com.microsoft.azure.functions.worker.handler.InvocationRequestHandler.execute(InvocationRequestHandler.java:12)
[2023-05-03T12:16:30.329Z] 	at com.microsoft.azure.functions.worker.handler.MessageHandler.handle(MessageHandler.java:44)
[2023-05-03T12:16:30.329Z] 	at com.microsoft.azure.functions.worker.JavaWorkerClient$StreamingMessagePeer.lambda$onNext$0(JavaWorkerClient.java:93)
[2023-05-03T12:16:30.329Z] 	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
[2023-05-03T12:16:30.329Z] 	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
[2023-05-03T12:16:30.329Z] 	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
[2023-05-03T12:16:30.329Z] 	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
[2023-05-03T12:16:30.330Z] 	at java.base/java.lang.Thread.run(Thread.java:833)
[2023-05-03T12:16:30.330Z] Caused by: kotlin.UninitializedPropertyAccessException: lateinit property exampleFunction has not been initialized
[2023-05-03T12:16:30.330Z] 	at org.example.ExampleAzureFunction.getExampleFunction(ExampleAzureFunction.kt:17)
[2023-05-03T12:16:30.330Z] 	at org.example.ExampleAzureFunction.execute(ExampleAzureFunction.kt:46)
[2023-05-03T12:16:30.330Z] 	... 18 more
[2023-05-03T12:16:30.330Z] .
```
