import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
 import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String input = scanner.nextLine();
        scanner.close();

        // мэп для сопостовления частоты и символа
        Map<Character, Long> frequencyMap = input.chars()// преобразуем в символы
                .mapToObj(c -> (char) c) //
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // групируем и считаем кол-во

        // Сортировка по частоте (по убыванию) и затем по алфавиту
        frequencyMap.entrySet().stream()
                .sorted(Map.Entry.<Character, Long>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .forEach(entry -> System.out.println("Символ: " + entry.getKey() + ", Частота: " + entry.getValue()));
    }
}
