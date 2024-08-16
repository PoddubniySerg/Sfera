plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.googleDevtoolsKsp)
}

android {
    namespace = "ru.zavod.app_di"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

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
        kotlinCompilerExtensionVersion = "1.5.11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

//    ViewModel utilities for Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
//    Dagger
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
//    Retrofit (api loader) - для получения OkhttpClient в RemoteCoreRepository
    implementation(libs.retrofit)

    api(project(":app-core"))
    implementation(project(":app-navigation"))
    implementation(project(":data-api"))
    implementation(project(":data-storage"))
    implementation(project(":feature-onboarding"))
    implementation(project(":feature-auth"))
    implementation(project(":feature-chats"))
    implementation(project(":feature-profile"))
    implementation(project(":feature-settings"))
}