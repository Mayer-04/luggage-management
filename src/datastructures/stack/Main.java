package datastructures.stack;

public class Main {

    public static void main(String[] args) {
        var stack = new Stack<String>();

        stack.push("Mayer");
        stack.push("Andres");
        stack.push("Chaves");

        System.out.println(stack);

        System.out.println("Elemento eliminado: " + stack.pop());

        System.out.printf("Nueva pila: %s\n", stack);
    }
}
