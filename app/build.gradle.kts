plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    //Hilt
    kotlin("kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.enterprise.appstockmarket"
    compileSdk = 34

    //Data Binding and View Binding
    buildFeatures {
        dataBinding = true
    }

    defaultConfig {
        applicationId = "com.enterprise.appstockmarket"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

//Hilt
kapt {
    correctErrorTypes = true
}

dependencies {

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //Gson
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //CSV
    implementation("com.opencsv:opencsv:5.5.2")

    //ViewModelScope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")


    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}