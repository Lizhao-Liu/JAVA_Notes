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


    public static void main(String[] args){
       Triangle test1 = new Triangle(1, 3, 3);
        int longest = test1.getLongestSide();
        System.out.println(test1.toString());
        System.out.println("The longest side of the triangle is " + longest);
    }
}
