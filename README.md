# Лабораторная работа №4 ЯП Java
**Султангулов Данил**

**Группа ИТ-9-2024 (2 курс)**

**Вариант №10**

---

# Тема: Обобщённые типы

Сперва я создал в своей лабораторной работе меню со всеми задания от 1 до 7, а также разделил их все на категории, чтобы пользователю было удобно выбирать, какую именно из программ он хочет использовать:
```java
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
```

Если пользователь введёт номер, которого нет в меню, или введёт иные символы, то в терминале вылезет предупреждение о неверном вводе и меню запуститься заново, чтобы выйти из программы, пользователю достаточно ввести в терминал '0'. Для запуска конкретной программы, я решил использовать конструкцию swith-case.

---

## Задание 1 (№1 Обобщённая коробка):
> **Задача №1 заключается в том, чтобы создать сущность Коробка, которая может хранить произвольный объект, объект можно будет получить и разместить на хранение**

Для выполнения этого задания я создал класс-сущность **Box**:
```java
public class Box<T> {
    private T item;

    public Box() {
        this.item = null;
    }

    public boolean isEmpty() {
        return item == null;
    }

    public void put(T newItem) throws Exception {
        if (!isEmpty()) {
            throw new Exception("Ошибка: коробка уже заполнена!");
        }
        this.item = newItem;
    }

    public T get() {
        T temp = this.item;
        this.item = null;
        return temp;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Коробка пуста.";
        } else {
            return "В коробке находится: " + item.toString();
        }
    }
}
```
---
**Реализовал пользовательский ввод в классе BoxDemo**
```java
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
                    System.out.println();
                    break;
                case "2":
                    runStringBox(scanner);
                    System.out.println();
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
```
---
**Тесты**

№1 Кладём целое число в коробку
```
Выберите тип коробки:
1. Целое число
2. Строка
0. Выход
Ваш выбор: 1

Создана коробка для хранения целых чисел.
1. Положить число
2. Извлечь число
3. Проверить, пуста ли коробка
0. Назад
Ваш выбор: 1
Введите целое число: 5
Число 5 положено в коробку.

1. Положить число
2. Извлечь число
3. Проверить, пуста ли коробка
0. Назад
Ваш выбор: 3
Коробка не пуста.

1. Положить число
2. Извлечь число
3. Проверить, пуста ли коробка
0. Назад
Ваш выбор: 1
Коробка уже заполнена.

1. Положить число
2. Извлечь число
3. Проверить, пуста ли коробка
0. Назад
Ваш выбор: 2
Извлечено значение: 5
```
№2 Кладём строку в коробку
```
Выберите тип коробки:
1. Целое число
2. Строка
0. Выход
Ваш выбор: 2

Создана коробка для хранения строк.
1. Положить строку
2. Извлечь строку
3. Проверить, пуста ли коробка
0. Назад
Ваш выбор: 1
Введите строку: Привет
Строка "Привет" положена в коробку.

1. Положить строку
2. Извлечь строку
3. Проверить, пуста ли коробка
0. Назад
Ваш выбор: 3
Коробка не пуста.

1. Положить строку
2. Извлечь строку
3. Проверить, пуста ли коробка
0. Назад
Ваш выбор: 1
Коробка уже заполнена!

1. Положить строку
2. Извлечь строку
3. Проверить, пуста ли коробка
0. Назад
Ваш выбор: 2
Извлечено значение: Привет
```
№3 Некорекктный ввод
```
Выберите тип коробки:
1. Целое число
2. Строка
0. Выход
Ваш выбор: Ааа
Неверный выбор. Попробуйте снова.

Выберите тип коробки:
1. Целое число
2. Строка
0. Выход
Ваш выбор: 1

Создана коробка для хранения целых чисел.
1. Положить число
2. Извлечь число
3. Проверить, пуста ли коробка
0. Назад
Ваш выбор: 1
Введите целое число: Привет
Ошибка: For input string: "Привет"
```
---
## Задание 1 (№2 Без null):
> **Задача №2 заключается в том, чтобы создать сущность Хранилище, которая может хранить произвольный объект, объект можно будет получить и разместить на хранение, если пользователь не ввёл значение, вместо него будет выведена альтернатива.**

Для выполнения этого задания я создал класс-сущность **Storage**:
```java
public class Storage<T> {
    private final T value;

    public Storage(T value) {
        this.value = value;
    }

    public T getOrElse(T alternative) {
        if (value != null) {
            return value;
        } else {
            return alternative;
        }
    }

    @Override
    public String toString() {
        if (value == null) {
            return "Хранилище содержит: " + "null";
        } else {
            return "Хранилище содержит: " + value.toString();
        }

    }
}
```
---
**Реализовал пользовательский ввод в классе StorageDemo**
```java
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
```
---
**Тесты**

№1 Пустые значения
```
Введите число (или оставьте пустым для null): 
Результат: 0

Введите число для второго хранилища (99 для проверки): 
Результат: -1

Введите строку (или оставьте пустым для null): 
Результат: default

Введите строку для последнего хранилища (например, hello): 
Результат: hello, world
```
№2 Если вместо числа ввести строку
```java
Введите число (или оставьте пустым для null): А
Ошибка: введено некорректное число!
```
№3 Со значениями
```java
Введите число (или оставьте пустым для null): 5
Результат: 5

Введите число для второго хранилища (99 для проверки): 66
Результат: 66

Введите строку (или оставьте пустым для null): null
Результат: null

Введите строку для последнего хранилища (например, hello): hello
Результат: hello
```
---

# Защита от ошибок
Для защиты от неверного ввода используется конструкция **try-catch**.
