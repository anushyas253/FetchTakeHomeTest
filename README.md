# Fetch App

## Description

The **FetchTakeHomeTest** app is a native Android app written in Kotlin.<br>
It retrieves data from a remote API, filters, and sorts the data before displaying it. <br>
It incorporates essential concepts I’ve learned throughout my career as an Android Software Engineer, including principles of Clean Architecture, best practices, and my own implementations.

---


### Table of Contents
- [Architecture](#architecture)
- [Features](#features)
- [Libraries and Tools](#libraries-and-tools)
- [Navigation Flow](#screenshots)

---

### Architecture

<img width="800" alt="image" src="https://github.com/anushyas253/FetchTakeHomeTest/blob/master/assests/img.png">

The app follows **Clean Architecture** principles, with separation of concerns across three layers:

1. **Data Layer**: Responsible for retrieving data from the API using Retrofit. It uses repository pattern to separate data sources.
   - Example: `ItemRepositoryImpl`, `ApiService`, `ItemData`

2. **Domain Layer**: Contains business logic with use cases interacting with repositories.
   - Example: `ItemUseCaseImpl`

3. **Presentation Layer**: Consists of ViewModels and Activities/Fragments to handle UI and lifecycle management.
   - Example: `ItemViewModel`, `ListDetailsActivity`, `GroupActivity` 

The app is also integrated with **Hilt** for dependency injection to manage the lifecycle of components and ease testing.

---

### Features
- Fetches data from an API (`https://fetch-hiring.s3.amazonaws.com/hiring.json`).
- Filters items where `name` is empty or null.
- Sorts data based on `listId` and `name`.
- Displays items grouped by `listid` in a simple UI.

---

### Libraries and Tools
The project makes use of the following libraries and tools:

- **Retrofit**: For networking and API calls.
- **OkHttp**: For handling HTTP requests.
- **Hilt (Dagger)**: For dependency injection.
- **LiveData & ViewModel**: For managing UI-related data in a lifecycle-conscious way.
- **RecyclerView**: For displaying the list of data.
- **Gson**: For JSON parsing.

---

### Navigation Flow of the App

Flow navigation video: It testes all the previous options
[Click Here](https://github.com/anushyas253/FetchTakeHomeTest/blob/master/assests/Fetch_video%20(1).mp4)

