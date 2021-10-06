# Movie App

The purpose of this app is to allow users to create a list of their favorite movies.
Adding movies is simple. The user only has to enter a title (or part of it) and the app will try to find the movie
in the OMDB (Open Movie Database) using their webservice.

## Main Activity

The main activity handles the movie overview page, as well as the detailed movie view. From here, the user
can navigate to the AddMovieActivity to add a new movie to the list.

## AddMovieActivity

This activity handles the user's title input, and passes it to the MovieService as an intent.

## MovieService

This Service connects to the OMDB API at http://www.omdbapi.com/ .
Using the user's input, the service requests details about the movie from the API and returns these using a broadcast.

## MovieBroadcastReceiver

This BroadcastReceiver will receive a broadcast from the MovieService, and passes the movie details to the
MovieContentProvider to process further.

## MovieContentProvider

This ContentProvider stores all movies in a local SQLite instance. All CRUD operation are possible through the
contentProvider. (Create, read, update, delete).

