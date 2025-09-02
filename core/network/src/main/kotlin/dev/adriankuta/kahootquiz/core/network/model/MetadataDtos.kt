package dev.adriankuta.kahootquiz.core.network.model

// Metadata section DTOs

data class MetadataDto(
    val access: AccessDto?,
    val duplicationProtection: Boolean?,
    val featuredListMemberships: List<FeaturedListMembershipDto>?,
    val lastEdit: LastEditDto?,
    val versionMetadata: VersionMetadataDto?
)

// Access settings

data class AccessDto(
    val groupRead: List<String>?,
    val folderGroupIds: List<String>?,
    val features: List<String>?
)

// Featured list membership

data class FeaturedListMembershipDto(
    val list: String?,
    val addedAt: Long?
)

// Last edit information

data class LastEditDto(
    val editorUserId: String?,
    val editorUsername: String?,
    val editTimestamp: Long?
)

// Version metadata

data class VersionMetadataDto(
    val version: Int?,
    val created: Long?,
    val creator: String?
)
