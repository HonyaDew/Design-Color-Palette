plugins {
    id(Deps.Plugins.library)
    id(Deps.Plugins.kotlinAndroid)
    id(Deps.Plugins.ksp)
}

android {
    namespace = Config.imageName
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

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
    implementation(project(":core:model"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
    implementation(project(":core:common"))

    implementation(Deps.Thirdparty.imagePicker)

    implementation(Deps.Compose.uiTooling)
    implementation(Deps.Compose.uiToolingPreview)
    implementation(Deps.Compose.composeMaterial)
    implementation(Deps.Compose.composeMaterialWindow)

    implementation(Deps.Lifecycle.viewModelKtx)
    implementation(Deps.Lifecycle.runtimeKtx)
    implementation(Deps.Lifecycle.extensions)

    implementation(Deps.Koin.koin)
    implementation(Deps.Koin.compose)

    testImplementation(Deps.Test.jUnit)
    testImplementation(Deps.Test.mockitoCore)
    testImplementation(Deps.Test.mockitoKotlin)
    testImplementation(Deps.Test.mockitoInline)
    testRuntimeOnly(Deps.Test.mockitoRuntimeOnly)
}