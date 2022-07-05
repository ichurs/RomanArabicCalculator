package package1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// класс имеет один метод:
// метод принимает строку, проводит серию проверок на условия
// возвращает список из трех элементов: операнд1, операнд2, операция
public class Interpreter {
    static boolean isArabic;
    public static List<String> interpretString(String expression){

        Pattern latinPattern = Pattern.compile("[a-zA-Z]");
        Pattern arabicPattern = Pattern.compile("[0-9]");
        Pattern operatorPattern = Pattern.compile("[+*/-]");
        Matcher latinMatcher = latinPattern.matcher(expression);
        Matcher arabicMatcher = arabicPattern.matcher(expression);
        Matcher operatorMatcher = operatorPattern.matcher(expression);

        // проверяем строку на отсутствие одновременно буквенных и цифровых символов
        if (latinMatcher.find() && arabicMatcher.find()){
            throw new IllegalArgumentException(" //т.к. используются одновременно разные системы счисления");
        } else if(latinMatcher.find()) {
            isArabic = false;
        } else {
            isArabic = true;
        }

        // разделяем строку по знаку операции, в случае отсутствие такового в строке - выбрасываем исключение
        List<String> operands = new ArrayList<>();
        if (operatorMatcher.find()){
            String [] splitExpression = expression.split("[+*/-]");
            if(splitExpression.length > 2){
                throw new IllegalArgumentException(" //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
            // добавляем выделенные из строки операнды в список
            operands.addAll(Arrays.asList(splitExpression));
            // выделяем из строки знак операции и добавляем в список последним элементом
            String operator = expression.substring((splitExpression[0].length()),(splitExpression[0].length()+1));
            operands.add(operator);
        }else {
            throw new IllegalArgumentException(" //т.к. строка не является математической операцией");
        }

        return operands;
    }

}
