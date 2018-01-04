# repo
build.gradle for allprojects
```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
add to app build.gradle
```gradle
dependencies {
    compile 'com.github.binhbt:Vegabase:$lastest_version'
}
```
Demo at:
https://github.com/binhbt/VegaBaseDemo
