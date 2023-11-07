# Kinopoisk Movie App

This Android app allows you to search for and explore movie data using the Kinopoisk Unofficial API. You can quickly access information about rating movies, view cast, and even watch trailers.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [API Key](#api-key)
- [Contributing](#contributing)
- [License](#license)

## Features

- View basic movie information and cast.
- Watch movie trailers directly from the app.

## Getting Started

Follow these instructions to get the app up and running on your local development environment.

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/kinopoisk-movie-app.git
   cd kinopoisk-movie-app
   ```

2. **Open in Android Studio**

   Open the project in Android Studio or your preferred Android development IDE.

3. **Build and Run**

   Build the project and run it on your Android emulator or physical device.

## Usage

1. **Search for Movies**

   - Open the app and enter a movie title or keyword in the search bar.
   - Tap the search button to get a list of movies matching your query.

2. **View Movie Details**

   - Tap on a movie from the search results to see detailed information.
   - This screen displays the movie's title, description, rating, reviews, and cast.
   - You can also watch the trailer by clicking the "Watch Trailer" button.

3. **Save to Watchlist**

   - If you like a movie, you can save it to your watchlist by clicking the "Add to Watchlist" button on the movie details screen.

4. **Switch between Dark and Light Mode**

   - The app offers both dark and light mode. You can switch between them by going to the settings in the app.

## API Key

To use this app, you will need to obtain an API key from Kinopoisk. Follow these steps to get your API key:

1. Visit the [Kinopoisk Developer Portal](https://developer.kinopoisk.ru/) and sign up for an account.

2. Create a new project and request an API key for movies.

3. Copy the API key and replace the placeholder in the app's code with your actual key.

   ```java
   // app/src/main/java/com/yourapp/kinopoisk/ApiService.java
   public interface ApiService {
       @GET("movie/search")
       Call<MovieResponse> searchMovies(@Query("api_key") String apiKey, @Query("query") String query);
   }
   ```

## Contributing

We welcome contributions to improve this app. If you have any ideas for features, bug fixes, or improvements, please feel free to submit a pull request or open an issue.

1. Fork the repository
2. Create a new branch
3. Make your changes
4. Test your changes
5. Create a pull request

## License

This app is open-source and is distributed under the [MIT License](LICENSE). Feel free to use, modify, and distribute it as you see fit.

**Disclaimer**: This project is not affiliated with or endorsed by Kinopoisk. It is intended for educational and personal use.
