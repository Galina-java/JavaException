import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные (Фамилия Имя Отчество дата_рождения номер_телефона пол), разделенные пробелом:");
        String input = scanner.nextLine();
        String[] data = input.split(" ");

        try {
            // Проверка количества введенных данных
            if (data.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных. Ожидается 6 параметров.");
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String birthDate = validateBirthDate(data[3]);
            String phoneNumber = validatePhoneNumber(data[4]);
            char gender = validateGender(data[5]);

            // Создание строки для записи
            String output = String.join(" ", lastName + firstName + middleName + birthDate, phoneNumber, String.valueOf(gender));

            // Запись данных в файл
            writeToFile(lastName, output);

            System.out.println("Данные успешно записаны в файл.");

        } catch (IllegalArgumentException | IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }

    private static String validateBirthDate(String birthDate) {
        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new IllegalArgumentException("Неверный формат даты рождения. Ожидается формат dd.mm.yyyy.");
        }
        return birthDate;
    }

    private static String validatePhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Номер телефона должен содержать только цифры.");
        }
        return phoneNumber;
    }

    private static char validateGender(String gender) {
        if (gender.length() != 1 || (!gender.equalsIgnoreCase("m") && !gender.equalsIgnoreCase("f"))) {
            throw new IllegalArgumentException("Неверный формат пола. Ожидается символ 'm' или 'f'.");
        }
        return gender.toLowerCase().charAt(0);
    }

    private static void writeToFile(String lastName, String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(lastName + ".txt", true))) {
            writer.write(data);
            writer.newLine();
        }
    }
}