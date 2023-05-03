import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  //---------- Kotlin
  // https://plugins.gradle.org/plugin/org.jetbrains.kotlin.jvm/1.8.20
  kotlin("jvm") version "1.8.21" apply false

  // https://mvnrepository.com/artifact/org.jetbrains.kotlin.plugin.spring/org.jetbrains.kotlin.plugin.spring.gradle.plugin
  kotlin("plugin.spring") version "1.8.21" apply false

  // https://mvnrepository.com/artifact/com.microsoft.azure.azurefunctions/com.microsoft.azure.azurefunctions.gradle.plugin?repo=eea-sk-public
  // https://github.com/microsoft/azure-gradle-plugins
  id("com.microsoft.azure.azurefunctions") version "1.12.1" apply false

  //---------- Spring
  // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-gradle-plugin
  id("org.springframework.boot") version "2.7.9" apply false

  // https://mvnrepository.com/artifact/io.spring.gradle/dependency-management-plugin
  id("io.spring.dependency-management") version "1.0.15.RELEASE" apply false

  // https://mvnrepository.com/artifact/org.springframework.boot.experimental/spring-boot-thin-launcher
  // https://github.com/spring-projects-experimental/spring-boot-thin-launcher
  id("org.springframework.boot.experimental.thin-launcher") version "1.0.29.RELEASE" apply false

}


allprojects {
  group = "org.example"
  version = "1.0-SNAPSHOT"

  repositories {
    gradlePluginPortal()
    mavenCentral()
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = "17"
    }
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}
