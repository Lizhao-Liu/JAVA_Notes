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


