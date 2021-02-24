# Further Language Struct

## Notes
- Abstract 
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

-  Enumerations
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
