plugins {
    val kotlinVersion="2.0.21"
    val quarkusPlatformVersion="3.20.0"
    kotlin("multiplatform") version kotlinVersion apply false
    kotlin("js") version kotlinVersion apply false
    kotlin("jvm") version kotlinVersion apply false
    kotlin("plugin.serialization") version kotlinVersion apply false
    kotlin("plugin.allopen") version kotlinVersion apply false
    id("io.quarkus") version quarkusPlatformVersion apply false
}

