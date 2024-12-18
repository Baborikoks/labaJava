import java.io.*; // для всех классов
import java.util.ArrayList;
import java.util.List;

public class TextProcessor {
    private List<StringBuffer> lines; // будет хранить строки из файла

    public TextProcessor() { // для нового объекта
        lines = new ArrayList<>();
    }

    // чтение из файла
    public void readFromFile(String inputFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) { // создает BufferedReader для чтения файла
            String line; // хранение каждой строки
            while ((line = reader.readLine()) != null) { // считывает пока не конец
                if (line.isEmpty()) break; // Конец ввода текста
                lines.add(new StringBuffer(line)); // добавляем в список
            }
        }
    }

    // исправления ошибок
    public void correctText() {
        for (int i = 0; i < lines.size(); i++) {
            StringBuffer currentLine = lines.get(i); // доступ к строкам
            for (int j = 0; j < currentLine.length() - 1; j++) { // проходим по каждому символу (длину строки)
                if (currentLine.charAt(j) == 'Р' && currentLine.charAt(j + 1) == 'А') { // символ сравнивается с Р
                    currentLine.setCharAt(j + 1, 'О'); // заменяем "А" на "О"
                }
            }
        }
    }

    // запись в файл
    public void writeToFile(String outputFilePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (StringBuffer line : lines) {
                writer.write(line.toString()); // записывает тек строку
                writer.newLine(); // добавляет новую строку после записи
            }
        }
    }

    public static void main(String[] args) {
        TextProcessor processor = new TextProcessor();
        String inputFilePath = "input.txt";  // Путь к входному файлу
        String outputFilePath = "output.txt"; // Путь к выходному файлу

        try { // методы из класса
            processor.readFromFile(inputFilePath);
            processor.correctText();
            processor.writeToFile(outputFilePath);
            System.out.println("Исправления внесены и сохранены в " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Произошла ошибка при обработке файла: " + e.getMessage());
        }
    }
}