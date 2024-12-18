import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LnTest {

    @Test
    void test1() {
        Ln calculator = new Ln();
        double x = 0.1;
        int k = 1;
        double expected = Math.log(1 - x);
        double result = calculator.calculateLn(x, k);

        String format = "%." + k + "f";
        System.out.printf("Тест 1 - x: %.2f, k: %d -> Ожидаемое: " + format + ", Полученное: " + format + "\n", x, k, expected, result);
        double a = Math.abs(result - expected); // отклон от 10^(-5)
        double b = Math.pow(10, -2*k);
        assertEquals(expected, result, b, "Ошибка при x = 0.1 и k = 1");
    }

    @Test
    void test2() {
        Ln calculator = new Ln();
        double x = 0.5;
        int k = 5;
        double expected = Math.log(1 - x);
        double result = calculator.calculateLn(x, k);
        double a = Math.abs(result - expected);
        double b = Math.pow(10, -2*k);
        String format = "%." + k + "f";
        System.out.printf("Тест 2 - x: %.2f, k: %d -> Ожидаемое: " + format + ", Полученное: " + format + "\n", x, k, expected, result);
        assertEquals((Math.round( expected* b) / b), (Math.round(result* b) / b), b, "Ошибка при x = 0.5 и k = 4");
    }

    @Test
    void test3() {
        Ln calculator = new Ln();
        double x = -0.5;
        int k = 3;
        double expected = Math.log(1 - x);
        double result = calculator.calculateLn(x, k);

        String format = "%." + k + "f";
        System.out.printf("Тест 3 - x: %.2f, k: %d -> Ожидаемое: " + format + ", Полученное: " + format + "\n", x, k, expected, result);
        double a = Math.abs(result - expected);
        double b = Math.pow(10, -2*k);
        assertEquals((Math.round(expected* b) / b), (Math.round(result* b) / b), b, "Ошибка при x = -0.5 и k = 3");
    }

    @Test
    void test4() {
        Ln calculator = new Ln();
        double x = 0.99;
        int k = 5;
        double expected = Math.log(1 - x);
        double result = calculator.calculateLn(x, k);

        String format = "%." + k + "f";
        System.out.printf("Тест 4 - x: %.2f, k: %d -> Ожидаемое: " + format + ", Полученное: " + format + "\n", x, k, expected, result);
        double a = Math.abs(result - expected);
        double b = Math.pow(10, -2*k);
        assertEquals((Math.round(expected* b) / b), (Math.round(result* b) / b), b, "Ошибка при x = 0.99 и k = 5");
    }

    @Test
    void test5() {
        Ln calculator = new Ln();
        double x = 0.05;
        int k = 10;
        double expected = Math.log(1 - x);
        double result = calculator.calculateLn(x, k);

        String format = "%." + k + "f";
        System.out.printf("Тест 5 - x: %.2f, k: %d -> Ожидаемое: " + format + ", Полученное: " + format + "\n", x, k, expected, result);
        double a = Math.abs(result - expected);
        double b = Math.pow(10, -2*k);
        assertEquals((Math.round(expected* b) / b), (Math.round(result* b) / b), b, "Ошибка при x = 0.05 и k = 10");
    }
}
