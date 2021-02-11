# AgileEnginePOC
POC for AgileEngine

# Build

To build:

```bash
$ git clone https://github.com/AHerbel/AgileEnginePOC.git
$ cd AgileEnginePOC/
$ ./gradlew build
```

# Run

To run:

Pre-requisites: <br>
Have the $ANDROID_SDK_HOME environment variable set in your computer<br>
Have the $ANDROID_SDK_HOME/platform-tools set in your PATH environment variable

```bash
$ emulator -list-advs
$ emulator -avd [avd-name]
$ adb install <path to repo folder>/app/build/outputs/apk/debug/app-debug.apk
$ adb shell am start -n com.aherbel.agileenginetest/com.aherbel.agileenginetest.presentation.MainActivity
```

PD: <br>
```bash
$ANDROID_HOME=/Users/<your_user>/Library/Android/sdk (Mac OS X)
$ANDROID_HOME=C:\Users\<your_user>\AppData\Local\Android\Sdk (Windows)
```
