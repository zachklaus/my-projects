# Drawing App

- This project creates a GUI where shapes and text are drawn using data specified in a text file.
- The main goal of this project was to practice inheritance. I was also exposed to Java GUI programming with swing.
- From what I remember I implemented all of the different primitive (things that can be drawn) classes as well as some of the code in DrawProgram.java. I believe I was supplied with most or all of UserInterface.java
- Primitive is the parent class of all the objects that can be drawn.
- Text, PolygonPrimitive, and RoundPrimitive inherit from Primitive.
- Round shapes inherit from RoundPrimitive and polygon shapes inherit from Polygon Primitive.
- Each line in an input file corresponds to one item being drawn in the window.
- Input format is: Primitive Type, hex code for color, coordinates (# depends on primitive - view class constructor for number of coordinates, filled or outline).