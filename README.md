# KahootQuiz — Interview Challenge

This project is an implementation for an interview-style challenge. It demonstrates a clean, modular
Android architecture with a focus on separation of concerns, convention plugins for Gradle, and
pragmatic Kotlin usage.

## TL;DR

- Only image media is supported right now.
- Slider question type is not supported.
- There is no end/completion screen yet.
- Errors in ViewModels are caught but not yet handled (no user-facing error states/actions).

## Project Overview

- Multi-module, clean architecture:
    - `core/` — common utilities (e.g., networking).
    - `domain/` — pure domain models and repository abstractions, domain models.
    - `data/` — repository implementations, mappers.
    - `ui/` — feature UI modules (e.g., `ui/quiz`).
- Convention plugins are used to centralize and reuse Gradle configuration across modules (see
  `build-logic/`).
- Kotlin-first approach using language features to keep code concise and readable.

## How to Build & Run

1. Requirements:
    - Android Studio
    - JDK 21
    - Gradle wrapper included
2. Steps:
    - Open the project in Android Studio.
    - Sync Gradle.
    - Run the `app` configuration on a device/emulator.

If you prefer the command line: `./gradlew assembleDebug` and then install the generated APK.

## Architecture Details

- Data flow follows a standard clean pattern:
    - `domain.repositories.QuizRepository` defines the contract.
    - `data.QuizRepositoryImpl` uses `core.network.retrofit.QuizApi` + mappers to produce
      `domain.models.Quiz`.
    - UI consumes domain via ViewModels and exposes a `UiState`.
- The code emphasizes separation of concerns and testability.

## Current Limitations & Known Issues

- Media support:
    - Only `image` media is supported in the quiz content.
    - Other media types are not supported.
- Question types:
    - Slider answers are not supported yet.
- UX flow:
    - There is no end/completion screen after the quiz finishes.
- Error handling:
    - Exceptions are caught in ViewModels but not handled (no retry, no error UI, no telemetry hooks
      yet).

## Suggested Improvements

1. Introduce a UI-specific model for the Quiz screen
    - The domain model `Quiz` is relatively complex and currently used directly in `UiState`.
    - Add a dedicated, lean UI data class that contains only the data relevant to the quiz screen.
    - Benefits: Improved clarity for UI developers, simpler previews, easier testing/mocking, and
      better forward-compatibility when domain evolves.

2. Expand Unit Test Coverage
    - Currently there is only one unit test for parsing a sample JSON API response.
    - Add tests for:
        - ViewModel state transitions (loading/success/error).
        - Mapping edge cases (e.g., missing fields, unsupported media types).
        - Navigation/flow for various question types.

3. Error Handling Strategy
    - Map exceptions to user-friendly UI states with retry actions.
    - Add telemetry/logging hooks for observability.

4. Feature Completeness
    - Implement slider answer type.
    - Add an end/completion screen with score summary and restart/share options.
    - Consider support for additional media types (video/audio), with graceful fallbacks.
5. Transitions between questions could be more smooth.

## Extra: Related Work I Can Share

I can share more complex code from my private app that is published on the Google Play Store.
Additionally, I have a secondary project — an AI Agent implemented in TypeScript using Google’s
GenKit framework — that prepares content for that app. It leverages multiple models, vector stores,
and embeddings to orchestrate cooperative behaviors.

If you’re interested, I can provide a deeper walkthrough, architectural diagrams, or selected code
excerpts.

## License

This repository is provided as-is for interview and demonstration purposes.
