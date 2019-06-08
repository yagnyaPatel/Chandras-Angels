
# Chandras-Angels

## Project Summary
### One Sentence Summary
The project is a platform for multiple people to choose a place to eat based on preference by voting.
### Project Overview
Sometimes in social situations, the problem of where to eat arises and is not always easily solved. It can quickly get chaotic as people begin blurting out their ideas, and not everyone may get a fair say. This platform is designed as a solution to allow multiple people to easily select a restaurant to eat at with a vote-based system to reflect the preferences of the group.

The project is an Android application that allows a user to create a group that other users can join. Group members can search for local restaurants, receive recommendations on their phone based on search queries and location, and members of the group can then vote on all selected options. The voting system is a simple upvote/downvote system where users are allowed 3 total votes to spend as they please. Thus, we streamline the process of idea suggestions and idea selections to improve efficiency, and save both time and a headache.

The frontend is an Android app developed in Android Studio. Restaurant selection is implemented using the Yelp Fusion API. The backend uses the Firebase Cloud Firestore database to store party info and push updates to clients.

## Installation
### Prerequisites
- Android Studio (See Android Studio's [Getting Started guide](https://developer.android.com/studio))
- Android device with USB Debugging enabled, or Android Virtual Device running Android 5.0 (API level 21) or above
  * If Firebase authentication (signing into the database) doesn't work, try using a device or AVD running Android 9 (API level 28) or above
### Installation Steps
#### Windows
1. Launch Android Studio and select "Open an existing project"
2. Navigate to the MunchEase folder within the Chandras-Angels folder and select it as the project folder
3. Run the app using Android Studio, either on an emulator or your Android device
## Functionality and Known Issues
Users can launch the app and create parties with unique IDs that other members can use to join. Members of a party can search for Isla Vista-local restaurants and can add these to the party. Currently the location and radius are hard-coded rather than using a user's GPS coordinates and inputted search radius. Restaurants that have been added to the party can be voted on. However, currently there is no way to undo votes. Tapping on the restaurant title opens up the phone's web browser to the restaurant's Yelp webpage.
For a more detailed view of app functionality, please see our [demo video](https://vimeo.com/339914219) (password: cs48).

## License
This project is licensed under the Apache License 2.0. The license can be viewed [here](LICENSE).
