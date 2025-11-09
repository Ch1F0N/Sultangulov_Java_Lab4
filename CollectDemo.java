import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class CollectDemo {

    public static <T, P> P collect(
            List<T> source,
            Supplier<P> collectionFactory,
            BiConsumer<P, T> accumulator) {

        P result = collectionFactory.get();
        for (T item : source) {
            accumulator.accept(result, item);
        }
        return result;
    }


    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Демонстрация коллекционирования ===");
        System.out.println("1 — Разделение чисел на положительные и отрицательные");
        System.out.println("2 — Группировка строк по длине");
        System.out.println("3 — Удаление дубликатов строк");
        System.out.print("Ваш выбор: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> demoNumbers(scanner);
            case "2" -> demoStringsByLength(scanner);
            case "3" -> demoUniqueStrings(scanner);
            default -> System.out.println("Неверный выбор!");
        }
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


        Map<String, List<Integer>> result = collect(
                numbers,
                HashMap::new,
                (map, value) -> {
                    if (value >= 0) {
                        String key = "Положительные";
                        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
                    } else {
                        String key = "Отрицательные";
                        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
                    }
                });

        System.out.println("\nРезультат:");
        result.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    private static void demoStringsByLength(Scanner scanner) {
        System.out.println("\nВведите строки через пробел:");
        String[] parts = scanner.nextLine().split("\\s+");
        List<String> strings = Arrays.asList(parts);

        Map<Integer, List<String>> result = collect(
                strings,
                HashMap::new,
                (map, str) -> {
                    int len = str.length();
                    map.computeIfAbsent(len, k -> new ArrayList<>()).add(str);
                });

        System.out.println("\nРезультат (группировка по длине):");
        result.forEach((len, group) ->
                System.out.println("Длина " + len + ": " + group)
        );
    }

    private static void demoUniqueStrings(Scanner scanner) {
        System.out.println("\nВведите строки через пробел:");
        String[] parts = scanner.nextLine().split("\\s+");
        List<String> strings = Arrays.asList(parts);

        Set<String> result = collect(
                strings,
                HashSet::new,
                Set::add);

        System.out.println("\nРезультат (уникальные строки): " + result);
    }
}
