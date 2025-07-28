# Android Build Fix - GitHub Actions APK Build Error

## Phân tích lỗi từ logs:

### Lỗi chính:
```
com.iosgallery.android.app-main-31:/drawable/ic_launcher_foreground.xml:21: error: attribute android:cx not found.
com.iosgallery.android.app-main-31:/drawable/ic_launcher_foreground.xml:21: error: attribute android:cy not found.
com.iosgallery.android.app-main-31:/drawable/ic_launcher_foreground.xml:21: error: '2' is incompatible with attribute radius (attr) dimension.
```

### Nguyên nhân:
- File `ic_launcher_foreground.xml` có syntax lỗi ở dòng 21
- Thuộc tính `android:cx` và `android:cy` không tồn tại hoặc sai context
- Giá trị `radius` không đúng định dạng dimension

## Giải pháp:

### 1. Fix ic_launcher_foreground.xml
Thay thế file với syntax đúng:

```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="108dp"
    android:height="108dp"
    android:viewportWidth="108"
    android:viewportHeight="108">
  <path
      android:fillColor="#26A69A"
      android:pathData="M0,0h108v108h-108z"/>
  <path
      android:fillColor="#FFFFFF"
      android:pathData="M54,20C35.8,20 21,34.8 21,53s14.8,33 33,33s33,-14.8 33,-33S72.2,20 54,20zM54,78c-13.8,0 -25,-11.2 -25,-25s11.2,-25 25,-25s25,11.2 25,25S67.8,78 54,78z"/>
  <path
      android:fillColor="#FFFFFF"
      android:pathData="M54,40c-7.2,0 -13,5.8 -13,13s5.8,13 13,13s13,-5.8 13,-13S61.2,40 54,40z"/>
</vector>
```

### 2. Tạo GitHub Actions Workflow mới
File `.github/workflows/build-apk-fixed.yml`:

```yaml
name: Build APK - Fixed
on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v4
    
    - name: Set up JDK 17
      uses: actions/setup-java@v4
      with:
        java-version: '17'
        distribution: 'temurin'
    
    - name: Grant execute permission for gradlew
      run: chmod +x gradlew
    
    - name: Build debug APK
      run: ./gradlew assembleDebug --stacktrace
    
    - name: Upload APK
      uses: actions/upload-artifact@v4
      with:
        name: debug-apk
        path: app/build/outputs/apk/debug/*.apk
```

### 3. Backup các file quan trọng
Đảm bảo có các file:
- `build.gradle` (root)
- `app/build.gradle`
- `settings.gradle`
- `gradle.properties`
- AndroidManifest.xml
- MainActivity.kt

## Kết quả mong đợi:
✅ Build thành công trên GitHub Actions
✅ APK được tạo và upload vào Artifacts
✅ Thời gian build: ~10-15 phút
✅ APK có thể install trên Android device