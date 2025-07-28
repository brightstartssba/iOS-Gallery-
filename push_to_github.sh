#!/bin/bash

# Script to push iOS Gallery Android project to GitHub
# Repository: https://github.com/brightstartssba/iOS-Gallery-.git

echo "🚀 Pushing iOS Gallery Android to GitHub..."

# Remove existing git repository if any
rm -rf .git

# Initialize new git repository
git init

# Configure git user
git config user.name "iOS Gallery Developer"
git config user.email "developer@iosgallery.com"

# Add remote repository
git remote add origin https://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@github.com/brightstartssba/iOS-Gallery-.git

# Add all files
git add .

# Create initial commit
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
- Gradle 8.0 build system

🔧 Ready for deployment:
- Complete Android project structure
- Professional code architecture
- Comprehensive documentation
- GitHub Actions CI/CD pipeline"

# Set main branch
git branch -M main

# Push to GitHub
echo "📤 Pushing to GitHub repository..."
git push -u origin main

if [ $? -eq 0 ]; then
    echo "✅ Successfully pushed to GitHub!"
    echo "🔗 Repository: https://github.com/brightstartssba/iOS-Gallery-"
    echo ""
    echo "📋 Next steps:"
    echo "1. GitHub Actions will automatically build the APK"
    echo "2. Check the Actions tab for build progress"
    echo "3. Download APK from build artifacts"
    echo "4. Install on Android device to test"
    echo ""
    echo "🔑 For signed releases, add these secrets:"
    echo "- KEYSTORE_FILE (base64 encoded keystore)"
    echo "- KEYSTORE_PASSWORD"
    echo "- SIGNING_KEY_ALIAS"
    echo "- SIGNING_KEY_PASSWORD"
else
    echo "❌ Push failed. Please check your token and repository access."
fi