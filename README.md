# Portfolio project IDATA1003 - 2023
This file uses Mark Down syntax. For more information see [here](https://www.markdownguide.org/basic-syntax/).

Removed student ID and name from public repo

STUDENT NAME = "*******"  
STUDENT ID = "******"

## Project description

This project represents a train departure system where a Departure Manager can create departures for a given train 
station. When assigning a train departure a destination, id, track and line is set as well as the departure time and any
eventual delays for the given departure.  
All the departures are collected in a common register where all the departures will be sorted by departure time
as well as verified that there are no two departures with the same unique identifier.

There is functionality that can be used so that a user can search for specific departures by its unique id, departure
destination as well as functionality to get all the departures sorted by the departure time.  
Departures will automatically be removed from the registry if the departure time plus delay is older than the current
system time.

## Project structure

For this project I have decided to structure my classes into packages relating to what the specific tasks for the 
classes are. I have followed the standard Java naming convention of the packages where I have used the domain I 
write the code from. In this case the packages are structured as "edu.ntnu.PACKAGE" where I have primarily separated the
classes into three main categories. "entity", "logic" and "ui".

Using the packages in this way means that the test classes automatically also follow the same package structure when 
generated providing the project with a clear and concise project tree. The source files are all
saved in their corresponding packages under the "src" directory as well as the JUnit tests being saved under the 
corresponding packages under the "test" directory.

## Link to repository

Here is a link to the repository on GitHub:  
https://github.com/matrHS/traindispatchapp


## How to run the project

What is the input and output of the program? What is the expected behaviour of the program?)

If the user is running the source code for the project the user should run the main method in the 
"TrainDispatchApp". After running the main method here the UI class will firstly initialize all the needed data before
the start method in the UI class shows the main menu interface where all the functionality of the program can be
used.

When running a packed version of the program the user needs to have java installed and make sure it's reachable through
the terminal / command prompt. Once this is verified the user can use the following command to run the application:
```powershell
java -jar TrainDispatchSystem.jar
```
Where "TrainDispatchSystem.jar" should be the directory to the file depending on where the active directory
in the terminal is.

## How to run the tests

[//]: # (TODO: Describe how to run the tests here.)

To run the tests created with JUnit there are "test classes" created for each class that should have unit tests. The 
test classes are located under the "test" folder in the projects structure. In IntelliJ there are some very useful 
features to run all tests. By right-clicking the "java" folder under the "test" folder the test can be run by clicking
the "Run all Tests".



## References

[//]: # (TODO: Include references here, if any. For example, if you have used code from the course book, include a reference to the chapter.
Or if you have used code from a website or other source, include a link to the source.)
