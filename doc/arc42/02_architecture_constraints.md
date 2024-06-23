# Architecture Constraints

This chapter shows the constraints on this project and if applicable, their background or motivation.

## Technical Constraints

| Symbol | Constraint                                                                                       | Background / Motivation                                                                                                                                                     |
| :----- | :----------------------------------------------------------------------------------------------- | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| TC1    | Split frontend and backend application                                                           | Splitting frontend and backend application allows for independet scaling and parallel development. It also leads to better maintainability                                  |
| TC2    | Backend implementation in Java                                                                   | Due to existing knowledge the backend is implemented in Java                                                                                                                |
| TC3    | Frontend implementation in Javascript                                                            | Due to the wish of building Javascript knowledge the frontend is programmed in Javascript                                                                                   |
| TC4    | Third party software must be under an open source license and installable with a package manager | This project should be easy to build without the need to install proprietary software. Additionally, all external dependencies should be available through package managers |
| TC5    | Deployable with [Docker](https://www.docker.com/) images                                         | Applications should be deployable through standard means with [Docker](https://www.docker.com/) images                                                                      |

## Organizational Constraints

| Symbol | Constraint                             | Background / Motivation                                                                                    |
| :----- | :------------------------------------- | :--------------------------------------------------------------------------------------------------------- |
| OC1    | Version Control                        | Project is placed in a git repository with public branches on GitHub                                       |
| OC2    | Mono Repository                        | To be able to provide a single github link, backend and frontend applications are placed in one repository |
| OC3    | IDE independent project                | The project must be compilable on the command line with standard build tools.                              |
| OC4    | Published under an open source license | The source and documentation should be published as open source                                            |

## Conventions

| Symbol | Constraint                 | Background / Motivation                                                                                                                                                                                                                                                       |
| :----- | :------------------------- | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| C1     | Architecture documentation | Structure based on the english [arc42](https://arc42.org/)-Template                                                                                                                                                                                                           |
| C2     | Code conventions           | The project follows the [Google Java Coding Style Guide](https://google.github.io/styleguide/javaguide.html). Rules for block indentation and javadoc comment are excluded. All Code conventions are checked and enforced by [Checkstyle](https://checkstyle.sourceforge.io/) |
| C3     | Language                   | English. The project targets an international audience, so English should be used throughout the whole project.                                                                                                                                                               |

