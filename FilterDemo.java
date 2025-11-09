import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FilterDemo {

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T value : list) {
            if (predicate.test(value)) {
                result.add(value);
            }
        }
        return result;
    }

    public static void demo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Демонстрация фильтрации ===");
        System.out.println("Выберите вариант демонстрации:");
        System.out.println("1 — Фильтр строк (длина >= 3)");
        System.out.println("2 — Фильтр чисел (только отрицательные)");
        System.out.println("3 — Фильтр массивов (только без положительных чисел)");
        System.out.print("Ваш выбор: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                demoStringFilter(scanner);
                break;
            case "2":
                demoNumberFilter(scanner);
                break;
            case "3":
                demoArrayFilter(scanner);
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void demoStringFilter(Scanner scanner) {
        System.out.println("\nВведите строки (через пробел):");
        String[] parts = scanner.nextLine().split("\\s+");
        List<String> strings = Arrays.asList(parts);

        List<String> filtered = filter(strings, s -> s.length() >= 3);
        System.out.println("Результат: " + filtered);
    }

    private static void demoNumberFilter(Scanner scanner) {
        System.out.println("\nВведите целые числа (через пробел):");
        String[] parts = scanner.nextLine().split("\\s+");
        List<Integer> numbers = new ArrayList<>();

        for (String p : parts) {
            try {
                numbers.add(Integer.parseInt(p));
            } catch (NumberFormatException e) {
                System.out.println("Пропущено некорректное значение: " + p);
            }
        }

        List<Integer> filtered = filter(numbers, x -> x < 0);
        System.out.println("Результат: " + filtered);
    }

    private static void demoArrayFilter(Scanner scanner) {
        System.out.println("\nВведите количество массивов: ");
        int n = 0;
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

        List<int[]> arrays = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            System.out.println("Введите элементы массива №" + i + " (через пробел): ");
            String[] parts = scanner.nextLine().split("\\s+");
            List<Integer> temp = new ArrayList<>();

            for (String p : parts) {
                try {
                    temp.add(Integer.parseInt(p));
                } catch (NumberFormatException e) {
                    System.out.println("Пропущено некорректное значение: " + p);
                }
            }

            int[] arr = temp.stream().mapToInt(Integer::intValue).toArray();
            arrays.add(arr);
        }

        List<int[]> filtered = filter(arrays, arr -> {
            for (int val : arr) {
                if (val > 0) return false;
            }
            return true;
        });

        System.out.println("\nРезультат:");
        if (filtered.isEmpty()) {
            System.out.println("Нет массивов, удовлетворяющих условию.");
        } else {
            int index = 1;
            for (int[] arr : filtered) {
                System.out.println("Массив #" + index++ + ": " + Arrays.toString(arr));
            }
        }
    }
}
