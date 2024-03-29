plugins {
    val kotlinVersion="1.6.21"
    val quarkusPlatformVersion="2.11.1.Final"
    kotlin("multiplatform") version kotlinVersion apply false
    kotlin("js") version kotlinVersion apply false
    kotlin("jvm") version kotlinVersion apply false
    kotlin("plugin.serialization") version kotlinVersion apply false
    kotlin("plugin.allopen") version kotlinVersion apply false
    id("io.quarkus") version quarkusPlatformVersion apply false
}

