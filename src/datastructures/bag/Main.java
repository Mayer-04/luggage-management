package datastructures.bag;

public class Main {

    public static void main(String[] args) {
        var bag = new Bag<Integer>();

        bag.add(2);
        bag.add(4);
        bag.add(6);
        bag.add(8);

        System.out.println(bag); // Output: bag [2,4,6,8]

        for (Integer element : bag) {
            System.out.println("element: " + element);
        }

        System.out.println(bag.size()); // Output: 4

        System.out.println(bag.contains(10)); // Output: false

        bag.clear();

        System.out.println(bag.isEmpty()); // Output: true

        System.out.println(bag); // Output: bag []
        System.out.println(bag.size()); // Output: 0
    }
}
