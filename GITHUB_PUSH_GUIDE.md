# Hướng dẫn Push Code lên GitHub

## Bước 1: Chuẩn bị Repository
1. Mở terminal/command prompt trên máy tính của bạn
2. Navigate đến thư mục chứa project này

## Bước 2: Khởi tạo Git Repository
```bash
# Xóa git repository cũ nếu có
rm -rf .git

# Khởi tạo git repository mới
git init

# Cấu hình git user
git config user.name "Your Name"
git config user.email "your.email@example.com"
```

## Bước 3: Thêm Remote Repository
```bash
# Thêm remote repository
git remote add origin https://github.com/brightstartssba/iOS-Gallery-.git
```

## Bước 4: Push Code
```bash
# Add tất cả files
git add .

# Commit với message
git commit -m "Initial commit: iOS Gallery Android App

✨ Features implemented:
- iOS-like gallery interface with dark theme
- Photo grid layout (3 columns)
- Recent Days section with date grouping
- People & Pets section structure
- Full-screen photo viewer with navigation
- Runtime permissions handling
- Glide image loading with caching

🚀 CI/CD Pipeline:
- GitHub Actions for automated builds
- Support for signed/unsigned APK generation
- Automatic artifact upload with versioning
- Debug and release build workflows

📱 Tech Stack:
- Kotlin + ViewBinding
- Material Design 3 with iOS styling
- Glide for image loading
- Dexter for permissions
- ViewPager2 for photo viewing
- Gradle 8.0 build system"

# Set main branch
git branch -M main

# Push với authentication token
git push -u origin main
```

## Xác thực với GitHub Token
Khi git yêu cầu username và password:
- **Username**: `brightstartssba`
- **Password**: `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`

## Sau khi Push thành công:

### 1. GitHub Actions sẽ tự động chạy
- Kiểm tra tab "Actions" trong GitHub repository
- Workflow "Android Build" sẽ chạy tự động
- Build sẽ tạo cả debug và release APK

### 2. Download APK
- Vào tab "Actions" → chọn build run mới nhất
- Download APK từ "Artifacts" section:
  - `app-debug`: APK debug để test
  - `app-release`: APK release (unsigned)

### 3. Cài đặt trên Android
- Copy APK file vào điện thoại Android
- Enable "Install from Unknown Sources" trong Settings
- Cài đặt và test ứng dụng

### 4. Build Status
Workflow đã được tối ưu để tránh lỗi:
- Sử dụng JDK 17 (Temurin distribution)
- Cache Gradle packages
- Build cả debug và release
- Stacktrace để debug lỗi dễ dàng

## Để tạo Signed APK (Production):
Thêm các secrets sau vào GitHub repository:
1. Vào Repository Settings → Secrets and variables → Actions
2. Thêm secrets:
   - `KEYSTORE_FILE`: File keystore encode base64
   - `KEYSTORE_PASSWORD`: Mật khẩu keystore
   - `SIGNING_KEY_ALIAS`: Alias của key
   - `SIGNING_KEY_PASSWORD`: Mật khẩu key

## Repository URL
🔗 https://github.com/brightstartssba/iOS-Gallery-

Sau khi push thành công, GitHub Actions sẽ tự động build và tạo APK file có thể cài đặt trên thiết bị Android!