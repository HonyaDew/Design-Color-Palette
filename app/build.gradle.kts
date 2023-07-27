plugins {
    id(Deps.Plugins.application)
    id(Deps.Plugins.kotlinAndroid)
    id(Deps.Plugins.ksp)
}

android {
    namespace = Config.appName
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.appName
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Deps.Core.Version.composeCompiler
    }
    packagingOptions {
        resources {
            excludes += setOf("META-INF/gradle/incremental.annotation.processors")
        }
    }

    configurations {
        create("cleanedAnnotations")
        implementation {
            exclude(group = "com.intellij", module = "annotations")
        }
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))

    implementation(project(":feature:harmony"))
    implementation(project(":feature:image"))
    implementation(project(":feature:palette"))
    implementation(project(":feature:saved"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:sliders"))

    implementation(Deps.Core.coreKts)
    implementation(Deps.Core.appCompat)
    implementation(Deps.Core.androidMaterial)
    implementation(Deps.Core.activityCompose)
    implementation(Deps.Core.accompanist)

    implementation(Deps.Compose.uiTooling)
    implementation(Deps.Compose.uiToolingPreview)
    implementation(Deps.Compose.composeMaterial)
    implementation(Deps.Compose.composeMaterialWindow)

    implementation(Deps.Lifecycle.viewModelKtx)
    implementation(Deps.Lifecycle.runtimeKtx)
    implementation(Deps.Lifecycle.extensions)

    implementation(Deps.Navigation.navigationCompose)
    implementation(Deps.Navigation.lifecycleViewModelCompose)

    implementation(Deps.Json.serialization)

    implementation(Deps.Room.runtime)
    implementation(Deps.Room.ktx)
    implementation(Deps.Room.compiler)

    implementation(Deps.Koin.koin)
    implementation(Deps.Koin.compose)

    implementation(Deps.Thirdparty.imageCrop)
    implementation(Deps.Thirdparty.imagePicker)
    implementation(Deps.Thirdparty.paletteApi)
    implementation(Deps.Thirdparty.splashScreen)

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