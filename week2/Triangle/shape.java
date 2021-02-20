public class shape {

    public static void main(String[] args) {
        int i = 0;
//       int count=0;
        TwoDimensionalShape[] shape = new TwoDimensionalShape[100];
        for (; i < 100 ; i++) {
            double flag = Math.random();
            if (flag < (1.0 / 3)) {
                int r = (int) (100 * Math.random());
                shape[i] = new Circle(r);
            } else if (flag > (2.0 / 3)) {
                int h = (int) (100 * Math.random());
                int w = (int) (100 * Math.random());
                shape[i] = new Rectangle(w, h);
            } else {
                long x = (long) (100 * Math.random());
                long y = (long) (100 * Math.random());
                long z = (long) (100 * Math.random());
                shape[i] = new Triangle(x, y, z);
                Triangle temp = (Triangle)shape[i];
                System.out.println("this is a "+ temp.variant + " triangle");
            }
        }
//        for (TwoDimensionalShape ncurr : shape){
//            if (ncurr instanceof MultiVariantShape) {
//                count++;
//            }
//        }

//        System.out.println("there are " + Triangle.population + " triangles");
        System.out.println("there are " + Triangle.getPopulation() + " triangles");

    }

}
//Static java: non-static method getPopulation() cannot be referenced from a static context



//If you try to print out an instance of a class by passing it to println,
// it will call the toString method of that object in order to get a printable string !
// : shape 不能给array复制， 用指针的形式赋值。
//-ea VM