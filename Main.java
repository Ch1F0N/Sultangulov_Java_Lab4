import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Меню заданий:\n" +
                        "1. Обобщенная коробка\n" +
                        "2. Без null\n" +
                        "3. Поиск максимума\n" +
                        "4. Функция\n" +
                        "5. Фильтр\n" +
                        "6. Сокращение\n" +
                        "7. Коллекционирование\n" +
                        "0. Выход\n");

                System.out.print("Выберите задание из меню, которое хотите запустить: ");
                int task_menu = scanner.nextInt();
                System.out.println();

                if (task_menu == 0) {
                    break;
                }

                switch (task_menu) {
                    //
                    case 1:
                        BoxDemo.demo();
                        System.out.println();
                        break;
                    case 2:
                        StorageDemo.demo();
                        System.out.println();
                        break;
                    case 3:
                        BoxMaxDemo.demo();
                        System.out.println();
                        break;
                    case 4:
                        FunctionDemo.demo();
                        System.out.println();
                        break;
                    case 5:
                        FilterDemo.demo();
                        System.out.println();
                        break;
                    case 6:
                        ReductionDemo.demo();
                        System.out.println();
                        break;
                    case 7:
                        CollectDemo.demo();
                        System.out.println();
                        break;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Вы должны ввести только целое число!");
                System.out.println();
            }
        }
    }
}