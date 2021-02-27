# Further Language Struct

## Notes
### Abstract 
``` Java
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
```
  - Abstract class includes abstract methods (just like prototypes);
  - Subclasses which inherit the abstract classes must have the same methods and will override them. 


``` Java
//Rectangle
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

//Circle
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
```
  - These two methods will "override" the abstract ones defined in the TwoDimensionalShape class. The reason that these are defined as "abstract" in the TwoDimensionalShape class is that: all shapes will have an area and perimeter length, but each shape will calculate these differently (so we can't write anything sensible inside the TwoDimensionalShape class).

###  Enumerations
```Java
public enum TriangleVariant {
    EQUILATERAL, ISOSCELES, SCALENE, RIGHT, FLAT, IMPOSSIBLE, ILLEGAL;
}
```
  - When you call the specific type, use e.g. `TriangleVariant.EQUILATERAL`.
```Java
if(x <= 0 || y <= 0 || z <= 0){ this.variant = TriangleVariant.ILLEGAL;}
else if((x + y) > z && (x + z) > y && (y + z) > x){
    if(x == y || x == z || y == z){
         if(x == y && x == z){
               this.variant = TriangleVariant.EQUILATERAL;
          }
         else{
               this.variant = TriangleVariant.ISOSCELES;
          }
    }
    else{
         this.variant = TriangleVariant.SCALENE;
    }
    if(Math.pow(x, 2)+Math.pow(y, 2) == Math.pow(z, 2) || Math.pow(x, 2)+Math.pow(z, 2) == Math.pow(y, 2) || Math.pow(y, 2)+Math.pow(z, 2) == Math.pow(x, 2)){
         this.variant = TriangleVariant.RIGHT;
    }
}
else if ((x + y) == z || (x + z) == y || (y + z) == x){
    this.variant = TriangleVariant.FLAT;
}
else{
    this.variant = TriangleVariant.IMPOSSIBLE;
}
```
### Interface
- think of an Interface as a contract that the class "agrees to" and guarantees to provide certain properties and features. 
- As an illustration of interfaces in action, we have provided a MultiVariantShape interface that can be used to indicate that a shape has more than one variant. We would use this interface to mark our Triangle class like so:

```java
class Triangle extends TwoDimensionalShape implements MultiVariantShape
```

We can test to see if an object conforms to an interface by using the Java instanceof keyword. So, for example, we can test any shape to see if it implements the MultiVariantShape interface using the following code:

```java
if(shape instanceof MultiVariantShape) System.out.println("This shape has multiple variants")
else System.out.println("This shape has only one variant")
```
- You may have noticed that the MultiVariantShape interface defines a method called getVariant that returns the variant of a shape. This allows code outside of the class to interrogate an instance in order to find out what variant it is.

### test
- `java -ea TriangleTester` in the shell.
-  adding `-ea` to the command line parameters of your IDE: 1. edit configuration 2. modify options 3. add VM options 4. add `-ea`
- If you try to print out an instance of a class by passing it to println, it will call the **toString** method of that object in order to get a printable string !

### Array
- instantiate array of references to a number of objects( int, float, char, complex object...).
- at start, array locations carry null.
- difference between foreach(using `:` notation) and for loop:

> They are basically the same, but for-each (the second one) has certain restrictions. 
> It can be used for accessing the array elements but not for modifying them.
> It is not usable for loops that must iterate over multiple collections in parallel—for example, to compare the elements of two arrays.
> It can be used only for a single element access and cannot be used to compare successive elements in an array. It is a forward-only iterator. If you want to access only a few elements of the array, you would need to use the traditional for loop.

- Math.random() returns a randomly selected double precision floating point number (between 0.0 and 1.0).

### Class Variables and Methods
- class variables are associated with the class (rather than each instance); class methods re similarly associated with the class (not the instances). (e.g. the random method of the Math class)
- when using a Class variable, we access it via the Class name, rather than an object name. e.g. `Triangle.population`
- non-static method getPopulation() cannot be referenced from a static context

### casting
- we can "down-cast" (or "narrow-cast") any TwoDimentionalShape into one of it's sub-classes.
- error `class Rectangle cannot be cast to class Triangle (Rectangle and Triangle are in unnamed module of loader 'app')`
