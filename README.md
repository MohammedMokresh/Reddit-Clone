# Reddit simple clone app by using kotlin and AAC

[![Kotlin](https://kotlin.link/awesome-kotlin.svg)](https://kotlinlang.org/)
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.3.72-blue.svg)](http://kotlinlang.org/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


### Clean Architecture Description and Implementation

This project uses MVVM with RxJava, androidx and android lifecycle components ViewModel, LiveData and Room to display a list of topics from [Reddit API](https://www.reddit.com/reddits.json) using Retrofit . Selecting a topic will display a topic detail page .

After bring the topics from the Api , the results will be saved in local DB using Room , the repository will achieve the Single source of truth with the help of RxJava to combine multible resources , which will send the data to the Viewmodel where will be data Transformation and get the final result in Livedata objects and send it to the View.

The Idea behind using local DB to achieve 3 things:

1 - to make the user expirience better when the user open the app by showing few results and refresh the list from the API.

2 - make it easier to transfer the data between the fragments and activities without the need of creating Parcelable objects. 

3 - help updating data like in our example (up vote , down vote)

### Package Details
**App:** Contain the Apiservices , RoomDB and Application class
* **Data:** Contain the listing Data Access object (DAO) to do all the local DB queries , and the listing repository where we achieve the single source of truth 
* **DI:** Contain the DI modules 
* **Dialogs :** Contain all the bottom sheet dialogs that we have in our example (add new topic and confirm clear the draft)
* **Utils:** Contain helpers for many purposes (like image rendering and fragment navigation) to avoid boilerplate in the code
 

### Frameworks and Libraries

* Architecture components with ViewModel and LiveData  , Room
* RxJava for async processing
* Gson for Json Parsing
* Koin for dependency injection
* AndroidX for Jetpack components
* Glide for Image Loading

