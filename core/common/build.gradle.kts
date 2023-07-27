plugins {
    id(Deps.Plugins.library)
    id(Deps.Plugins.kotlinAndroid)
    id(Deps.Plugins.ksp)
    id(Deps.Plugins.serialization)
}

android {
    namespace = Config.commonName
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

dependencies{
    implementation(project(":core:model"))


    implementation(Deps.Lifecycle.viewModelKtx)
    implementation(Deps.Lifecycle.runtimeKtx)
    implementation(Deps.Lifecycle.extensions)

    implementation(Deps.Compose.dataGraphics)

    implementation(Deps.Thirdparty.paletteApi)

    // TODO Do something with tests. (in all modules)
    testImplementation(Deps.Test.junit)
    testImplementation(Deps.Test.mockk)
    testImplementation(Deps.Test.roboeletric)
    testImplementation(Deps.Test.okHttp3MockWebServer)
    androidTestImplementation(Deps.Test.androidJunit)
    androidTestImplementation(Deps.Test.espressoCore)
    androidTestImplementation(Deps.Test.composeJunit)
    debugImplementation(Deps.Test.composeDebugTooling)
    debugImplementation(Deps.Test.composeDebugManifest)
}