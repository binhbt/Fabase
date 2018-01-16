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
And
![Image of CoinMarketCap](https://lh3.googleusercontent.com/8UUcocHx_7XeIzfMDlUHbSuXOcmTqC3rmuC5_nRITCQoS2JO3olDf8f-_Pf0TuMUVmg=h900-rw)
at 
https://play.google.com/store/apps/details?id=com.bip.coin.market

