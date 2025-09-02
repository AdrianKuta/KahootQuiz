package dev.adriankuta.kahootquiz.core.network.models

import com.google.gson.annotations.SerializedName

// Root response for a Kahoot quiz details (network layer DTO)
data class QuizResponseDto(
    val uuid: String?,
    val language: String?,
    val creator: String?,
    @SerializedName("creator_username") val creatorUsername: String?,
    val compatibilityLevel: Int?,
    @SerializedName("creator_primary_usage") val creatorPrimaryUsage: String?,
    val folderId: String?,
    val themeId: String?,
    val visibility: Int?,
    val audience: String?,
    val title: String?,
    val description: String?,
    val quizType: String?,
    val cover: String?,
    val coverMetadata: CoverMetadataDto?,
    val questions: List<QuestionDto>?,
    val contentTags: ContentTagsDto?,
    val metadata: MetadataDto?,
    val resources: String?,
    val slug: String?,
    val languageInfo: LanguageInfoDto?,
    val inventoryItemIds: List<String>?,
    val channels: List<ChannelDto>?,
    val isValid: Boolean?,
    val playAsGuest: Boolean?,
    val hasRestrictedContent: Boolean?,
    val type: String?,
    val created: Long?,
    val modified: Long?
)
