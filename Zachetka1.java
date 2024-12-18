import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;

// Класс для представления зачетки студента
class Zachetka {
    private String surname; // Фамилия студента
    private String name; // Имя студента
    private String patronymic; // Отчество студента
    private String studentID; // Номер зачетки
    private List<Session> sessions; // Список сессий студента

    // Конструктор для инициализации объекта Zachetka
    public Zachetka(String surname, String name, String patronymic, String studentID) {
        this.surname = surname; // Устанавливаем фамилию
        this.name = name; // Устанавливаем имя
        this.patronymic = patronymic; // Устанавливаем отчество
        this.studentID = studentID; // Устанавливаем номер зачетки
        this.sessions = new ArrayList<>(); // Инициализируем список сессий
    }

    // Метод для добавления сессии
    public void addSession(Session session) {
        sessions.add(session); // Добавляем сессию в список
    }

    // Метод для добавления сессии
    public void addSession(int sessionNumber) {
        sessions.add(new Session(sessionNumber)); // Создаем новую сессию и добавляем в список
    }

    // Метод для получения номера зачетки
    public String getStudentID() {
        return studentID; // Возвращаем номер зачетки
    }

    // Метод для получения ФИО студента
    public String getFullName() {
        return surname + " " + name + " " + patronymic; // Формируем и возвращаем ФИО
    }

    // Метод для получения списка сессий
    public List<Session> getSessions() {
        return sessions; // Возвращаем список сессий
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(); // Создаем StringBuilder для формирования строки
        sb.append(studentID).append(", ").append(getFullName()).append("\n"); // Добавляем номер зачетки и ФИО

        // Сортируем сессии по номеру
        Collections.sort(sessions, Comparator.comparingInt(Session::getSessionNumber));

        for (Session session : sessions) {
            sb.append(session).append("\n"); // Добавляем информацию о каждой сессии
        }
        return sb.toString().trim(); // Убираем лишний перенос строки в конце
    }
    // Метод для получения JSON представления зачетки
    public static class Session {
        private int sessionNumber; // Номер сессии
        private List<Exam> exams; // Список экзаменов

        public Session(int sessionNumber) {
            this.sessionNumber = sessionNumber; // Устанавливаем номер сессии
            this.exams = new ArrayList<>(); // Инициализируем список экзаменов
        }

        public int getSessionNumber() {
            return sessionNumber;
        }

        public void addExam(Exam exam) {
            exams.add(exam); // Добавляем экзамен в сессию
        }

        public static class Exam {
            private String subject; // Название предмета экзамена
            private int grade; // Оценка экзамена

            public Exam(String subject, int grade) {
                this.subject = subject; // Устанавливаем предмет экзамена
                this.grade = grade; // Устанавливаем оценку экзамена
            }
        }
    }
}

public class Zachetka1 {
    public static void main(String[] args) {
        Map<String, Zachetka> zachetkiMap = new HashMap<>(); // Создаем карту для хранения зачеток студентов
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Создаем объект Gson для работы с JSON

        // Чтение первого файла с номерами зачеток и ФИО
        try (BufferedReader br = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = br.readLine()) != null) { // Читаем файл построчно
                String[] parts = line.split("[,\\s]+"); // Разделяем строку по запятым и пробелам
                String studentID = parts[0].trim(); // Извлекаем номер зачетки
                String surname = parts[1].trim(); // Извлекаем фамилию
                String name = parts[2].trim(); // Извлекаем имя
                String patronymic = parts[3].trim(); // Извлекаем отчество

                // Создаем объект Zachetka и добавляем в карту
                Zachetka zachetka = new Zachetka(surname, name, patronymic, studentID);
                zachetkiMap.put(studentID, zachetka); // Сохраняем зачетку в карту
            }
        } catch (IOException e) { // Обработка исключений при чтении файла
            e.printStackTrace(); // Выводим стектрейс ошибки
        }

        // Обработка файлов экзаменов
        File folder = new File("."); // Путь к папке с файлами
        for (File file : folder.listFiles()) {
            if (file.isFile() && file.getName().startsWith("Exam")) { // Проверяем, что файл начинается с "Exam"
                try (BufferedReader br = new BufferedReader(new FileReader(file))) { // Читаем файл
                    String subjectAndSession = br.readLine(); // Читаем первую строку
                    String[] parts = subjectAndSession.split(",\\s*");
                    String subject = parts[0].trim(); // Название предмета
                    int sessionNumber = Integer.parseInt(parts[1].trim()); // Номер сессии

                    // Читаем оценки студентов
                    String line;
                    while ((line = br.readLine()) != null) { // Читаем файл построчно
                        String[] examParts = line.split(",\\s*"); // Разделяем строку
                        String studentID = examParts[0].trim(); // Извлекаем номер зачетки
                        int grade = Integer.parseInt(examParts[1].trim()); // Извлекаем оценку

                        // Находим зачетку по номеру зачетки и добавляем сессию и экзамен
                        Zachetka zachetka = zachetkiMap.get(studentID); // Получаем зачетку из карты
                        if (zachetka != null) {
                            // Проверяем, существует ли сессия, если нет - добавляем
                            boolean sessionExists = false;
                            for (Zachetka.Session session : zachetka.getSessions()) {
                                if (session.getSessionNumber() == sessionNumber) {
                                    session.addExam(new Zachetka.Session.Exam(subject, grade)); // Добавляем экзамен в существующую сессию
                                    sessionExists = true;
                                    break;
                                }
                            }
                            if (!sessionExists) {
                                Zachetka.Session newSession = new Zachetka.Session(sessionNumber);
                                newSession.addExam(new Zachetka.Session.Exam(subject, grade)); // Добавляем экзамен к новой сессии
                                zachetka.addSession(newSession); // Добавляем новую сессию
                            }
                        }
                    }
                } catch (IOException e) { // Обработка исключений при чтении файла
                    e.printStackTrace(); // Выводим стектрейс ошибки
                }
            }
        }

        // Запись данных в выходной JSON файл для каждого студента
        for (Zachetka zachetka : zachetkiMap.values()) { // Проходимся по всем зачеткам
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(zachetka.getStudentID() + "_output.json"))) { // Открываем файл для записи
                String json = gson.toJson(zachetka); // Сериализуем объект Zachetka в JSON
                bw.write(json); // Записываем JSON в файл
            } catch (IOException e) { // Обработка исключений при записи в файл
                e.printStackTrace(); // Выводим стектрейс ошибки
            }
        }
    }
}