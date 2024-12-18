import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод значений x и k
        System.out.print("Введите значение x (|x| < 1): ");
        double x = scanner.nextDouble();
        System.out.print("Введите значение k (натуральное число): ");
        int k = scanner.nextInt();

        Ln calculator = new Ln();  // Создаем объект класса Ln

        try {
            // Вычисление значения ln(1 - x) с использованием ряда Тейлора
            double approxValue = calculator.calculateLn(x, k);
            double actualValue = Math.log(1 - x);

            String format = "%." + k + "f";

            // Вывод результатов с динамическим количеством знаков после запятой
            System.out.printf("Приближённое значение суммы ряда Тейлора: " + format + "\n", approxValue);
            System.out.printf("Точное значение функции ln(1 - x) (из библиотеки): " + format + "\n", actualValue);
            System.out.printf("Разница между приближённым и библиотечным значением: " + format + "\n", Math.abs(approxValue - actualValue));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}

class Ln {
    // Метод для вычисления ln(1 - x) с заданной точностью 10^(-k)
    public double calculateLn(double x, int k) {
        // Проверка на условие |x| < 1
        if (Math.abs(x) >= 1) {
            throw new IllegalArgumentException("x должен быть меньше 1 по модулю.");
        }

        // Определение точности esp = 10^(-k)
        double esp = Math.pow(10, -2*k);

        // Вычисление суммы ряда Тейлора
        double sum = 0;
        double term = x; // Первое слагаемое: x^1 / 1 = x
        int n = 1;

        // Суммируем члены ряда до тех пор, пока они не станут меньше заданной точности
        while (Math.abs(term) >= esp) {
            sum -= term; // Учёт текущего члена ряда
            n++;
            term = Math.pow(x, n) / n; // Переход к следующему члену ряда
        }

        return sum;
    }
}
