package package1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String expressionForTest = scanner.nextLine();
        System.out.println(calc(expressionForTest));
    }

    public static String calc(String input){
        return Calculator.calculate(input);
    }
}
