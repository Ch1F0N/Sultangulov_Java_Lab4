import java.util.InputMismatchException;
import java.util.Scanner;

public class StorageDemo {

    // Метод демонстрации с вводом и проверками
    public static void demo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nБез null");

        try {
            System.out.print("\nВведите число (или оставьте пустым для null): ");
            String input1 = scanner.nextLine().trim();
            if (input1.isEmpty()) {
                Integer num1 = null;
                Storage<Integer> s1 = new Storage<>(num1);
                System.out.println("Результат: " + s1.getOrElse(0));
            } else {
                Integer num1 = Integer.parseInt(input1);
                Storage<Integer> s1 = new Storage<>(num1);
                System.out.println("Результат: " + s1.getOrElse(0));
            }

            System.out.print("\nВведите число для второго хранилища (99 для проверки): ");
            String input2 = scanner.nextLine().trim();
            Integer num2 = input2.isEmpty() ? null : Integer.parseInt(input2);
            Storage<Integer> s2 = new Storage<>(num2);
            System.out.println("Результат: " + s2.getOrElse(-1));

            System.out.print("\nВведите строку (или оставьте пустым для null): ");
            String input3 = scanner.nextLine().trim();
            String str1 = input3.isEmpty() ? null : input3;
            Storage<String> s3 = new Storage<>(str1);
            System.out.println("Результат: " + s3.getOrElse("default"));

            System.out.print("\nВведите строку для последнего хранилища (например, hello): ");
            String input4 = scanner.nextLine().trim();
            String str2 = input4.isEmpty() ? null : input4;
            Storage<String> s4 = new Storage<>(str2);
            System.out.println("Результат: " + s4.getOrElse("hello, world"));

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введено некорректное число!");
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода!");
        }
    }
}
