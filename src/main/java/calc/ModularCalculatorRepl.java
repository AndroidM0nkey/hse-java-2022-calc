package calc;

import calc.parser.ModularCalculator;
import calc.parser.utils.ParserException;

import java.util.Scanner;

public class ModularCalculatorRepl {
    public static void main(String[] args) {
        ModularCalculatorRepl app = new ModularCalculatorRepl();
        app.solve();
    }

    public void solve() {
        ModularCalculator modularCalculator = new ModularCalculator();

        System.out.println("Доступные команды:\ncalc - вычислить выражение\nnew - добавить новую команду\nquit - завершить программу");
        String input = Input.next();
        while (!input.equals("quit")) {
            if (input.equals("calc")) {
                System.out.println("Введите выражение:");
                try {
                    Double result = modularCalculator.process(Input.next());
                    System.out.println("Результат вычислений = " + result);
                } catch (ParserException e) {
                    System.err.println("Во время вычислений произошла ошибка: " + e.getLocalizedMessage());
                }
            } else if (input.equals("new")) {
                System.out.println("В разработке....");
            } else {
                System.out.println("Неизвестная команда");
            }
            input = Input.next();
        }
        System.out.println("Пока!");
    }

    public void restart() {

    }

    private static class Input {
        static Scanner scanner = new Scanner(System.in);

        static public String next() {
            return scanner.nextLine().trim();
        }
    }
}
