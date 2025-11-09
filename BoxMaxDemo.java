import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BoxMaxDemo {


    public static double findMax(Box<? extends Number>... boxes) {
        if (boxes == null || boxes.length == 0) {
            throw new IllegalArgumentException("Список коробок пуст!");
        }

        double max = Double.NEGATIVE_INFINITY;
        boolean hasValue = false;

        for (Box<? extends Number> box : boxes) {
            if (box == null || box.isEmpty()) continue;

            Number value = box.peek();
            if (value != null) {
                double val = value.doubleValue();
                if (!hasValue || val > max) {
                    max = val;
                    hasValue = true;
                }
            }
        }

        if (!hasValue) {
            throw new IllegalArgumentException("Во всех коробках нет числовых значений!");
        }

        return max;
    }

    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        List<Box<? extends Number>> boxes = new ArrayList<>();

        System.out.println("=== Поиск максимума среди коробок ===");

        int n = 0;
        while (true) {
            System.out.print("Введите количество коробок (1–10): ");
            try {
                n = Integer.parseInt(scanner.nextLine().trim());
                if (n <= 0 || n > 10) {
                    System.out.println("Ошибка: введите число от 1 до 10.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println("\nКоробка №" + i);
            System.out.println("Выберите тип содержимого:");
            System.out.println("1 — Integer");
            System.out.println("2 — Double");
            System.out.println("3 — Float");
            System.out.println("4 — Long");
            System.out.print("Ваш выбор: ");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1": {
                        Box<Integer> b = new Box<>();
                        Integer value = readInt(scanner, "Введите целое число: ");
                        b.put(value);
                        boxes.add(b);
                        break;
                    }
                    case "2": {
                        Box<Double> b = new Box<>();
                        Double value = readDouble(scanner, "Введите вещественное число (через точку): ");
                        b.put(value);
                        boxes.add(b);
                        break;
                    }
                    case "3": {
                        Box<Float> b = new Box<>();
                        Float value = readFloat(scanner, "Введите число с плавающей точкой (через точку): ");
                        b.put(value);
                        boxes.add(b);
                        break;
                    }
                    case "4": {
                        Box<Long> b = new Box<>();
                        Long value = readLong(scanner, "Введите длинное целое число: ");
                        b.put(value);
                        boxes.add(b);
                        break;
                    }
                    default:
                        System.out.println("Неверный выбор, создаётся пустая коробка.");
                        boxes.add(new Box<>());
                }
            } catch (Exception e) {
                System.out.println("Ошибка при создании коробки: " + e.getMessage());
            }
        }

        try {
            double max = findMax(boxes.toArray(new Box[0]));
            System.out.println("\nМаксимальное значение среди всех коробок: " + max);
        } catch (Exception e) {
            System.out.println("Ошибка при поиске максимума: " + e.getMessage());
        }
    }

    private static Integer readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    private static Double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное вещественное число (через точку).");
            }
        }
    }

    private static Float readFloat(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Float.parseFloat(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число (через точку).");
            }
        }
    }

    private static Long readLong(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное длинное целое число.");
            }
        }
    }
}
