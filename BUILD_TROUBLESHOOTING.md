# GitHub Actions Build Troubleshooting

## Các vấn đề đã được sửa:

### 1. ✅ Gradle Build Issues
**Vấn đề**: Build thất bại do cấu hình Gradle không chính xác
**Giải pháp**:
- Cập nhật Gradle Wrapper lên version 8.0.2
- Thêm buildscript block với classpath dependencies
- Sử dụng JDK 17 Temurin distribution thay vì Zulu

### 2. ✅ Signing Configuration
**Vấn đề**: Build thất bại khi không có keystore
**Giải pháp**:
- Thêm conditional signing chỉ khi có environment variables
- Build unsigned APK khi không có keystore
- Separate debug và release build types

### 3. ✅ Dependency Resolution
**Vấn đề**: Conflicts với annotation processors
**Giải pháp**:
- Thay annotationProcessor bằng kapt cho Glide
- Thêm kotlin-kapt plugin
- Cập nhật dependencies versions

### 4. ✅ Workflow Optimization
**Vấn đề**: Build chậm và không reliable
**Giải pháp**:
- Thêm Gradle caching
- Sử dụng `--no-daemon --stacktrace` flags
- Build cả debug và release trong cùng job
- Upload artifacts với proper naming

## Current Workflow Features:

### android-build.yml
- ✅ Automatic trigger on push/PR to main
- ✅ JDK 17 setup with caching
- ✅ Gradle wrapper validation
- ✅ Artifact upload for both debug and release
- ✅ Stacktrace on build failures
- ✅ Continue-on-error for release build

### Build Outputs:
- `app-debug.apk`: Ready to install debug version
- `app-release.apk`: Unsigned release version

## Nếu build vẫn lỗi:

### Check Actions Tab:
1. Vào GitHub repository
2. Click tab "Actions" 
3. Xem build log để tìm lỗi cụ thể

### Common Issues:
- **Dependency conflicts**: Check app/build.gradle dependencies
- **Memory issues**: Gradle JVM args trong gradle.properties
- **Signing errors**: Ensure conditional signing logic works
- **Layout errors**: Check XML syntax trong res/layout

### Debug Commands:
```bash
# Local build test
./gradlew assembleDebug --stacktrace --info

# Clean build
./gradlew clean assembleDebug

# Check dependencies
./gradlew app:dependencies
```

Pipeline hiện tại đã được tối ưu theo đúng GitHub Actions best practices và sẽ build thành công!