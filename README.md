# EAPlayers

EAPlayers is a Kotlin Multiplatform project that leverages **Compose Multiplatform** for both Android and iOS to provide a fully native mobile app experience. The project uses [EA's Drop API](https://drop-api.ea.com) to fetch player information.

![GitHub Actions build status](https://github.com/kaszabimre/EAPlayers/actions/workflows/PR.yml/badge.svg)

![iOS](https://img.shields.io/badge/iOS-000000?style=for-the-badge&logo=ios&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white)

<img src="/screenshots/eaplayers.gif" width="300" />

### About the project

EAPlayers is a showcase of the latest Kotlin Multiplatform technologies for mobile development using a fully Compose-based UI framework for both Android and iOS. Key features include:

- **Shared ViewModels** for business logic across platforms
- **Fetching player details** and team information from [EA's Drop API](https://drop-api.ea.com)
- **Dark mode** support for both platforms
- **Koin** for dependency injection
- **Ktor** for networking and API communication
- **Coil** for image loading in a multiplatform environment
- **Detekt** for static code analysis

### Screenshots
*(Screenshots coming soon)*

---

### Libraries

Here are the primary libraries and tools used in the project:

- 🌎 [Ktor](https://github.com/ktorio/ktor) - Network communication
  [![GitHub Repo stars](https://img.shields.io/github/stars/ktorio/ktor)](https://github.com/ktorio/ktor)
- 📦 [Coil](https://github.com/coil-kt/coil) - Image loading
  [![GitHub Repo stars](https://img.shields.io/github/stars/coil-kt/coil)](https://github.com/coil-kt/coil)
- 💉 [Koin](https://github.com/InsertKoinIO/koin) - Dependency injection
  [![GitHub Repo stars](https://img.shields.io/github/stars/InsertKoinIO/koin)](https://github.com/InsertKoinIO/koin)
- 📋 [Kermit](https://github.com/touchlab/Kermit) - Logging
  [![GitHub Repo stars](https://img.shields.io/github/stars/touchlab/Kermit)](https://github.com/touchlab/Kermit)
- 🚦 Static analysis and formatting with [Detekt](https://github.com/detekt/detekt)
  [![GitHub Repo stars](https://img.shields.io/github/stars/detekt/detekt)](https://github.com/detekt/detekt)
  ```bash
  ./gradlew detekt
  ```

---

### API

This project fetches player data from EA's Drop API. You can learn more about the API here: [EA Drop API](https://drop-api.ea.com).

---
