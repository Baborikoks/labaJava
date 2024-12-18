import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Comparator; // Импорт Comparator

class Diplomant {
    private String name;  // имя дипломанта
    private int diploma;  // степень

    public Diplomant(String name, int diploma) { // конструктор класса
        this.name = name; // присваивает переданное имя
        this.diploma = diploma; // присваивает переданную степень
    }

    public String getName() { // метод доступа для получения имени
        return name;
    }

    public int getDiploma() { // метод доступа для получения степени
        return diploma;
    }

    public String toString() {  // формирование структуры
        return name + ", диплом " + diploma;
    }
}

public class Main {
    public static void main(String[] args) {
        // создание хэш-карты
         Map<String, List<Diplomant>> diplomantsMap = new HashMap<>();

        // Чтение данных из текстового файла
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) { // открытие файла для чтения
            String line; // хранение текущей строки
            while ((line = br.readLine()) != null) { // читает пока не конец
                String[] parts = line.split(", "); // разделение запятая + пробел
                if (parts.length == 3) { // проверка, что в строке есть все 3 элемента
                    String name = parts[0]; // сохраняет имя
                    int diploma = Integer.parseInt(parts[1]); // степень диплома
                    String competition = parts[2]; // название соревнования

                    Diplomant diplomant = new Diplomant(name, diploma); // новый объект класса
                    diplomantsMap.putIfAbsent(competition, new ArrayList<>()); // пустой список, если нет соревнований
                    diplomantsMap.get(competition).add(diplomant); // добавляю дипломанта в соответствующее соревнование
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // ошибка
        }

        // Запрос пользователя на ввод ключа (названия соревнования)
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название соревнования для поиска: ");
        String searchKey = scanner.nextLine();

        // Запись информации о дипломантах в другой текстовый файл и вывод на консоль
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) { // открываем файл для записи
            // Проверка, существует ли ключ в карте
            if (diplomantsMap.containsKey(searchKey)) {
                List<Diplomant> diplomants = diplomantsMap.get(searchKey); // список дипломантов для данного соревнования

                // Сортировка дипломантов по степени диплома (от 1 до 3)
                diplomants.sort(Comparator.comparingInt(Diplomant::getDiploma)); // сортирует по степени

                bw.write(searchKey + ":\n"); // записи в файл
                System.out.println(searchKey + ":"); // Вывод на консоль
                for (Diplomant diplomant : diplomants) {
                    String diplomantInfo = "  " + diplomant; // информация
                    bw.write(diplomantInfo + "\n");
                    System.out.println(diplomantInfo); // Вывод на консоль
                }
            } else {
                System.out.println("Соревнование \"" + searchKey + "\" не найдено.");
                bw.write("Соревнование \"" + searchKey + "\" не найдено.\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close(); // Закрываем сканер
        }
    }
}