allprojects {
    group = "be.aufildemescoutures"
    version = "1.0.0-SNAPSHOT"
}

plugins {
	val kotlinVersion = "1.6.21"
    kotlin("multiplatform") version kotlinVersion apply false
    kotlin("jvm") version kotlinVersion apply false
    kotlin("js") version kotlinVersion apply false
    kotlin("plugin.allopen") version kotlinVersion apply false

}
