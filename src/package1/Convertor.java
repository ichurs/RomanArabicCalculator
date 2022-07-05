package package1;

import java.util.List;

// класс имеет два метода:
// первый метод преобразует строку латинских букв в целое арабское число
// второй метод преобразует целое арабское число в строку латинских букв

public class Convertor {
    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase().replaceAll("\\s+","");
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " // т.к. данные символы не используются в римской системе");
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(number + " // т.к. в римской системе нет отрицательных чисел");
        } else if (number > 4000) {
            throw new IllegalArgumentException(number + " // т.к. в римской системе нет чисел больше 3999");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}
