import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FunctionDemo {

    public static <T, P> List<P> transform(List<T> list, Function<T, P> func) {
        List<P> result = new ArrayList<>();
        for (T value : list) {
            result.add(func.apply(value));
        }
        return result;
    }

    public static void demo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Демонстрация обобщённой функции ===");
        System.out.println("Выберите вариант демонстрации:");
        System.out.println("1 — Строки → длины строк");
        System.out.println("2 — Числа → абсолютные значения");
        System.out.println("3 — Массивы чисел → максимумы");
        System.out.print("Ваш выбор: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                demoStringToLength(scanner);
                break;
            case "2":
                demoNumbersToAbsolute(scanner);
                break;
            case "3":
                demoArraysToMax(scanner);
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void demoStringToLength(Scanner scanner) {
        System.out.println("\nВведите строки (через пробел):");
        String[] parts = scanner.nextLine().split("\\s+");
        List<String> strings = Arrays.asList(parts);

        List<Integer> lengths = transform(strings, s -> s.length());
        System.out.println("Результат: " + lengths);
    }

    private static void demoNumbersToAbsolute(Scanner scanner) {
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

        List<Integer> absValues = transform(numbers, x -> Math.abs(x));
        System.out.println("Результат: " + absValues);
    }

    private static void demoArraysToMax(Scanner scanner) {
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

        List<Integer> maxValues = transform(arrays, arr -> {
            if (arr.length == 0) return null;
            int max = arr[0];
            for (int val : arr) {
                if (val > max) max = val;
            }
            return max;
        });

        System.out.println("Результат: " + maxValues);
    }
}
