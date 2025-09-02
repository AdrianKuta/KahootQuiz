package dev.adriankuta.kahootquiz.core.network.retrofit

import dev.adriankuta.kahootquiz.core.network.models.QuizResponseDto
import retrofit2.http.GET

interface QuizApi {

    @GET("/rest/kahoots/fb4054fc-6a71-463e-88cd-243876715bc1")
    suspend fun getQuiz(): QuizResponseDto
}
