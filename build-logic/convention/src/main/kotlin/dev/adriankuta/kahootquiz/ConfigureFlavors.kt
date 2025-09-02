package dev.adriankuta.kahootquiz

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

@Suppress("EnumEntryName")
enum class FlavorDimension {
    environment
}

// The content for the app can either come from local static data which is useful for demo
// purposes, or from a production backend server which supplies up-to-date, real content.
// These two product flavors reflect this behaviour.
@Suppress("EnumEntryName")
enum class PartyManiaFlavor(
    val dimension: FlavorDimension,
    val applicationIdSuffix: String? = null,
) {
    dev(FlavorDimension.environment, ".dev"),
    prod(FlavorDimension.environment)
}

fun Project.configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        flavorDimensions += FlavorDimension.environment.name
        productFlavors {
            PartyManiaFlavor.values().forEach {
                create(it.name) {
                    dimension = it.dimension.name
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (it.applicationIdSuffix != null) {
                            applicationIdSuffix = it.applicationIdSuffix
                        }
                    }
                }
            }
        }
    }
}
