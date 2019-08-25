package com.company.calculator;

import com.company.enums.Choice;
import com.company.enums.MathOperation;
import com.company.enums.OperationType;
import com.company.exceptions.NegativeNumberException;
import com.company.exceptions.ZeroException;
import com.company.pojo.Operation;
import com.company.store.MessageStore;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.company.consts.Constants.MINUS;
import static com.company.consts.Constants.ZERO;

public class Calculator {
    private static Calculator instance;

    public static Calculator getInstance() {
        if (instance == null) {
            instance = new Calculator();
        }
        return instance;
    }

    private Calculator() {

    }

    private List<String> input;
    private MessageStore messageStore = MessageStore.getInstance();
    private Scanner scanner = new Scanner(System.in);
    private MathOperation mathOperation;

    public void run() {
        makeChoice();
    }

    private void makeChoice() {
        input = new ArrayList<>();
        System.out.println("====================================");
        System.out.println("Список команд: ");
        System.out.println("exit - выход ");
        System.out.println("sum - сумма двух чисел ");
        System.out.println("multi - сумма десяти введеных чисел ");
        System.out.println("sqrt - вычесление квадратного корня ");
        System.out.println("====================================");
        System.out.print("Сделайте выбор: ");

        String currentChoice = scanner.nextLine();

        if (currentChoice.equalsIgnoreCase(Choice.EXIT.getChoice())) {
            System.out.println("Хорошего дня!");
        } else if (currentChoice.equalsIgnoreCase(Choice.MULTI_SUM.getChoice())) {
            mathOperation = MathOperation.SUM;
            doOperations(OperationType.MULTISUM);
        } else if (currentChoice.equalsIgnoreCase(Choice.SQRT.getChoice())) {
            mathOperation = MathOperation.SQRT;
            doOperations(OperationType.SQRT);
        } else if (currentChoice.equalsIgnoreCase(Choice.SUM.getChoice())) {
            mathOperation = MathOperation.SUM;
            doOperations(OperationType.SUM);
        } else {
            System.out.println("Введите верную команду!");
            makeChoice();
        }
    }

    private void doOperations(OperationType operationType) {
        List<Operation> currentOperation = messageStore.getOperationsByType(operationType);


        for (Operation operation : currentOperation) {
            String message = operation.getMessage();

            if (message != null) {
                System.out.println(message);
            }

            getDataAndCatchErrors(operation);
        }
        showResult();
        makeChoice();
    }

    private void getDataAndCatchErrors(Operation operation) {
        try {
            getAndValidateData(operation);
        } catch (ZeroException exception) {
            System.out.println("Вводимое не может ровняться нулю!");
            getDataAndCatchErrors(operation);
        } catch (NegativeNumberException exception) {
            System.out.println("Вводимое число должно быть положительным!");
            getDataAndCatchErrors(operation);
        } catch (InputMismatchException exception) {
            System.out.println("Вводить можно только числа!");
            scanner.next();
            getDataAndCatchErrors(operation);
        } finally {
            scanner.nextLine();
        }
    }

    private void getAndValidateData(Operation operation) throws
            ZeroException,
            NegativeNumberException,
            InputMismatchException {
        String nextInput;

        switch (operation.getValidation()) {
            case NUMBER:
                input.add(Double.toString(scanner.nextDouble()));
                break;
            case NO_ZERO:
                nextInput = Double.toString(scanner.nextDouble());

                if (nextInput.equals(ZERO)) {
                    throw new ZeroException();
                }
                input.add(nextInput);
                break;

            case POSITIVE_NUMBER:
                nextInput = Double.toString(scanner.nextDouble());

                if (nextInput.contains(MINUS)) {
                    throw new NegativeNumberException();
                }
                input.add(nextInput);
                break;
        }
    }

    private void showResult() {
        String result = ZERO;

        for (String element : input) {
            result = calculateResult(result, element);
        }

        System.out.println("Результат вычислений: " + result);
    }

    private String calculateResult(String first, String second) {
        Double firstNumber = Double.parseDouble(first);
        Double secondNumber = Double.parseDouble(second);

        switch (mathOperation) {
            case SQRT:
                return Double.toString(Math.sqrt(secondNumber));
            case SUM:
                return Long.toString(Math.round(firstNumber + secondNumber));
            default:
                return null;
        }

    }
}
