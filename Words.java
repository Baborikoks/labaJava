import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Words {

    // Метод для получения уникальных слов из файла
    private static Set<String> getUniqueWords(String fileName) {
        Set<String> uniqueWords = new TreeSet<>(); // множество уникальных храниться

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) { // о строчно читает
                String[] words = line.split("[^\\p{L}]+"); // Разделитель - любые не буквенно-цифровые символы, делит на слова
                                                                  // \\p{L}+ не буква одна или больше
                 for (String word : words) { // перебирает каждое слово
                    if (!word.isEmpty()) { // не пустое
                        // Добавляем слова в множество (в нижнем регистре для уникальности)
                        uniqueWords.add(word.toLowerCase());
                    }
                }
            }
        } catch (IOException e) { // исключения
            e.printStackTrace();// вывод информации о стеке
        }

        return uniqueWords; // Возвращаем множество уникальных слов
    }

    public static void main(String[] args) {
        // Вызов метода для получения уникальных слов
        Set<String> uniqueWords = getUniqueWords("input.txt");

        // Выводим уникальные слова на консоль
        if (uniqueWords.isEmpty()) {
            System.out.println("Уникальные слова не найдены.");
        } else {
            System.out.println("Уникальные слова:");
            for (String word : uniqueWords) {
                System.out.println(word);
            }
        }
    }
}