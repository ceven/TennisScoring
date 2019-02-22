# Tennis TennisMatchScoring System

This project aims at implementing a scoring system for a tennis match.

## TennisMatchScoring vocabulary and rules

For the aim of this scoring system, we will use the rules and vocabulary described below. Note that while these may look similar to the real rules and vocabulary of tennis in many aspects, some differences and simplifications are made.
 - A match of tennis is played between 2 players in a single set. The set winner is the winner of the match.
 - A set consists of several games.
 - A tennis game is played in several "points".
 
 **To win a set**, a player must either:
  - win 6 games with a margin of 2 or more games over his opponent
  - or win 7 games to 5
  - or, if the score is a tie at 6-6, win the tie-break.
  
 **To win a game**, a player must win at least 4 points with a margin of at least 2 points over his opponent.
 
 **A tie-break** is won by the first player who scores at least 7 points with a margin of 2 or more points.
 
 We will use the tennis vocabulary "Deuce", "Advantage" with their original meaning.
 
 ## Implementation
 
This implementation provides two significant methods:
 - a method `pointWonBy` which updates the score of the match based on the winner, passed in as a method argument
 - a method `score` which returns the current score of the match (the current set score followed by the current game score)
 
 ## Requirements & How to run
 
 
This project requires Java 10. I use gradle (version 5.2.1) to manage dependencies and run tests. Please follow the instructions below to download and run the code:

 - Open a terminal prompt and clone this repository in the location of your choice
 - Navigate to the project root
 - Type ./gradlew clean build to build the project and run unit tests
 
 
 ## Improvement considerations
 
 ### Naming
 
 - While the two methods previously described are named after the problem description, we could consider renaming:
  - the `score` method to `getScore` or `getMatchScore`
