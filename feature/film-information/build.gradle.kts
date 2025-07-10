import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.ansssiaz.feature.film_information"
    compileSdk = 36

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_11)
    }
}

dependencies {
    implementation(project(":component:theme"))
    implementation(project(":component:ui-components"))
    implementation(project(":shared:film"))
    implementation(project(":data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    //Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)

    //Загрузка изображений
    implementation(libs.coil)

    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //DI
    implementation(libs.koin.android.compose)
    implementation(libs.koin.android)
    implementation(libs.koin.core)
}