# iOS Gallery Android - Project Documentation

## Project Overview
This is an Android gallery application inspired by the iOS Photos app interface. The app features a modern dark theme, grid-based photo layout, recent days grouping, and full-screen photo viewing capabilities with zoom functionality.

## Project Architecture

### Technology Stack
- **Language**: Kotlin
- **UI Framework**: Android Views with ViewBinding
- **Image Loading**: Glide library for efficient image loading and caching
- **Photo Viewing**: PhotoView library for zoom/pan functionality in fullscreen
- **Permissions**: Dexter library for simplified runtime permission handling
- **Build System**: Gradle with Android plugin

### Key Components
- **MainActivity**: Main gallery interface with photo grid and sections
- **ImageViewerActivity**: Full-screen photo viewer with navigation
- **Adapters**: PhotoAdapter, RecentDayAdapter, PersonAdapter, FullscreenPhotoAdapter
- **Models**: Photo, RecentDay, Person data classes
- **UI**: iOS-inspired dark theme with proper color scheme

### Features Implemented
- Photo grid layout matching iOS Photos app
- Recent Days section with date-grouped photos
- People & Pets section (placeholder structure)
- Full-screen photo viewer with zoom capabilities
- Runtime permission handling for photo access
- GitHub Actions CI/CD pipeline for automated builds

## CI/CD Configuration

### GitHub Actions Workflows
1. **android-ci.yml**: Continuous integration for main/develop branches
   - Builds debug APK
   - Runs unit tests
   - Uploads artifacts

2. **build-release.yml**: Release builds for tags and release branches
   - Supports signed and unsigned builds
   - Generates APK and AAB files
   - Automatic versioning and artifact upload

### Required Secrets for Signed Builds
- KEYSTORE_FILE (base64 encoded)
- KEYSTORE_PASSWORD
- SIGNING_KEY_ALIAS  
- SIGNING_KEY_PASSWORD

## Recent Changes
- **2025-07-28**: Initial project creation
  - Created complete Android project structure
  - Implemented iOS-like UI design with dark theme
  - Added photo grid, recent days, and people sections
  - Integrated Glide for image loading with kapt
  - Configured GitHub Actions CI/CD pipeline
  - Added proper permission handling for photo access
  - Created comprehensive README and documentation

- **2025-07-28**: Fixed GitHub Actions build issues
  - Updated workflow to use JDK 17 Temurin distribution
  - Added Gradle caching for faster builds
  - Fixed signing configurations for optional keystore
  - Simplified build process with stacktrace debugging
  - Created optimized android-build.yml workflow
  - Fixed layout issues and dependency conflicts

- **2025-07-28**: Fixed Gradle wrapper checksum issue
  - Updated distributionSha256Sum to correct checksum
  - Created minimal-build.yml with gradle-build-action
  - Removed problematic caching from workflows
  - Added wrapper validation step

## User Preferences
- Language: Vietnamese (user communicated in Vietnamese)
- Preferred interface style: iOS-like design with dark theme
- Focus on automated CI/CD pipeline without manual intervention

## Project Status
- ✅ Complete Android project structure created
- ✅ iOS-inspired UI design implemented
- ✅ Photo loading and display functionality
- ✅ Full-screen photo viewer with zoom
- ✅ GitHub Actions CI/CD pipeline configured
- ✅ Permission handling implemented
- ✅ Documentation completed

## Next Steps Consideration
- Test the build pipeline by pushing to GitHub
- Add more advanced photo organization features
- Implement photo editing capabilities
- Add cloud storage integration options