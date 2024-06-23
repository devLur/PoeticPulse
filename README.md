# PoeticPulse

### About

PoeticPulse is a application to learn about software quality assurance and poetry.

### Documentation

The Software is documented in the arc42 format. The documentation can be found [here](doc/arc42/01_introduction_and_goals.md), under `./doc/arc42/`. It is a proven, practical and pragmatic format that is free and open source. It takes the pain out of documentation. Look into it to acquire more knowledge about the application.

### Structure

- `./doc` contains all documentation around PoeticPulse
- `./poeticPulse-be` contains the backend application for PoeticPulse. Read the [README.md here](poeticPulse-be/README.md) on how to setup your dev environment
- `./poeticPulse-fe` contains the frontend application for PoeticPulse Read the [README.md here](poeticPulse-ui/README.md) on how to setup your dev environment
- `./.github/workflows` contains GitHub actions

### Run application
**Prerequisites**:  
follow the requirements sections in the README.md files of both Applications `./poeticPulse-be` and `./poeticPulse-fe`

If you have Docker and Docker-Compose installed, you can simply start the application with the command:  
```bash
docker-compose up
```

