package dev.adriankuta.kahootquiz.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.adriankuta.kahootquiz.core.network.BuildConfig
import dev.adriankuta.kahootquiz.core.network.retrofit.QuizApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.KAHOOT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesQuizApi(retrofit: Retrofit): QuizApi =
        retrofit.create(QuizApi::class.java)
}
