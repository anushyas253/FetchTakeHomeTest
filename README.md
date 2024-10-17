# Fetch Home Test

## Description
A native Android application built using Kotlin that performs the following operations:

--Fetches data ( id, listId, and name) from an external API (**https://fetch-hiring.s3.amazonaws.com/hiring.json**).<br>
--Filters out any entries where the name is null or blank.
--Sorts the remaining data by listId first and then by name.
--Displays the sorted data grouped according to listId.

### Table of Contents
- [Architecture](#architecture)
- [Features](#features)
- [Libraries and Tools](#libraries-and-tools)
- [Installation](#installation)
- [Screenshots](#screenshots)

---

### Architecture
The app follows **Clean Architecture** principles, with separation of concerns across three layers:

1. **Data Layer**: Responsible for retrieving data from the API using Retrofit. It uses repository pattern to separate data sources.
   - Example: `ItemRepositoryImpl`, `ApiService`

2. **Domain Layer**: Contains business logic with use cases interacting with repositories.
   - Example: `ItemUseCaseImpl`, `ItemData`

3. **Presentation Layer**: Consists of ViewModels and Activities/Fragments to handle UI and lifecycle management.
   - Example: `ItemViewModel`, `GroupActivity`

The app is also integrated with **Hilt** for dependency injection to manage the lifecycle of components and ease testing.

---

### Features
- Fetches data from an API (`https://fetch-hiring.s3.amazonaws.com/hiring.json`).
- Filters items where `name` is empty or null.
- Sorts data based on `listId` and `name`.
- Displays the filtered and sorted data in a simple UI.

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

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/FetchTakeHomeTest.git
