import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReductionDemo {

    public static <T> T reduce(List<T> list, Reducer<T> reducer, T defaultValue) {
        if (list == null || list.isEmpty()) {
            return defaultValue;
        }

        T result = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            result = reducer.apply(result, list.get(i));
        }
        return result;
    }

    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Демонстрация сокращения (reduce) ===");
        System.out.println("1 — Объединить строки");
        System.out.println("2 — Суммировать числа");
        System.out.println("3 — Подсчитать общее количество элементов во вложенных списках");
        System.out.print("Ваш выбор: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                demoStrings(scanner);
                break;
            case "2":
                demoNumbers(scanner);
                break;
            case "3":
                demoNestedLists(scanner);
                break;
            default:
                System.out.println("Неверный выбор!");
        }
    }

    private static void demoStrings(Scanner scanner) {
        System.out.println("\nВведите строки через пробел:");
        String[] parts = scanner.nextLine().split("\\s+");
        List<String> strings = Arrays.asList(parts);

        String result = reduce(strings, (a, b) -> a + b, "");
        System.out.println("Результат: " + result);
    }

    private static void demoNumbers(Scanner scanner) {
        System.out.println("\nВведите целые числа через пробел:");
        String[] parts = scanner.nextLine().split("\\s+");
        List<Integer> numbers = new ArrayList<>();

        for (String p : parts) {
            try {
                numbers.add(Integer.parseInt(p));
            } catch (NumberFormatException e) {
                System.out.println("Пропущено некорректное значение: " + p);
            }
        }

        Integer result = reduce(numbers, Integer::sum, 0);
        System.out.println("Результат: " + result);
    }

    private static void demoNestedLists(Scanner scanner) {
        System.out.println("\nВведите количество списков:");
        int n;
        while (true) {
            try {
                n = Integer.parseInt(scanner.nextLine().trim());
                if (n <= 0) {
                    System.out.println("Введите положительное число.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }

        List<List<Integer>> nested = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.println("Введите элементы списка №" + i + " через пробел:");
            String[] parts = scanner.nextLine().split("\\s+");
            List<Integer> temp = new ArrayList<>();
            for (String p : parts) {
                try {
                    temp.add(Integer.parseInt(p));
                } catch (NumberFormatException e) {
                    System.out.println("Пропущено некорректное значение: " + p);
                }
            }
            nested.add(temp);
        }


        Integer total = reduce(
                nested.stream().map(List::size).toList(),
                Integer::sum,
                0
        );

        System.out.println("Общее количество элементов: " + total);
    }
}
