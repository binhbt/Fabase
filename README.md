- Use as Android project boilerplate 
- Request support cache, rxjava, custome request adapter with other Httprequest library. you can use Retrofit, OkHttp, Volley or whats the fuckin httplibrary you want. Switch http core request in a sec. Support cache API 
- A RecyclerView Adapter which allows you to have an Infinite scrolling list in your apps. This library offers you a custom adapter to use with any recycler view. You get a callback when the user is about to reach the bottom (or top, depending on your configuration), of the list, which you can use to load more data. FaRecyclerView support pull to refresh, multiple type view layout in a sec with model

You can also customize what the loading view at the bottom of the list looks like.

Note: This library should be able to work with any layout manager linear, grid, stagged layout manager. You can config layout manager in xml or in java code, support no data layout

This library is contain 2 lib:

Fucking Awesome RecyclerView

https://github.com/binhbt/FaRecyclerview

And

Fucking Awesome Http Request

https://github.com/binhbt/FaRequest
```sh
//All project
allprojects {
    repositories {
        google()
        maven { url "https://jitpack.io" }
    }
}
//App project
    implementation 'com.github.binhbt:Fabase:1.5.4'
```
- Demo at:
https://github.com/binhbt/FADemo
