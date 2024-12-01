plugins {
  id("buildlogic.kotlin-application-conventions")
  id("com.gradleup.shadow") version "8.3.5"
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(project(":bunnylang"))
}

application {
  // Define the main class for the application.
  mainClass = "net.tkbunny.bunnylang.app.AppKt"
}

/*
apply(plugin("com.gradleup.shadow"))
Task.withType<ShadowJar> {
  archiveFileName = "bunnylang-cli.jar"
}
*/

