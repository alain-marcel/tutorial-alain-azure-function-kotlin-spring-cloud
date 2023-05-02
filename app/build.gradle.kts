plugins {
  kotlin("jvm")
  kotlin("plugin.spring")

}

apply(from = "../xproject/gradle/azure_functions_package.gradle")

dependencies {
  implementation("com.microsoft.azure.functions:azure-functions-java-library:3.0.0")
  testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
  testImplementation("org.mockito:mockito-core:5.3.1")
}
