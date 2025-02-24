plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

dependencies {
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.darwin)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.logging)

    implementation(libs.koin)
    implementation(libs.kotlinx.serialization.json)
    implementation(project(":dmain"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}