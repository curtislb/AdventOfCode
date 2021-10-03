# AdventOfCode

<!-- Badges -->
[![build](https://img.shields.io/github/workflow/status/curtislb/AdventOfCode/build?logo=Gradle)](https://github.com/curtislb/AdventOfCode/actions/workflows/build.yml)
[![license](https://img.shields.io/github/license/curtislb/AdventOfCode?logo=data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB2ZXJzaW9uPSIxLjEiIGhlaWdodD0iMTYiIHdpZHRoPSIxNiIgdmlld0JveD0iMCAwIDE2IDE2Ij4NCiAgICA8cGF0aCBmaWxsPSJ3aGl0ZSIgZmlsbC1ydWxlPSJldmVub2RkIiBkPSJNOC43NS43NWEuNzUuNzUgMCAwMC0xLjUgMFYyaC0uOTg0Yy0uMzA1IDAtLjYwNC4wOC0uODY5LjIzbC0xLjI4OC43MzdBLjI1LjI1IDAgMDEzLjk4NCAzSDEuNzVhLjc1Ljc1IDAgMDAwIDEuNWguNDI4TC4wNjYgOS4xOTJhLjc1Ljc1IDAgMDAuMTU0LjgzOGwuNTMtLjUzLS41My41M3YuMDAxbC4wMDIuMDAyLjAwMi4wMDIuMDA2LjAwNi4wMTYuMDE1LjA0NS4wNGEzLjUxNCAzLjUxNCAwIDAwLjY4Ni40NUE0LjQ5MiA0LjQ5MiAwIDAwMyAxMWMuODggMCAxLjU1Ni0uMjIgMi4wMjMtLjQ1NGEzLjUxNSAzLjUxNSAwIDAwLjY4Ni0uNDVsLjA0NS0uMDQuMDE2LS4wMTUuMDA2LS4wMDYuMDAyLS4wMDIuMDAxLS4wMDJMNS4yNSA5LjVsLjUzLjUzYS43NS43NSAwIDAwLjE1NC0uODM4TDMuODIyIDQuNWguMTYyYy4zMDUgMCAuNjA0LS4wOC44NjktLjIzbDEuMjg5LS43MzdhLjI1LjI1IDAgMDEuMTI0LS4wMzNoLjk4NFYxM2gtMi41YS43NS43NSAwIDAwMCAxLjVoNi41YS43NS43NSAwIDAwMC0xLjVoLTIuNVYzLjVoLjk4NGEuMjUuMjUgMCAwMS4xMjQuMDMzbDEuMjkuNzM2Yy4yNjQuMTUyLjU2My4yMzEuODY4LjIzMWguMTYybC0yLjExMiA0LjY5MmEuNzUuNzUgMCAwMC4xNTQuODM4bC41My0uNTMtLjUzLjUzdi4wMDFsLjAwMi4wMDIuMDAyLjAwMi4wMDYuMDA2LjAxNi4wMTUuMDQ1LjA0YTMuNTE3IDMuNTE3IDAgMDAuNjg2LjQ1QTQuNDkyIDQuNDkyIDAgMDAxMyAxMWMuODggMCAxLjU1Ni0uMjIgMi4wMjMtLjQ1NGEzLjUxMiAzLjUxMiAwIDAwLjY4Ni0uNDVsLjA0NS0uMDQuMDEtLjAxLjAwNi0uMDA1LjAwNi0uMDA2LjAwMi0uMDAyLjAwMS0uMDAyLS41MjktLjUzMS41My41M2EuNzUuNzUgMCAwMC4xNTQtLjgzOEwxMy44MjMgNC41aC40MjdhLjc1Ljc1IDAgMDAwLTEuNWgtMi4yMzRhLjI1LjI1IDAgMDEtLjEyNC0uMDMzbC0xLjI5LS43MzZBMS43NSAxLjc1IDAgMDA5LjczNSAySDguNzVWLjc1ek0xLjY5NSA5LjIyN2MuMjg1LjEzNS43MTguMjczIDEuMzA1LjI3M3MxLjAyLS4xMzggMS4zMDUtLjI3M0wzIDYuMzI3bC0xLjMwNSAyLjl6bTEwIDBjLjI4NS4xMzUuNzE4LjI3MyAxLjMwNS4yNzNzMS4wMi0uMTM4IDEuMzA1LS4yNzNMMTMgNi4zMjdsLTEuMzA1IDIuOXoiPjwvcGF0aD4NCjwvc3ZnPg==)](https://github.com/curtislb/AdventOfCode/blob/master/LICENSE.txt)
[![linkedin](https://img.shields.io/badge/linkedin-curtisbelmonte-blue?logo=linkedin)](https://www.linkedin.com/in/curtisbelmonte/)

## Summary

My solutions to various programming puzzles from [adventofcode.com](https://adventofcode.com/).
Currently, this includes:

* [Advent of Code 2019](https://adventofcode.com/2019) (Days 1-25)
* [Advent of Code 2020](https://adventofcode.com/2020) (Days 1-25)

## Getting Started

### Dependencies

* [Java 11](https://openjdk.java.net/projects/jdk/11/)
* [Kotlin 1.5.31](https://github.com/JetBrains/kotlin/releases/tag/v1.5.31)
* [Gradle 7.2](https://docs.gradle.org/7.2/release-notes.html)

### Installation

Clone with SSH (recommended):

```
git clone git@github.com:curtislb/AdventOfCode.git
```

Clone with HTTPS:

```
git clone https://github.com/curtislb/AdventOfCode.git 
```

### Usage

This project can be opened with [IntelliJ IDEA](https://www.jetbrains.com/idea/) and run using the
[JetBrains Gradle plugin](https://www.jetbrains.com/help/idea/gradle.html).

Alternatively, the following commands can be run from the project root directory:

```
./gradlew build                       # Build whole project (and run tests)

./gradlew test                        # Run all tests in project

./gradlew codeCoverageReport          # Create a code coverage report (under coverage/build)

./gradlew :common:collection:test     # Run tests for a specific library

./gradlew :year2019:day01:part1:test  # Run tests for a specific puzzle

./gradlew :year2019:day01:part1:run   # Solve a given puzzle and print the answer
```
