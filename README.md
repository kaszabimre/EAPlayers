# EAPlayers

![GitHub Actions build status](https://github.com/kaszabimre/EAPlayers/actions/workflows/PR.yml/badge.svg)

![iOS](https://img.shields.io/badge/iOS-000000?style=for-the-badge&logo=ios&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white)

EAPlayers is a Kotlin Multiplatform project that leverages **Compose Multiplatform** for both Android and iOS to provide a fully native mobile app experience.

### Screenshots

|                      Android                       |                      iOS                       |                      Desktop                       |
|:--------------------------------------------------:|:----------------------------------------------:|:--------------------------------------------------:|
| <img src="/screenshots/android.gif" width="250" /> | <img src="/screenshots/ios.gif" width="250" /> | <img src="/screenshots/desktop.gif" width="250" /> |

---

### About the project

EAPlayers is a showcase of the latest Kotlin Multiplatform technologies for mobile development using a fully Compose-based UI framework for both Android and iOS. Key features include:

- 🧩 **Shared ViewModels** for business logic across platforms
- 🖼️ **Compose Multiplatform-based UI**, allowing the views to be shared across Android and iOS for a consistent user experience
- 🧳 **Shared navigation** implementation using Compose Multiplatform, enabling a unified navigation experience on both platforms
- 🖥️ **Compose for Desktop** support
- 🏗️ **Multi-modular architecture** for improved maintainability, scalability, and build efficiency
- 📡 **Fetching player details** and team information from [EA's Drop API](https://drop-api.ea.com)
- 🚦 **Detekt** for static code analysis and maintaining code quality
- 🛠️ **Code Style and Inspections** to enforce consistent formatting and static analysis
- ⚙️ **GitHub Actions** for continuous integration to ensure code quality
- 🌙 **Dark mode** support for both platforms
- 💉 [Koin](https://github.com/InsertKoinIO/koin) - Dependency injection ![GitHub stars](https://img.shields.io/github/stars/InsertKoinIO/koin?style=social)
- 🌎 [Ktor](https://github.com/ktorio/ktor) - Network communication ![GitHub stars](https://img.shields.io/github/stars/ktorio/ktor?style=social)
- 📦 [Coil](https://github.com/coil-kt/coil) - Image loading ![GitHub stars](https://img.shields.io/github/stars/coil-kt/coil?style=social)
- 📋 [Kermit](https://github.com/touchlab/Kermit) - Logging ![GitHub stars](https://img.shields.io/github/stars/touchlab/Kermit?style=social)


### IDE Compatibility

- **Android Studio**: Koala | 2024.1.1 Patch 2
- **Xcode**: Version 16.0

<details>
<summary><strong>Setting up Code Style and Inspections in IDEA / Android Studio</strong></summary>

### CodeStyle
1. Import code style: **Android Studio** -> **Settings** -> **Editor** -> **Code style**.
   * At the Scheme section click the settings gear
   * Choose **Import scheme...**
   * Browse the code style config file at *./config/codestyle.xml*
2. Hit **Apply**. You can now use **Reformat code** that complies to the standards.

### Inspections

1. Import inspections: **Android Studio** -> **Settings** -> **Editor** -> **Inspections**.
   * At the Profile section click the settings gear
   * Choose **Import profile...**
   * Browse the inspections config file at *./config/inspections.xml*
2. Hit **Apply**.
</details>

<details>
<summary><strong>Desktop Support</strong></summary>

The project includes desktop support using **Compose for Desktop**. This allows the application to run natively, providing a consistent user experience across mobile and desktop environments.

To run the desktop version of the app, follow these steps:

1. Ensure you have the necessary environment set up, including a compatible JDK and Kotlin Multiplatform dependencies.
2. Use the following command to build and run the desktop application:

   ```bash
   ./gradlew composeApp:run
   ```

3. The application will start up and can be tested on your local machine.

</details>

<details>
<summary><strong>Detekt</strong></summary>

- 🚦 The project uses [Detekt](https://github.com/detekt/detekt) for static code analysis and formatting.
- Detekt helps maintain code quality by identifying potential issues in the codebase.
- To run Detekt, use the following command:

```bash
  ./gradlew detekt
  ```

---
</details>

<details>
<summary><strong>Theming</strong></summary>

The app implements a custom theme system to support dynamic theming and dark mode. The theme includes dimensions, colors, typography, shapes, and ripple effects, all of which can adapt to the current system theme (light or dark). The `AppTheme` composable allows you to toggle between light and dark themes and provides a `MaterialTheme` wrapper to ensure consistent appearance throughout the app.

The color scheme is dynamically generated based on the current theme settings, and additional customization can be applied using local providers for text selection colors, ripple effects, and typography. This ensures a cohesive look and feel while leveraging the power of Compose's Material 3 design.

</details>

<details>
<summary><strong>Multi-modular Architecture</strong></summary>

The project follows a multi-modular architecture to enhance scalability, maintainability, and build efficiency. The modularization approach is as follows:

- **Features Module**: The app's features are divided into separate modules like `details` and `list`, each containing a `view` for the UI and a `viewmodel` for the presentation logic. This helps isolate individual features and facilitates independent development and testing.

- **Core Module**: Houses core utilities and extensions used throughout the app.

- **Data Module**: Manages data sources, repositories, and API interactions. It serves as a centralized module for managing data flow.

- **DI Module**: Contains the dependency injection setup using Koin to provide a modular and easily configurable DI configuration.

- **Domain Module**: Encapsulates the business logic and use cases of the application, separating it from the data handling and UI layers.

- **Logger Module**: Implements logging functionalities using Kermit, enabling consistent logging across the entire codebase.

- **Navigation Module**: Manages navigation logic for both Android and iOS, making it easier to handle cross-platform navigation requirements.

- **Theme Module**: Contains all theming-related resources, such as color schemes, typography, dimensions, and shape configurations, for a consistent visual experience.

The multi-modular structure helps in isolating functionalities, making the codebase more manageable, and accelerating build times by reducing the impact of changes on other parts of the project.

</details>

<details>
<summary><strong>Dependency Management</strong></summary>

The project uses [Renovate](https://github.com/apps/renovate) for automated dependency updates. Renovate helps keep dependencies up-to-date by regularly checking for new versions and creating pull requests for updates.

The configuration file for Renovate is located in the root of the project: [renovate.json](./renovate.json).

</details>

<details>
<summary><strong>Contributing</strong></summary>

Contributions are welcome! Here’s the process for contributing to EAPlayers:

1. Check the [issues](https://github.com/kaszabimre/EAPlayers/issues) section on the repository. There are various issues listed that need help.

2. To work on an issue, create a new branch from the `development` branch named after the corresponding issue.

3. Once you have a solution, run the `./gradlew build` command locally to ensure that the build is successful.

4. If the build passes, create a pull request targeting the `development` branch. Make sure to assign the pull request to [@kaszabimre](https://github.com/kaszabimre) for approval.

5. Once the pull request is approved, the solution will be considered successfully integrated.

</details>

<details>
<summary><strong>Commit Message Conventions</strong></summary>

The project follows the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) standard for commit messages. This convention is important for maintaining consistent commit history and will be utilized in the future for generating release logs.

Please ensure that your commit messages adhere to the specified format to facilitate automated release note generation.

</details>

<details>
<summary><strong>GitHub Actions</strong></summary>

The project utilizes GitHub Actions for continuous integration (CI) to ensure code quality and maintainability. The primary workflow is defined as a "PR job," which is triggered on the following events:

- Manual trigger (`workflow_dispatch`)
- Pushes to the `main` or `development` branches
- Pull requests, excluding changes to markdown files, images, and documentation

The workflow consists of several jobs:

1. **Pre-conditions**:
    - Sets up the environment by checking out the repository, configuring the JDK (Java 18), caching Kotlin Multiplatform dependencies, running `detekt` for static code analysis, and executing SwiftLint for iOS-specific code.

2. **Build Android**:
    - Depends on the "pre-conditions" job.
    - Involves setting up the environment and building the Android project using Gradle.

3. **Build iOS**:
    - Depends on the "pre-conditions" job.
    - Configures the environment, selects the Xcode version, resolves Swift package dependencies, and builds the iOS app using `xcodebuild`. It targets an iOS Simulator with the specified configuration.

#### Kotlin Multiplatform Cache Action

A custom action is used for caching Gradle and Kotlin Native artifacts to speed up the build process. The action caches directories such as Gradle caches, wrapper files, and Kotlin Native dependencies. The cache keys are based on the content of Gradle build files, ensuring cache consistency across builds.

</details>
