package dev.adriankuta.kahootquiz.model.data.mappers

import dev.adriankuta.kahootquiz.core.network.models.AccessDto
import dev.adriankuta.kahootquiz.core.network.models.ChannelDto
import dev.adriankuta.kahootquiz.core.network.models.ChoiceDto
import dev.adriankuta.kahootquiz.core.network.models.ChoiceRangeDto
import dev.adriankuta.kahootquiz.core.network.models.ContentTagsDto
import dev.adriankuta.kahootquiz.core.network.models.CoverMetadataDto
import dev.adriankuta.kahootquiz.core.network.models.CropDto
import dev.adriankuta.kahootquiz.core.network.models.ExtractedColorDto
import dev.adriankuta.kahootquiz.core.network.models.FeaturedListMembershipDto
import dev.adriankuta.kahootquiz.core.network.models.ImageMetadataDto
import dev.adriankuta.kahootquiz.core.network.models.LanguageInfoDto
import dev.adriankuta.kahootquiz.core.network.models.LastEditDto
import dev.adriankuta.kahootquiz.core.network.models.MediaItemDto
import dev.adriankuta.kahootquiz.core.network.models.MetadataDto
import dev.adriankuta.kahootquiz.core.network.models.PointDto
import dev.adriankuta.kahootquiz.core.network.models.QuestionDto
import dev.adriankuta.kahootquiz.core.network.models.QuizResponseDto
import dev.adriankuta.kahootquiz.core.network.models.VersionMetadataDto
import dev.adriankuta.kahootquiz.core.network.models.VideoDto
import dev.adriankuta.kahootquiz.domain.models.Access
import dev.adriankuta.kahootquiz.domain.models.Channel
import dev.adriankuta.kahootquiz.domain.models.Choice
import dev.adriankuta.kahootquiz.domain.models.ChoiceRange
import dev.adriankuta.kahootquiz.domain.models.ContentTags
import dev.adriankuta.kahootquiz.domain.models.CoverMetadata
import dev.adriankuta.kahootquiz.domain.models.Crop
import dev.adriankuta.kahootquiz.domain.models.ExtractedColor
import dev.adriankuta.kahootquiz.domain.models.FeaturedListMembership
import dev.adriankuta.kahootquiz.domain.models.ImageMetadata
import dev.adriankuta.kahootquiz.domain.models.LanguageInfo
import dev.adriankuta.kahootquiz.domain.models.LastEdit
import dev.adriankuta.kahootquiz.domain.models.MediaItem
import dev.adriankuta.kahootquiz.domain.models.Metadata
import dev.adriankuta.kahootquiz.domain.models.Point
import dev.adriankuta.kahootquiz.domain.models.Question
import dev.adriankuta.kahootquiz.domain.models.Quiz
import dev.adriankuta.kahootquiz.domain.models.QuizId
import dev.adriankuta.kahootquiz.domain.models.VersionMetadata
import dev.adriankuta.kahootquiz.domain.models.Video
import kotlin.time.Duration.Companion.milliseconds

internal fun QuizResponseDto.toDomainModel(): Quiz =
    Quiz(
        id = QuizId(uuid),
        language = language,
        creator = creator,
        creatorUsername = creatorUsername,
        compatibilityLevel = compatibilityLevel,
        creatorPrimaryUsage = creatorPrimaryUsage,
        folderId = folderId,
        themeId = themeId,
        visibility = visibility,
        audience = audience,
        title = title,
        description = description,
        quizType = quizType,
        cover = cover,
        coverMetadata = coverMetadata?.toDomain(),
        questions = questions?.map { it.toDomain() } ?: emptyList(),
        contentTags = contentTags?.toDomain(),
        metadata = metadata?.toDomain(),
        resources = resources,
        slug = slug,
        languageInfo = languageInfo?.toDomain(),
        inventoryItemIds = inventoryItemIds ?: emptyList(),
        channels = channels?.map { it.toDomain() } ?: emptyList(),
        isValid = isValid,
        playAsGuest = playAsGuest,
        hasRestrictedContent = hasRestrictedContent,
        type = type,
        created = created,
        modified = modified,
    )

private fun CoverMetadataDto.toDomain(): CoverMetadata = CoverMetadata(
    id = id,
    altText = altText,
    contentType = contentType,
    origin = origin,
    externalRef = externalRef,
    resources = resources,
    width = width,
    height = height,
    extractedColors = extractedColors?.map { it.toDomain() },
    blurhash = blurhash,
    crop = crop?.toDomain(),
)

private fun ExtractedColorDto.toDomain(): ExtractedColor = ExtractedColor(
    swatch = swatch,
    rgbHex = rgbHex,
)

private fun CropDto.toDomain(): Crop = Crop(
    origin = origin?.toDomain(),
    target = target?.toDomain(),
    circular = circular,
)

private fun PointDto.toDomain(): Point = Point(x = x, y = y)

private fun ContentTagsDto.toDomain(): ContentTags = ContentTags(
    curriculumCodes = curriculumCodes,
    generatedCurriculumCodes = generatedCurriculumCodes,
)

private fun MetadataDto.toDomain(): Metadata = Metadata(
    access = access?.toDomain(),
    duplicationProtection = duplicationProtection,
    featuredListMemberships = featuredListMemberships?.map { it.toDomain() },
    lastEdit = lastEdit?.toDomain(),
    versionMetadata = versionMetadata?.toDomain(),
)

private fun AccessDto.toDomain(): Access = Access(
    groupRead = groupRead,
    folderGroupIds = folderGroupIds,
    features = features,
)

private fun FeaturedListMembershipDto.toDomain(): FeaturedListMembership = FeaturedListMembership(
    list = list,
    addedAt = addedAt,
)

private fun LastEditDto.toDomain(): LastEdit = LastEdit(
    editorUserId = editorUserId,
    editorUsername = editorUsername,
    editTimestamp = editTimestamp,
)

private fun VersionMetadataDto.toDomain(): VersionMetadata = VersionMetadata(
    version = version,
    created = created,
    creator = creator,
)

private fun LanguageInfoDto.toDomain(): LanguageInfo = LanguageInfo(
    language = language,
    lastUpdatedOn = lastUpdatedOn,
    readAloudSupported = readAloudSupported,
)

private fun ChannelDto.toDomain(): Channel = Channel(id = id)

private fun QuestionDto.toDomain(): Question = Question(
    type = type,
    question = question,
    time = time?.milliseconds,
    points = points,
    pointsMultiplier = pointsMultiplier,
    choices = choices?.map { it.toDomain() },
    layout = layout,
    image = image,
    imageMetadata = imageMetadata?.toDomain(),
    resources = resources,
    video = video?.toDomain(),
    questionFormat = questionFormat,
    languageInfo = languageInfo?.toDomain(),
    media = media?.map { it.toDomain() },
    choiceRange = choiceRange?.toDomain(),
)

private fun ChoiceDto.toDomain(): Choice = Choice(
    answer = answer,
    correct = correct,
    languageInfo = languageInfo?.toDomain(),
)

private fun VideoDto.toDomain(): Video = Video(
    id = id,
    startTime = startTime,
    endTime = endTime,
    service = service,
    fullUrl = fullUrl,
)

private fun ImageMetadataDto.toDomain(): ImageMetadata = ImageMetadata(
    id = id,
    altText = altText,
    contentType = contentType,
    origin = origin,
    externalRef = externalRef,
    resources = resources,
    width = width,
    height = height,
    effects = effects,
    crop = crop?.toDomain(),
)

private fun MediaItemDto.toDomain(): MediaItem = MediaItem(
    type = type,
    zIndex = zIndex,
    isColorOnly = isColorOnly,
    id = id,
    altText = altText,
    contentType = contentType,
    origin = origin,
    externalRef = externalRef,
    resources = resources,
    width = width,
    height = height,
    crop = crop?.toDomain(),
)

private fun ChoiceRangeDto.toDomain(): ChoiceRange = ChoiceRange(
    start = start,
    end = end,
    step = step,
    correct = correct,
    tolerance = tolerance,
)
