import java.util.Scanner;

public class BoxDemo {

    public static <T> void showBoxContent(Box<T> box) {
        if (box.isEmpty()) {
            System.out.println("Коробка пуста!");
        } else {
            T value = box.get();
            System.out.println("Извлечено значение: " + value);
        }
    }

    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Обобщённая коробка");

        while (running) {
            System.out.println("\nВыберите тип коробки:");
            System.out.println("1. Целое число");
            System.out.println("2. Строка");
            System.out.println("0. Выход");
            System.out.print("Ваш выбор: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    runIntegerBox(scanner);
                    break;
                case "2":
                    runStringBox(scanner);
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void runIntegerBox(Scanner scanner) {
        Box<Integer> intBox = new Box<>();
        System.out.println("\nСоздана коробка для хранения целых чисел.");
        while (true) {
            System.out.println("1. Положить число");
            System.out.println("2. Извлечь число");
            System.out.println("3. Проверить, пуста ли коробка");
            System.out.println("0. Назад");
            System.out.print("Ваш выбор: ");
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        if (!intBox.isEmpty()) {
                            System.out.println("Коробка уже заполнена!");
                            break;
                        }
                        System.out.print("Введите целое число: ");
                        int value = Integer.parseInt(scanner.nextLine());
                        intBox.put(value);
                        System.out.println("Число " + value + " положено в коробку.\n");
                        break;
                    case "2":
                        showBoxContent(intBox);
                        System.out.println();
                        break;
                    case "3":
                        if (intBox.isEmpty()) {
                            System.out.println("Коробка пуста.\n");
                        } else {
                            System.out.println("Коробка не пуста.\n");
                        }
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Неверный выбор.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private static void runStringBox(Scanner scanner) {
        Box<String> strBox = new Box<>();
        System.out.println("\nСоздана коробка для хранения строк.");
        while (true) {
            System.out.println("1. Положить строку");
            System.out.println("2. Извлечь строку");
            System.out.println("3. Проверить, пуста ли коробка");
            System.out.println("0. Назад");
            System.out.print("Ваш выбор: ");
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        if (!strBox.isEmpty()) {
                            System.out.println("Коробка уже заполнена!\n");
                            break;
                        }
                        System.out.print("Введите строку: ");
                        String value = scanner.nextLine();
                        strBox.put(value);
                        System.out.println("Строка \"" + value + "\" положена в коробку.\n");
                        break;
                    case "2":
                        showBoxContent(strBox);
                        System.out.println();
                        break;
                    case "3":
                        if (strBox.isEmpty()) {
                            System.out.println("Коробка пуста.\n");
                        } else {
                            System.out.println("Коробка не пуста.\n");
                        }
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Неверный выбор.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
