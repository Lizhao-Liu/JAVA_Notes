

# Hello World

```java
package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello World");
    }
}
```
> Notes
- Code stored in src folder;
- All Java source code files must have a `.java` extension;
- Run `javac HelloWorld.java` on the command line, which produces a bytecode file with a `.class` extension;
- Run the program you use `java HelloWorld` 
    - Problem: 
    ```
    Error: Could not find or load main class Helloworld.javac. 
    Caused by: java.lang.ClassNotFoundException: Helloword.javac
    ```
    If use template, pay attention to `package com.company`
    - Solution: 
    Go back to src directory, run `java com.company.Helloworld`
    

# Triangle
```java
class Triangle {
    int x, y, z;
    public Triangle(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public int getLongestSide(){
        return Math.max(Math.max(x, z), Math.max(y, z));
        //return (x > y) ? Math.max(x, z) : Math.max(y, z);
    }

    public String toString(){

        return "Triangle with sides of length " + x +", " + y +", " + z;
    }


   public static void main(String[] args){
       Triangle test1 = new Triangle(1, 3, 3);
       int longest = test1.getLongestSide();
       System.out.println(test1.toString());
       System.out.println("The longest side of the triangle is " + longest);
    }
}
```
> Notes
-  `Math.max(a, b)`
-  "glue" separate Strings together in Java by using the + concatenation operator. Java will even let you concatenate different types together.


# TwoDimensionalShape
```java
class Triangle extends TwoDimensionalShape{
    int x, y, z;
    public Triangle(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public int getLongestSide(){
        return Math.max(Math.max(x, z), Math.max(y, z));
        //return (x > y) ? Math.max(x, z) : Math.max(y, z);
    }

    public String toString(){

        return "Triangle with sides of length " + x +", " + y +", " + z;
    }
    
    public double calculateArea() {
        return -1;
    }

    public int calculatePerimeterLength() {
        return -1;
    }
}

class Circle extends TwoDimensionalShape {
    int radius;

    public Circle(int r) {
        radius = r;
    }

    double calculateArea() {
        return (int)Math.round(Math.PI * radius * radius);
    }

    int calculatePerimeterLength() {
        return (int)Math.round(Math.PI * radius * 2.0);
    }

    public String toString() {
        return "Circle with radius " + radius;
    }
}

class Rectangle extends TwoDimensionalShape {
    int width;
    int height;

    public Rectangle(int w, int h) {
        width = w;
        height = h;
    }

    double calculateArea() {
        return width * height;
    }

    int calculatePerimeterLength() {
        return 2 * (width + height);
    }

    public String toString() {
        return "Rectangle of dimensions " + width + " x " + height;
    }
}

abstract class TwoDimensionalShape {
    private Colour shapecolour;

    public TwoDimensionalShape() {
    }

    abstract double calculateArea();

    abstract int calculatePerimeterLength();

    Colour getColour(){
        return this.shapecolour;
    }

    void setColour(Colour colour){
        this.shapecolour = colour;
    }
}

public enum Colour {
    RED, GREEN, BLUE, YELLOW, CYAN, PURPLE, PINK, ORANGE, BROWN, WHITE, BLACK, GREY
}


public class shape {
    public static void main(String[] args){
        TwoDimensionalShape [] shape = new TwoDimensionalShape [3];
        shape[0] = new Circle(2);
        shape[1] = new Rectangle(3, 4);
        shape[2] = new Triangle(3, 4, 5);
        for(TwoDimensionalShape scurr : shape){
            scurr.setColour(Colour.CYAN);
            scurr.calculateArea();
            scurr.calculatePerimeterLength();
            System.out.println(scurr.toString());
            System.out.println(scurr.getColour());
        }
    }
}
 ```
 
> Notes
- Inheritance, Polymorphism, Encapsulation
- Using the `extends` keyword in your `Triangle` class to link it to the `TwoDimensionalShape` class
- If try to print out an instance of a class by passing it to `println`, it will call the `toString` method of that object in order to get a printable string
- The use of `Colour` class
- Controlling access
