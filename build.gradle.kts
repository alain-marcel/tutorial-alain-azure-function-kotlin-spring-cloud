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
