#!/bin/bash

echo "=== iOS Gallery Android - Push to GitHub ==="
echo "Repository: https://github.com/brightstartssba/iOS-Gallery-.git"
echo "Token: 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"

# Initialize git if needed
if [ ! -d ".git" ]; then
    git init
    git remote add origin https://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@github.com/brightstartssba/iOS-Gallery-.git
fi

# Add all files
echo "Adding all files..."
git add .

# Commit with timestamp
TIMESTAMP=$(date '+%Y-%m-%d %H:%M:%S')
git commit -m "iOS Gallery Android - Repository Conflict Fixed - $TIMESTAMP"

# Push to GitHub (force if needed)
echo "Pushing to GitHub..."
git branch -M main
git push -u origin main --force

echo "=== DONE - Check GitHub Actions for build status ==="
echo "Visit: https://github.com/brightstartssba/iOS-Gallery-/actions"