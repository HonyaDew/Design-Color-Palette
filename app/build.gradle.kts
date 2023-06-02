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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Deps.Core.Version.composeCompiler
    }
    packagingOptions {
        exclude("META-INF/gradle/incremental.annotation.processors")
    }

    configurations {
        create("cleanedAnnotations")
        implementation {
            exclude(group = "com.intellij", module = "annotations")
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(Deps.Core.coreKts)
    implementation(Deps.Core.appCompat)
    implementation(Deps.Core.androidMaterial)
    implementation(Deps.Core.activityCompose)

    implementation(Deps.Compose.uiTooling)
    implementation(Deps.Compose.uiToolingPreview)
    implementation(Deps.Compose.composeMaterial)
    implementation(Deps.Compose.composeMaterialWindow)

    implementation(Deps.Lifecycle.viewModelKtx)
    implementation(Deps.Lifecycle.runtimeKtx)
    implementation(Deps.Lifecycle.extensions)

    implementation(Deps.Navigation.navigationCompose)
    implementation(Deps.Navigation.lifecycleViewModelCompose)

    implementation(Deps.Room.runtime)
    implementation(Deps.Room.ktx)
    implementation(Deps.Room.compiler)

    implementation(Deps.Koin.koin)
    implementation(Deps.Koin.compose)

    testImplementation(Deps.Test.junit)
    testImplementation(Deps.Test.roboeletric)
    testImplementation(Deps.Test.mockk)
    testImplementation(Deps.Test.okHttp3MockWebServer)
    androidTestImplementation(Deps.Test.androidJunit)
    androidTestImplementation(Deps.Test.espressoCore)
    androidTestImplementation(Deps.Test.composeJunit)
    debugImplementation(Deps.Test.composeDebugTooling)
    debugImplementation(Deps.Test.composeDebugManifest)

}