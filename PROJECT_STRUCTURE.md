# iOS Gallery Android - Project Structure

## Cấu trúc thư mục cần upload lên GitHub:

```
iOS-Gallery/
├── .github/
│   └── workflows/
│       ├── android-build.yml      ✅ Main build workflow
│       └── android-ci.yml         ✅ CI workflow
├── app/
│   ├── build.gradle               ✅ App build configuration
│   ├── proguard-rules.pro         ✅ ProGuard rules
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml ✅ App manifest
│           ├── java/com/iosgallery/android/
│           │   ├── MainActivity.kt           ✅ Main activity
│           │   ├── ImageViewerActivity.kt    ✅ Image viewer
│           │   ├── adapters/
│           │   │   ├── PhotoAdapter.kt       ✅ Photo grid adapter
│           │   │   ├── RecentDayAdapter.kt   ✅ Recent days adapter
│           │   │   ├── PersonAdapter.kt      ✅ People adapter
│           │   │   └── FullscreenPhotoAdapter.kt ✅ Fullscreen adapter
│           │   └── models/
│           │       ├── Photo.kt              ✅ Photo data model
│           │       ├── RecentDay.kt          ✅ Recent day model
│           │       └── Person.kt             ✅ Person model
│           └── res/
│               ├── layout/
│               │   ├── activity_main.xml             ✅ Main layout
│               │   ├── activity_image_viewer.xml     ✅ Image viewer layout
│               │   ├── item_photo.xml               ✅ Photo item layout
│               │   ├── item_recent_day.xml          ✅ Recent day layout
│               │   ├── item_person.xml              ✅ Person item layout
│               │   └── item_fullscreen_photo.xml    ✅ Fullscreen layout
│               ├── values/
│               │   ├── colors.xml            ✅ iOS color scheme
│               │   ├── strings.xml           ✅ App strings
│               │   ├── themes.xml            ✅ Material theme
│               │   └── dimens.xml            ✅ Dimensions
│               └── drawable/
│                   └── ic_launcher_foreground.xml ✅ App icon
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar        ✅ Gradle wrapper jar
│       └── gradle-wrapper.properties ✅ Gradle wrapper config
├── build.gradle                      ✅ Root build file
├── gradle.properties                 ✅ Gradle properties
├── settings.gradle                   ✅ Settings
├── gradlew                          ✅ Gradle wrapper (Unix)
├── gradlew.bat                      ✅ Gradle wrapper (Windows)
├── README.md                        ✅ Project documentation
├── replit.md                        ✅ Replit documentation
├── GITHUB_PUSH_GUIDE.md            ✅ Push guide
├── BUILD_TROUBLESHOOTING.md        ✅ Build troubleshooting
├── UPLOAD_GUIDE.md                 ✅ Upload guide
└── PROJECT_STRUCTURE.md            ✅ This file
```

## Files quan trọng nhất cần upload:

### 1. GitHub Actions (Required for auto-build)
- `.github/workflows/android-build.yml`
- `.github/workflows/android-ci.yml`

### 2. Build Configuration (Required)
- `build.gradle` (root)
- `app/build.gradle`
- `gradle.properties`
- `settings.gradle`
- `gradle/wrapper/gradle-wrapper.properties`

### 3. Source Code (Required)
- `app/src/main/AndroidManifest.xml`
- `app/src/main/java/com/iosgallery/android/MainActivity.kt`
- `app/src/main/java/com/iosgallery/android/ImageViewerActivity.kt`
- All adapter và model files

### 4. Resources (Required)
- All XML layouts trong `app/src/main/res/layout/`
- Colors, strings, themes trong `app/src/main/res/values/`

### 5. Gradle Wrapper (Required)
- `gradlew`
- `gradlew.bat`
- `gradle/wrapper/gradle-wrapper.jar`

## Verification Checklist:

After upload, check:
✅ GitHub Actions tab shows workflows  
✅ Build automatically starts after upload  
✅ APK artifacts are generated  
✅ No build errors in Actions logs  

Đảm bảo upload đầy đủ các files này để GitHub Actions có thể build thành công!