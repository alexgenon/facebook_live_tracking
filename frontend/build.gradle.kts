plugins {
    kotlin("js")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":core"))
    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:1.0.0-pre.738"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-mui-material")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-mui-icons-material")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-mui-lab")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-mui-system")
    implementation("io.ktor:ktor-client-core:2.3.12")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.12")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.12")
    implementation("io.ktor:ktor-client-websockets:2.3.12")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-js")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
    implementation(npm("@mui/material", "5.6.2"))
    implementation(npm("@mui/icons-material", "5.6.2"))
    // For styling with Emotion
    implementation(npm("@emotion/react", "11.4.1"))
    implementation(npm("@emotion/styled","11.3.0"))
}


kotlin {
   js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
            runTask {
                sourceMaps = true
                devServer = devServer.copy(
                    port = 8081
                )
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
    }
}
