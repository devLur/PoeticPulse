# Navigate to the projects .git/hooks directory
cd .git/hooks || exit

# Create the pre-push file using a heredoc for better readability
cat << EOF > pre-push
#!/bin/sh
./gradlew clean build
EOF
