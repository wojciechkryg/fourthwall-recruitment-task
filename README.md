# Fourthwall Recruitment Task
Android recruitment task made for Fourthwall.


## Features
- List of images
    - Shown as a grid list
    - Number of columns depends on screen size and orientation
- Image details
    - Full image
    - Author
    - Share button to share image with other apps
- Loading state
- Error state

## Screenshots

<p align="center">
 <img src="/screenshots/01.jpg" width="280" height="571" alt="Photo gallery">
 <img src="/screenshots/02.jpg" width="280" height="571" alt="Photo details">
 <img src="/screenshots/03.jpg" width="571" height="280" alt="Photo gallery in landscape">
</p>


## Details
- MVI pattern
- UI created with [Jetpack Compose](https://developer.android.com/jetpack/compose)
- DI done with [Hilt](https://dagger.dev/hilt/)
- API calls made with [Retrofit](https://square.github.io/retrofit/)
- JSON parse made with [Moshi](https://github.com/square/moshi)
- Images are downloaded from [Lorem Picsum API](https://picsum.photos/)
- Images loaded with [Coil](https://github.com/coil-kt/coil)
- Used [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- Included Unit Tests

## Main project structure
- `data` package contains:
    - entities
    - local and remote data sources
    - mappers
    - repositories
- `domain` package contains:
    - models
    - use cases
 - `ui` package contains:
    - details ui and view model
    - gallery ui and view model

## Further steps
- [ ] Create different build variants or extract url for different environments
- [ ] Extract `data`, `domain`, `ui` packages as separate modules

## How to run?

### Build project
You can build a project manually from Android Studio or use the following gradle command:
```
./gradlew installDebug
```

### Unit Tests
You can run unit tests manually from Android Studio or use the following gradle command:
```
./gradlew testDebugUnitTest
```

## License
```
 Copyright 2021 Wojciech Kryg

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
