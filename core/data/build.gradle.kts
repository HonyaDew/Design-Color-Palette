plugins {
    id(Deps.Plugins.library)
    id(Deps.Plugins.kotlinAndroid)
    id(Deps.Plugins.ksp)
}

android {
    namespace = Config.dataName
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:model"))

    implementation(Deps.Core.coreKts)
    implementation(Deps.Core.appCompat)
    implementation(Deps.Core.androidMaterial)

    implementation(Deps.Json.serialization)

    implementation(Deps.Compose.dataGraphics)

    implementation(Deps.Room.ktx)
    ksp(Deps.Room.compiler)
    implementation(Deps.Room.runtime)

    androidTestImplementation(Deps.Test.androidJunit)
    androidTestImplementation(Deps.Test.espressoCore)
}