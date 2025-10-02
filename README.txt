iX Android Project Template
===========================

This ZIP contains the full Android Studio project source for the iX app template.
It is pre-populated with core Kotlin source files, Compose components, resources, and Gradle files.

IMPORTANT:
- This environment cannot compile a real Android APK. The ZIP includes source only.
- To produce an APK you must build locally in Android Studio (or CI) with an Android SDK installed.

How to build an APK locally
---------------------------
1. Open Android Studio (Arctic Fox or newer recommended).
2. File -> Open -> select the folder containing this project (the folder with settings.gradle.kts).
3. Allow Gradle to sync. You may need to install/update SDK/Gradle Kotlin versions.
4. (Optional) Add KAPT for Room compiler in app/build.gradle if you use Room annotations:
   apply plugin: 'kotlin-kapt'
   add kapt("androidx.room:room-compiler:2.6.1")
5. Build -> Build Bundle(s) / APK(s) -> Build APK(s).
6. The generated APK will appear in app/build/outputs/apk/.

Notes about the provided project
-------------------------------
- BluetoothManager.kt is a simplified skeleton. You must implement device discovery, permission flow, pairing logic, and robust error handling.
- Image & voice transfer over Bluetooth should use chunking and acknowledgements (not implemented here).
- The particle animation, blur, and Compose animations are provided as examples and will run on Android 10+.
- Replace 'res/font/cursive_font.ttf' with your preferred cursive font file to display the quote in AboutPage.
- Replace mipmap launcher icons with your final white-A icon images (provide multiple resolutions).

If you want, I can also prepare a separate script / GitHub Actions workflow that builds a debug APK automatically in CI and uploads it as an artifact. That would require linking to a repository (or I can provide the workflow file for you to add to your repo).

--- End of README
