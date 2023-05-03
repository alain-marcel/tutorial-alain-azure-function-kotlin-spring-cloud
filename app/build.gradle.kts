plugins {
  kotlin("jvm")
  kotlin("plugin.spring")

  id("org.springframework.boot")

}

apply(from = "../xproject/gradle/spring_app.gradle")
apply(from = "../xproject/gradle/azure_functions_package.gradle")

dependencies {

  //---------- SpringBoot BOM
  // https://github.com/Azure/azure-sdk-for-java/wiki/Spring-Versions-Mapping
  // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-dependencies
  // https://search.maven.org/artifact/org.springframework.boot/spring-boot-dependencies
  implementation("org.springframework.boot:spring-boot-dependencies:2.7.9")

  //---------- SpringCloud BOM
  // https://spring.io/projects/spring-cloud
  // https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-dependencies
  // https://search.maven.org/artifact/org.springframework.cloud/spring-cloud-dependencies
  implementation("org.springframework.cloud:spring-cloud-dependencies:2021.0.5")


  //---------- Spring Cloud Azure Dependencies
  // https://github.com/Azure/azure-sdk-for-java/wiki/Spring-Versions-Mapping
  // https://mvnrepository.com/artifact/com.azure.spring/spring-cloud-azure-dependencies
  // https://search.maven.org/artifact/com.azure.spring/spring-cloud-azure-dependencies
  implementation("com.azure.spring:spring-cloud-azure-dependencies:4.6.0")


  //----------
  implementation("org.springframework.boot:spring-boot-starter-webflux:2.7.9")
  implementation("org.springframework.cloud:spring-cloud-starter-task:2.4.6")

  implementation("com.microsoft.azure.functions:azure-functions-java-library:3.0.0")

  testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
  testImplementation("org.mockito:mockito-core:5.3.1")
  testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.9")

}
