plugins {
    kotlin("multiplatform") version "1.6.21"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(LEGACY) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-netty:1.6.7")
                implementation("io.ktor:ktor-html-builder:1.6.7")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
            }
        }
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.290-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.290-kotlin-1.6.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-css:17.0.2-pre.290-kotlin-1.6.10")
            }
        }
        val jsTest by getting
    }
}

/*
application {
    mainClass.set("org.example.application.ServerKt")
}
*/

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}


tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
    //val duplicatesStrategy = "inherit" //may need something like this but this is not correct as is
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}
