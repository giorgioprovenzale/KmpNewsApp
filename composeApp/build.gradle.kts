import com.codingfeline.buildkonfig.compiler.FieldSpec.Type
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.konan.properties.Properties

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.15.1")
    }
}

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    id("com.codingfeline.buildkonfig") version "+"
    kotlin("plugin.serialization") version "1.9.21" //decompose step2
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true

            export("com.arkivanov.decompose:decompose:2.2.2-compose-experimental")
            export("com.arkivanov.essenty:lifecycle:1.3.0")
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)

            //decompose step3
            implementation("com.arkivanov.decompose:decompose:2.2.2-compose-experimental")
            implementation("com.arkivanov.decompose:extensions-compose-jetbrains:2.2.2-compose-experimental")

        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.logging)

            api(libs.image.loader)

            implementation("com.arkivanov.decompose:decompose:2.2.2-compose-experimental")
            implementation("com.arkivanov.decompose:extensions-compose-jetbrains:2.2.2-compose-experimental")
            //decompose step1

            //koin step1
            implementation("io.insert-koin:koin-core:3.5.3")
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)

            //koin step2
            implementation("io.insert-koin:koin-android:3.5.3")

        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)

            api("com.arkivanov.decompose:decompose:2.2.2-compose-experimental")
            api("com.arkivanov.essenty:lifecycle:1.3.0")

        }
    }
}

buildkonfig {
    packageName = "com.jpmobilelab.kmp.newsapp"
    // objectName = "YourAwesomeConfig"
    // exposeObjectWithName = "YourAwesomePublicConfig"

    val props = Properties()

    try {
        props.load(file("secrets.properties").inputStream())
    } catch (e: Exception) {
        // keys are private and can not be comitted to git
    }

    defaultConfigs {

        buildConfigField(
            Type.STRING,
            "API_KEY",
            props["API_KEY"]?.toString()
        )
    }
}

android {
    namespace = "com.jpmobilelab.kmp.newsapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.jpmobilelab.kmp.newsapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}




