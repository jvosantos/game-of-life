# Game of life #

This is a raw implementation of [Conway's game of life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

- - -

## Index ##

* [Building the project](#building-the-project)
* [Running the application](#running-the-application)
* [Configuring the application](#configuring-the-application)
* [Test scenarios](#test-scenarios)
* [Implementations demystified](#implementations-demystified)
  * [Game of life endless](#game-of-life-endless)
  * [Game of life constrained](#game-of-life-contrained)
  
- - -

## Building the project ##

In order to build this project, you will require JDK 1.8 and maven 3.x

To build the project, simply make `mvn clean package` and maven will place a jar file on the target directory named `game-of-life.jar`.

- - -

## Running the application ##

To run the application you can either run the jar file

`java -jar target/game-of-life.jar [OPTION]..`

or you can use maven

`mvn exec:java`

- - -

## Configuring the application ##

The application can be configured in several different ways. You can create a properties file on the same place as the jar file called `application.properties` or you can pass the commands via command line.

The following table describe the options available and their defaults

| Option | default | description |
|--------|---------|-------------|
|     x  |    x    |    x        |

- - -

## Test scenarios ##

- - -

## Implementations Demystified ##

- - -

### Game Of Life Endless ###

- - -

### Game of Life Constrained ###
