package dev.adriankuta.kahootquiz.model.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.adriankuta.kahootquiz.domain.repositories.QuizRepository
import dev.adriankuta.kahootquiz.model.data.QuizRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsQuizRepository(
        quizRepositoryImpl: QuizRepositoryImpl,
    ): QuizRepository
}
