pluginManagement {
  repositories {
    gradlePluginPortal()
    maven {
      url = uri("https://repo.spring.io/plugins-snapshot")
    }
  }
}

rootProject.name = "tutorial-alain-azure-function-kotlin-spring-cloud"

include( "app")
