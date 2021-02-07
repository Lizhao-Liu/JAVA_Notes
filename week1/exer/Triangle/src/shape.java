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
//If you try to print out an instance of a class by passing it to println,
// it will call the toString method of that object in order to get a printable string !