package dev.adriankuta.kahootquiz.core.network

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import dev.adriankuta.kahootquiz.core.network.models.QuizResponseDto
import org.junit.Test
import java.io.InputStreamReader

class QuizResponseDtoParsingTest {

    private fun loadSample(): QuizResponseDto {
        val stream = checkNotNull(javaClass.classLoader?.getResourceAsStream("sample_quiz.json")) {
            "sample_quiz.json not found on test classpath"
        }
        stream.use { input ->
            return Gson().fromJson(InputStreamReader(input), QuizResponseDto::class.java)
        }
    }

    @Test
    fun parses_root_fields_correctly() {
        val dto = loadSample()

        assertThat(dto.uuid).isEqualTo("fb4054fc-6a71-463e-88cd-243876715bc1")
        assertThat(dto.title).isEqualTo("Seven Wonders of the Ancient World")
        assertThat(dto.creatorUsername).isEqualTo("KahootStudio")
        assertThat(dto.creatorPrimaryUsage).isEqualTo("teacher")
        assertThat(dto.quizType).isEqualTo("quiz")
        assertThat(dto.isValid).isTrue()
        assertThat(dto.playAsGuest).isTrue()
        assertThat(dto.hasRestrictedContent).isFalse()
        assertThat(dto.created).isGreaterThan(0)
        assertThat(dto.modified).isGreaterThan(0)
    }

    @Test
    fun parses_cover_metadata_and_colors() {
        val dto = loadSample()
        val cover = checkNotNull(dto.coverMetadata)
        assertThat(cover.id).isEqualTo("0b64142f-0624-4014-9f50-b65e6be93d8f")
        assertThat(cover.contentType).isEqualTo("image/jpeg")
        assertThat(cover.extractedColors).isNotNull()
        assertThat(cover.extractedColors).hasSize(4)
        assertThat(cover.extractedColors?.first()?.swatch).isEqualTo("VIBRANT")
        assertThat(cover.blurhash).isNotNull()
        val crop = checkNotNull(cover.crop)
        assertThat(crop.circular).isFalse()
        assertThat(crop.origin?.x).isEqualTo(227)
        assertThat(crop.target?.y).isEqualTo(1299)
    }

    @Test
    fun parses_questions_and_choices() {
        val dto = loadSample()
        val questions = checkNotNull(dto.questions)
        assertThat(questions).hasSize(12)

        // First question true/false
        val q1 = questions[0]
        assertThat(q1.type).isEqualTo("quiz")
        assertThat(q1.layout).isEqualTo("TRUE_FALSE")
        assertThat(q1.choices).hasSize(2)
        assertThat(q1.choices?.get(0)?.answer).isEqualTo("True")
        assertThat(q1.choices?.get(0)?.correct).isTrue()
        assertThat(q1.choices?.get(1)?.answer).isEqualTo("False")
        assertThat(q1.choices?.get(1)?.correct).isFalse()

        // Open ended question exists and has accepted answers
        val openEnded = questions.first { it.type == "open_ended" }
        assertThat(openEnded.choices).isNotNull()
        val answers = openEnded.choices!!.map { it.answer }
        assertThat(answers).containsAtLeast("Helios", "helios")

        // Slider question has choiceRange
        val slider = questions.first { it.type == "slider" }
        val range = checkNotNull(slider.choiceRange)
        assertThat(range.start).isEqualTo(0)
        assertThat(range.end).isEqualTo(7)
        assertThat(range.step).isEqualTo(1)
        assertThat(range.correct).isEqualTo(1)
        assertThat(range.tolerance).isEqualTo(0)
    }

    @Test
    fun parses_metadata_and_channels() {
        val dto = loadSample()
        val metadata = checkNotNull(dto.metadata)
        assertThat(metadata.duplicationProtection).isTrue()
        assertThat(metadata.featuredListMemberships).isNotNull()
        assertThat(metadata.featuredListMemberships).isNotEmpty()
        assertThat(metadata.versionMetadata?.version).isEqualTo(4)
        assertThat(metadata.lastEdit?.editorUsername).isEqualTo("KahootStudio")

        val channels = checkNotNull(dto.channels)
        assertThat(channels).hasSize(1)
        assertThat(channels.first().id).isEqualTo("247c3eb4-af80-4c1f-b006-558682c7bd2f")

        assertThat(dto.languageInfo?.readAloudSupported).isTrue()
    }
}
