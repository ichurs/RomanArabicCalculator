package package1;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public static String calculate(String input){

        // преобразуем полученную строку в список строк
        List<String> expression = Interpreter.interpretString(input);
        int operand1;
        int operand2;
        String operation = expression.get(2);
        int result = 0;
        String resultString;

        List<Integer> arabicOperands = new ArrayList<>();

        // проверяем выражение, если цифры латинские - преобразуем в арабские
        // список строк преобразуем в список целых чисел
        if (!Interpreter.isArabic){
             operand1 = Convertor.romanToArabic(expression.get(0));
             operand2 = Convertor.romanToArabic(expression.get(1));
        } else{
            operand1 = Integer.parseInt(expression.get(0));
            operand2 = Integer.parseInt(expression.get(1));
        }

        // выполняем арифметическую операцию в зависимости от знака операции
        switch (operation){
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                try {
                    result =  operand1 / operand2;
                }
                catch(ArithmeticException ae){
                    System.err.println(ae.getMessage() + " // т.к. невозможно деление на ноль");
                }
                break;
            default: break;
        }

        // проводим обратную операцию по преобразованию арабских цифр в латинские, если это необходимо
        if(!Interpreter.isArabic){
            resultString = Convertor.arabicToRoman(result);
        } else {
            resultString = String.valueOf(result);
        }

        return resultString;
    }
}
