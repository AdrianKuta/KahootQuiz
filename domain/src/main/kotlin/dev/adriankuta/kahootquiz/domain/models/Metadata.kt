package dev.adriankuta.kahootquiz.domain.models

// Metadata section domain models

data class Metadata(
    val access: Access?,
    val duplicationProtection: Boolean?,
    val featuredListMemberships: List<FeaturedListMembership>?,
    val lastEdit: LastEdit?,
    val versionMetadata: VersionMetadata?,
)