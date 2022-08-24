# PropertyTycoon
 
 ## Team Members:
 Kieran Young, Hannah OH, Hanzhen Gong, Leon Feng, Ekin Zorel, Ali Ahmed

## Getting Started:
The game is in the form of a Java application, running at a resolution of 1280x720 (minimum required to display). It works on both Windows and OSX and can be ran using Main.java under the source directory. Compilation requires JavaFX and JUnit and is compiled using Java 1.8.

## Backend (Java)
The framework for the backend was written in Java. We took an object-oriented approach by translating the components of the original physical version into their own classes.

## Frontend (JavaFX)
The GUI was designed using SceneBuilder, which produces an FXML format that was directly compatible with JavaFX code. We utilised multiple different objects such as: Panes, Text, Images and Buttons. This was limiting in some aspects but had all the functionality we required to carry out the project. This library is relatively well optimised and allows the game to perform quickly regardless of platform, and had zero loading time which made it very effective for quick testing and development.

## Testing
Since we decided on using Java it was suitable for us to implement unit testing using the standard JUnit library. For each class we produced a unit test that would go over the core functions of the class and test that they are working in line with our expectations. This allowed us to quickly identify logical errors that would not have been picked up by the IDE when developing and squish any bugs found on the basic level.

During the sprint cycles and towards the end of the development we focused more on general feature based testing which we did in the System Test document. This was a crucial part of the development process as it allowed us to identify key issues early before they evolved into problems that would halt progress. From the system tests we had to make some key decisions about our final product and in cases like the auctioning feature, we had to disable some functionality for the final version.

## UML Diagram
![Final Class Diagrams](https://user-images.githubusercontent.com/84683922/184548305-f7a79d1f-4ee0-40c8-8ba5-a81a0a64cd3e.png)
