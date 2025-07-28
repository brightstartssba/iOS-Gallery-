# iOS Gallery Android

An Android gallery application inspired by iOS Photos app design, featuring a modern dark theme and intuitive user interface.

## Features

- **iOS-like Interface**: Clean, modern design matching iOS Photos app aesthetics
- **Photo Grid View**: Responsive grid layout showing all photos from device storage  
- **Recent Days**: Grouped photos by date with card-based layout
- **People & Pets**: Circular photo thumbnails for quick access (placeholder)
- **Full-screen Viewer**: Immersive photo viewing with zoom and pan capabilities
- **Permissions Handling**: Proper runtime permission requests for photo access
- **Dark Theme**: Beautiful dark theme matching iOS design language

## Technical Stack

- **Language**: Kotlin
- **UI Framework**: Android Views with ViewBinding
- **Image Loading**: Glide for efficient image loading and caching
- **Photo Viewing**: PhotoView for zoom/pan functionality  
- **Permissions**: Dexter for simplified permission handling
- **Architecture**: MVVM pattern with Repository pattern
- **Build System**: Gradle with Kotlin DSL

## Screenshots

The app replicates the iOS Photos interface with:
- Grid layout for all photos
- Recent Days section with date-grouped cards
- People & Pets circular thumbnails
- Full-screen photo viewer with controls

## CI/CD Pipeline

This project includes GitHub Actions workflows for:

### Android CI (`android-ci.yml`)
- Runs on push to `main` and `develop` branches
- Builds debug APK
- Runs unit tests
- Uploads APK artifacts

### Release Build (`build-release.yml`)  
- Triggered by tags or release branches
- Supports both signed and unsigned builds
- Generates both APK and AAB files
- Automatic artifact upload with versioning

## Setup Instructions

### Prerequisites
- Android SDK 24+ (Android 7.0)
- JDK 17
- Gradle 8.0+

### Local Development
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run on device or emulator

### CI/CD Setup
To enable signed releases, add these secrets to your GitHub repository:

- `KEYSTORE_FILE`: Base64 encoded keystore file
- `KEYSTORE_PASSWORD`: Keystore password  
- `SIGNING_KEY_ALIAS`: Key alias
- `SIGNING_KEY_PASSWORD`: Key password

## Permissions

The app requires the following permissions:
- `READ_EXTERNAL_STORAGE` (Android 12 and below)
- `READ_MEDIA_IMAGES` (Android 13+)

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## Build & Release

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build  
```bash
./gradlew assembleRelease
```

### Generate AAB
```bash
./gradlew bundleRelease
```

The CI/CD pipeline automatically handles building and releasing when code is pushed to release branches or tagged versions.