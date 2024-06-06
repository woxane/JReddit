![Reddit pic](reddit.png)
# JReddit
## Table of Contents
- [Description](#description)
- [Usage](#usage)
- [Credits](#credits)
- [Changelog](#changelog)
- [Contact Information](#contact-information)

## Description
This project aims to recreate a simplified version of the Reddit platform within the terminal environment using Java. It provides users with a command-line interface to interact with various features commonly found on Reddit, such as creating and browsing subreddits, posting and commenting on threads, and upvoting/downvoting content.

Key features include:

- **User Authentication:** Users can create accounts and log in securely to access the system's functionalities.
- **Subreddit Management:** Users can create, subscribe to, and browse different subreddits based on their interests.
- **Thread Interactions:** Users can create new threads, comment on existing ones, and upvote or downvote both threads and comments.
- **Search Functionality:** Users can search for specific threads or subreddits using keywords.
- **User Profiles:** Each user has a profile displaying their activity, including posts and comments.

This project provides a hands-on opportunity to explore concepts such as data structures, user input handling, authentication, and data persistence in Java. It's suitable for anyone interested in learning more about Java programming or terminal-based application development.

## Usage
To use this project, follow these steps:

1. **Sync Gradle:** Ensure your Gradle project is synced to download the latest dependencies.

2. **Running in IntelliJ IDEA:**
  - Open the project in IntelliJ IDEA.
  - Simply run the `Main.java` file.

3. **Running in Terminal:**
  - Navigate to the project directory using the `cd` command.
  - Compile the Java files using `javac`:
    ```
    javac Account.java Admin.java Comment.java Database.java Main.java Post.java Reddit.java Subreddit.java Vote.java test.java
    ```
  - Run the compiled program using `java`:
    ```
    java Main
    ```

## Credits
This project utilizes the following dependencies:

- **commons-codec:commons-codec:1.16.1**: Apache Commons Codec library for encoding and decoding binary data in various formats.
- **org.xerial:sqlite-jdbc:3.45.2.0**: SQLite JDBC Driver for Java, provided by Xerial.
- **org.slf4j:slf4j-api:1.6.1**: Simple Logging Facade for Java (SLF4J) API, version 1.6.1.
- **org.slf4j:slf4j-simple:1.6.1**: SLF4J simple binding, providing a simple implementation of the SLF4J API for logging.

These dependencies are essential for the functionality of the project. No external contributions have been made to the codebase at this time.

## Contact Information

If you encounter any issues or have suggestions for improvements, please feel free to reach out:

- **GitHub:** [Report an Issue](https://github.com/woxane/JReddit/issues)
    - If you encounter a problem or would like to suggest an enhancement, please open an issue on GitHub.
- **Contributions:** [Pull Requests Welcome](https://github.com/woxane/JReddit/pulls)
    - Contributions are welcome! If you'd like to contribute code or documentation changes, please submit a pull request on GitHub.
- **Email:** [mohsen.minavand.ch@gmail.com](mailto:mohsen.minavand.ch@gmail.com)