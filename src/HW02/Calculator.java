package HW02;

/*
Print out the list of operations for the user.
Prompt the user to enter an operation. This operation must be processed as case-insensitive.
If the user enters an invalid operation, the program should print the following error message and terminate gracefully.
Invalid input entered. Terminating...
Perform the chosen operation and print the correct output.
If the user is performing an add/subtract operation, prompt the user to enter two integers.
If the user is performing a multiply/divide operation, prompt the user to enter two doubles.
If the user is performing an alphabetize operation, prompt the user to enter two words.
If the user inputs an invalid type (e.g. inputs doubles for add/subtract) for the given operation, print the same error message shown above.
The program should terminate gracefully after the result of the operation is printed.
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    public static void add(int a, int b){
        System.out.printf("Answer: %d\n", a + b);
    }
    public static void subtract(int a, int b){
        System.out.printf("Answer: %d\n", a - b);
    }
    public static void multiply(double a, double b){
        System.out.printf("Answer: %.2f\n", a * b);
    }
    public static void divide(double a, double b){
        if (b == 0){
            System.out.println("Invalid input entered. Terminating...");
        } else {
            System.out.printf("Answer: %.2f\n", a / b);
        }
    }
    public static void alphabetize(String w1, String w2){
        if (w1.toLowerCase().compareTo(w2.toLowerCase()) < 0){
            System.out.printf("Answer: %s comes before %s alphabetically.\n", w1, w2);
        } else if (w1.toLowerCase().compareTo(w2.toLowerCase()) == 0) {
            System.out.println("Answer: Chicken or Egg.");
        } else {
                System.out.printf("Answer: %s comes before %s alphabetically.\n", w2, w1);
        }
    }
    public static Object[] getInputType(Class<?> cls, Scanner input){
        try {
            if (cls == String.class) {
                String[] result = new String[2];
                result[0] = input.next();
                result[1] = input.next();
                return result;
            } else if (cls == int.class) {
                Integer[] result = new Integer[2];
                result[0] = input.nextInt();
                result[1] = input.nextInt();
                return result;
            } else if (cls == double.class) {
                Double[] result = new Double[2];
                result[0] = input.nextDouble();
                result[1] = input.nextDouble();
                return result;
            }} catch (InputMismatchException e){
            System.out.println("Invalid input entered. Terminating...");
            System.exit(0);
        }
        System.out.println("Invalid input entered. Terminating...");
        System.exit(0);
        return null;
    }
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("List of operations: add subtract multiply divide alphabetize\nEnter an operation:\n");
        String operation = input.next();
        Object[] doubles;
        Object[] ints;
        Object[] strs;
        switch (operation.toLowerCase()) {
            case "add":
                System.out.println("Enter two integers:");
                ints = getInputType(int.class, input);
                add((int) ints[0], (int) ints[1]);
                break;
            case "subtract":
                System.out.println("Enter two integers:");
                ints = getInputType(int.class, input);
                subtract((int) ints[0], (int) ints[1]);
                break;
            case "multiply":
                System.out.println("Enter two doubles:");
                doubles = getInputType(double.class, input);
                multiply((double) doubles[0], (double) doubles[1]);
                break;
            case "divide":
                System.out.println("Enter two doubles:");
                doubles = getInputType(double.class, input);
                divide((double) doubles[0], (double) doubles[1]);
                break;
            case "alphabetize":
                System.out.println("Enter two words:");
                strs = getInputType(String.class, input);
                alphabetize((String) strs[0], (String) strs[1]);
                break;
            default:
                System.out.println("Invalid input entered. Terminating...");
                break;
        }
    }
}
