plugins {
    alias(libs.plugins.kahootquiz.android.library)
    alias(libs.plugins.kahootquiz.android.library.hilt)
}

android {
    namespace = "dev.adriankuta.kahootquiz.data"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.domain)
}
