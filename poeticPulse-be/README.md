# Getting Started with PoeticPulse-be

This project was bootstrapped with [Spring Initializr](https://start.spring.io/).

## Installing requirements (Mac OS)
Install the following dependencies, if you don't have brew because you are not on OS, you need to find an alternative package manager like chocolatey for windows for example:   
```bash
brew install docker
brew install openjdk
brew install gradle
```

run the following commands in this folder:  
```bash
gradlew build
```

It is recommended to install git hooks to make sure your code is tested before commiting anything. In order to do so execute:  
```bash
./installGitHooks.sh
```

## Run locally
Make Sure the dependent applications are available locally before running this application:
```bash
docker pull mongo:7.0
docker run --name mongoDB -p 27017:27017 -d mongo:7.0
```