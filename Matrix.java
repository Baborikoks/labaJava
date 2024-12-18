import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Matrix {
    // Метод для вывода столбцов с четными элементами
    private static void printColumnsWithAllEven(int[][] matrix, PrintWriter writer) {
        writer.println("Столбцы с четными элементами:"); // вывод
        System.out.print("Столбцы с четными элементами: ");
        for (int j = 0; j < matrix[0].length; j++) { // проходим по каждому столбцу
            boolean allEven = true; // Флаг для проверки, все ли элементы четные
            for (int i = 0; i < matrix.length; i++) { // проходим по строкам в текущем столбце
                if (matrix[i][j] % 2 != 0) { // Если встречается нечетный элемент
                    allEven = false; // если нечетный, то выход из программы
                    break;
                }
            }
            if (allEven) { // если все эл-ты четные, то выполняем вывод
                writer.print(j + " "); // Записываем номер столбца в файл
                System.out.print(j + " "); // Выводим номер столбца
            }
        }
        writer.println();
        System.out.println();
    }

    // Метод для вывода столбцов с отрицательным элементом на главной диагонали и их сумм
    private static void printColumnsWithNegativeDiagonal(int[][] matrix, PrintWriter writer) {
        writer.println("Столбцы с отрицательным элементом на главной диагонали:");
        for (int j = 0; j < Math.min(matrix.length, matrix[0].length); j++) { // проходим по элементам главной диагонали
            if (matrix[j][j] < 0) { // проверка на отрицательность
                int sum = 0;
                for (int i = 0; i < matrix.length; i++) { // проходи по эл-там текущего столбца
                    sum += matrix[i][j];
                }
                writer.println("Столбец: " + j + ", Сумма: " + sum); // вывод
                System.out.println("Столбец: " + j + ", Сумма: " + sum);
            }
        }
    }

    // Метод для вычисления определителя матрицы методом Гаусса
    public static double calculateDeterminant(int[][] matrix, int n) {
        double det = 1; // начальные значения
        int[][] tempMatrix = new int[n][n];

        // Копируем матрицу
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, tempMatrix[i], 0, n);
        }

        for (int i = 0; i < n; i++) {
            // Поиск максимального элемента в текущем столбце
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(tempMatrix[k][i]) > Math.abs(tempMatrix[maxRow][i])) {
                    maxRow = k;
                }
            }

            // Перестановка строк
            if (i != maxRow) {
                int[] temp = tempMatrix[i]; // меняем местами строки и знак определителя
                tempMatrix[i] = tempMatrix[maxRow];
                tempMatrix[maxRow] = temp;
                det = -det; // Изменение знака определителя
            }

            // Приведение к верхнетреугольному виду
            for (int k = i + 1; k < n; k++) { // обнуление под главноц диагонали
                double factor = (double) tempMatrix[k][i] / tempMatrix[i][i];
                for (int j = i; j < n; j++) {
                    tempMatrix[k][j] -= factor * tempMatrix[i][j];
                }
            }
            det *= tempMatrix[i][i];
        }

        return det;
    }

    public static void main(String[] args) {
        Scanner scanner = null;
        PrintWriter writer = null;

        try {
            // Инициализация считывателя и писателя
            scanner = new Scanner(new File("input.txt"));
            writer = new PrintWriter("output.txt");

            // Ввод размерности матрицы
            int rows = scanner.nextInt();
            int cols = scanner.nextInt();

            int[][] matrix = new int[rows][cols];

            // Ввод элементов матрицы
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }

            // Вывод номеров столбцов с четными элементами
            printColumnsWithAllEven(matrix, writer);

            // Вывод столбцов с отрицательным элементом на главной диагонали и их сумм
            printColumnsWithNegativeDiagonal(matrix, writer);

            // Если матрица квадратная, находим её определитель методом Гаусса
            if (rows == cols) {
                double determinant = calculateDeterminant(matrix, rows);
                writer.println("Определитель матрицы: " + determinant);
                System.out.println("Определитель матрицы: " + determinant);
            } else {
                writer.println("Матрица не квадратная, определитель не может быть найден.");
                System.out.println("Матрица не квадратная, определитель не может быть найден.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
}