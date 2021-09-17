import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    List<Integer> numbers = new ArrayList<>();
    List<String> operations = new ArrayList<>();

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        try {
            calculator.calculations();
        } catch (RuntimeException runtimeException) {
            System.out.println("Вы ввели некорректное число!");
            calculator.calculations();
        }

    }

    public String inputValidation() {
        System.out.print("Введите выражение: ");
        Scanner in = new Scanner(System.in);
        String inputString = in.nextLine();
        if (inputString.matches("-?\\s*\\d+(\\s*[+\\-*/]\\s*\\d+)*")) {
            System.out.println("Вы ввели: " + inputString);
        } else {
            System.out.println("Попробуйте ввести выражение заново\nПринимаются только целочисленые значения и операции +, -, *, /");
            inputValidation();
        }
        return inputString;
    }

    public void itemSearch() {
        String inputString = inputValidation();
        Pattern numbersPattern = Pattern.compile("\\d+");
        Pattern operationsPattern = Pattern.compile("[+\\-*/]");
        Matcher numbersMatcher = numbersPattern.matcher(inputString);
        Matcher operationsMatcher = operationsPattern.matcher(inputString);
        while (numbersMatcher.find()) {
            numbers.add(Integer.valueOf(numbersMatcher.group()));
        }
        while (operationsMatcher.find()) {
            operations.add(operationsMatcher.group());
        }
        if (numbers.size() == operations.size()) {
            numbers.add(0, 0);
        }
        System.out.println(numbers);
        System.out.println(operations);
    }

    public void calculations() {
        itemSearch();
        while (operations.contains(Character.toString('*'))) {
            int indexMultiply = operations.indexOf(Character.toString('*'));
            numbers.set(indexMultiply, numbers.get(indexMultiply) * numbers.get(indexMultiply + 1));
            numbers.remove(indexMultiply + 1);
            operations.remove(indexMultiply);
        }
        while (operations.contains(Character.toString('/'))) {
            int indexMultiply = operations.indexOf(Character.toString('/'));
            numbers.set(indexMultiply, numbers.get(indexMultiply) / numbers.get(indexMultiply + 1));
            numbers.remove(indexMultiply + 1);
            operations.remove(indexMultiply);
        }
        while (operations.contains(Character.toString('-'))) {
            int indexMultiply = operations.indexOf(Character.toString('-'));
            numbers.set(indexMultiply, numbers.get(indexMultiply) - numbers.get(indexMultiply + 1));
            numbers.remove(indexMultiply + 1);
            operations.remove(indexMultiply);
        }
        while (operations.contains(Character.toString('+'))) {
            int indexMultiply = operations.indexOf(Character.toString('+'));
            numbers.set(indexMultiply, numbers.get(indexMultiply) + numbers.get(indexMultiply + 1));
            numbers.remove(indexMultiply + 1);
            operations.remove(indexMultiply);
        }

        System.out.println("Результат: " + numbers.get(0));
    }
}