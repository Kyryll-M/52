import java.io.*;
import java.util.Scanner;

public class BasicTextEditor {
    static final String FILE_NAME = "D:\\data\\text.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Записати у файл");
            System.out.println("2. Переглянути у файлі");
            System.out.println("3. Вихід.....");
            System.out.print("Ваш вибір: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Помилка! Введіть число.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                writeToFile(scanner);
            } else if (choice == 2) {
                readFromFile();
            } else if (choice == 3) {
                System.out.println("Вихід...");
                break;
            } else {
                System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    static void writeToFile(Scanner scanner) {
        System.out.print("Введіть текст: ");
        String text = scanner.nextLine().trim();

        if (text.isEmpty()) {
            System.out.println("Порожній рядок не можна записати!");
            return;
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(FILE_NAME, true);
            writer.write(text + "\n");
        } catch (IOException e) {
            System.out.println("Помилка запису!");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Помилка закриття файлу!");
                }
            }
        }
    }

    static void readFromFile() {
        FileReader reader = null;
        try {
            reader = new FileReader(FILE_NAME);
            char[] buffer = new char[1024];
            int bytesRead;
            boolean isEmpty = true;

            while ((bytesRead = reader.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, bytesRead));
                isEmpty = false;
            }

            if (isEmpty) {
                System.out.println("Файл порожній.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено!");
        } catch (IOException e) {
            System.out.println("Помилка читання!");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Помилка закриття файлу!");
                }
            }
        }
    }
}