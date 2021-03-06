import java.util.*;

class ListWorld {
    static void printList(List<Robot> list) {
        System.out.print("List is:");
        for (Robot robot : list) {
            System.out.print(robot.name+',');
        }
        System.out.println("");
    }

    public static void main(String args[]) {
        List<Robot> list = new ArrayList<>();
        Robot c3po = new Robot("C3PO");
        list.add(c3po);
        list.add(new CarrierRobot());
        printList(list);
        list.add(1, new Robot("C4PO"));
        printList(list);
        Robot removed = list.remove(2);
        System.out.println("Removed:"+ removed.name);
        printList(list);
        System.out.println("C3PO in list?:" + list.contains(c3po));
        list.addAll(0,list);
        printList(list);
    }
}
