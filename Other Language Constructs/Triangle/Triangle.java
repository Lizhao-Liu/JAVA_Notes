class Triangle extends TwoDimensionalShape implements MultiVariantShape{
    long x, y, z;
    TriangleVariant variant;
    static int population = 0;
    public Triangle(long x, long y, long z){
        this.x = x;
        this.y = y;
        this.z = z;
        population++;
        if(x <= 0 || y <= 0 || z <= 0){
            this.variant = TriangleVariant.ILLEGAL;
        }
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
    }

    public static int getPopulation(){
        return Triangle.population;
    }
    public TriangleVariant getVariant(){
        return variant;
    }

    public long getLongestSide(){
        return Math.max(Math.max(x, z), Math.max(y, z));
        //return (x > y) ? Math.max(x, z) : Math.max(y, z);
    }

    public String toString(){
        return  "Triangle with sides of length " + x +", " + y +", " + z + "\nThis is a " + variant + " Triangle.";
    }
    double calculateArea() {
        double s;
        s = (double)(x + y + z) / 2;
        return Math.sqrt(s * (s - x) * (s - y)* (s - z));
    }

    int calculatePerimeterLength() {
        return (int) (x + y + z);
    }


    public static void main(String[] args){
        Triangle [] tri = new Triangle[5];
        tri[0] = new Triangle(1, 3, 3);
        tri[1] = new Triangle(5, 5, 5);
        tri[2] = new Triangle(7, 7, 14);
        tri[3] = new Triangle(1, 11, 12);
        tri[4] = new Triangle(150000002, 666666671, 683333338);
        for(Triangle curr : tri){
            long longest = curr.getLongestSide();
            System.out.println(curr.toString());
            System.out.println("The longest side of the triangle is " + longest);
            System.out.println("The area is " + (int)curr.calculateArea());
            System.out.println("The perimeter length is " + curr.calculatePerimeterLength() + "\n");
        }
    }
}

// class variable vs instance variable
