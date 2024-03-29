/*
 * This file was generated by the Gradle 'init' task.
 */

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project
val kotlinVersion: String by project

plugins {
    kotlin("jvm")
    kotlin("plugin.allopen")
    kotlin("plugin.serialization")
    id("io.quarkus")
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
}


dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    /*implementation("io.quarkus:quarkus-reactive-pg-client")
    implementation("io.quarkus:quarkus-jdbc-postgresql")
    implementation("io.quarkus:quarkus-hibernate-orm-rest-data-panache")
    implementation("io.quarkus:quarkus-hibernate-reactive-panache")*/
    implementation("io.quarkus:quarkus-rest-client-reactive")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("io.quarkus:quarkus-resteasy-reactive-qute")
    implementation("io.quarkus:quarkus-resteasy-reactive-kotlin-serialization")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.quarkus:quarkus-websockets")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-openshift")
    implementation("io.quarkus:quarkus-opentelemetry-exporter-otlp")
    implementation("io.quarkus:quarkus-micrometer-registry-prometheus")
    implementation(project(":core"))
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.rest-assured:kotlin-extensions")
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.javaParameters = true
}


tasks {
    processResources {
        dependsOn(":frontend:browserWebpack")
        from(project(":frontend").buildDir.resolve("distributions"))  {
            into("META-INF/resources")
        }
    }
}

publishing{
  if (System.getenv("CI") != null) {
      repositories {
        maven {
         name = "GitHubPackages"
          url = uri("https://maven.pkg.github.com/alexgenon/facebook_live_tracking")
          credentials {
            username = System.getenv("GITHUB_ACTOR")
            password = System.getenv("GITHUB_TOKEN")
          }
       }
     }
  }
}
