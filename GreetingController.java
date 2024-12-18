package com.example.demo5;

import javafx.fxml.FXML;
// пакеты для элементов управления
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
// компоновка эл-тов управления
import javafx.scene.layout.VBox;
// базовые
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GreetingController {
    @FXML // сообщает, что будет связан с эл-тами из интерфейса
    // поздравители
    private RadioButton greeter1;
    @FXML
    private RadioButton greeter2;

    @FXML
    private VBox giftOptions; // Контейнер для подарков, собержит чекбоксы
    @FXML
    private Label giftLabel; // Лейбл для подарков
    private CheckBox gift1;
    private CheckBox gift2;
    private CheckBox gift3;
    // концерт
    @FXML
    private RadioButton concertYes;
    @FXML
    private RadioButton concertNo;
    //постоянный клиент
    @FXML
    private RadioButton regularClientYes;
    @FXML
    private RadioButton regularClientNo;

    @FXML
    private Label resultLabel; // результат
    @FXML
    private Button backButton; // Кнопка "Назад"
    // коллекция, кот. хранит пары имя подарка и цену
    private Map<String, Double> prices = new HashMap<>();

    @FXML
    public void initialize() {
        loadPricesFromFile("input.txt"); // чтение из файла
        giftLabel.setVisible(false);
        giftOptions.setVisible(false);
    }
    // метод чтения из файла
    private void loadPricesFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String itemName = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    prices.put(itemName, price);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onGreeterSelected() { // обработка выбора поздравителей
        giftOptions.getChildren().clear(); // чтобы вывести новые чекбоксы
        giftLabel.setVisible(false);

        if (greeter1.isSelected()) {
            gift1 = new CheckBox("Блокнот");
            gift2 = new CheckBox("Ручка");
            gift3 = new CheckBox("Бутылка для воды");
        } else if (greeter2.isSelected()) {
            gift1 = new CheckBox("Носки");
            gift2 = new CheckBox("Обложка на зачетку");
            gift3 = new CheckBox("Блокнот в клетку");
        }
// Добавляем лейбл в контейнер
        if (!giftOptions.getChildren().contains(giftLabel)) {
            giftOptions.getChildren().add(giftLabel);
        }

        giftOptions.getChildren().addAll(gift1, gift2, gift3); // добавляем чек бокс
        giftLabel.setVisible(true); // Показываем лейбл для подарков
        giftOptions.setVisible(true); // Показываем контейнер с подарками
    }

    @FXML
    protected void onCalculateButtonClick() {
        double cost = calculateCost(); // расчет стоимости
        resultLabel.setText("Итоговая стоимость: " + cost + " руб.");
        resultLabel.setVisible(true);
        backButton.setVisible(true);
    }

    private double calculateCost() {
        double baseCost = 0.0; // Базовая стоимость

        // Проверка выбранного поздравителя
        if (greeter1.isSelected()) {
            baseCost += prices.get("Яндекс");
        } else if (greeter2.isSelected()) {
            baseCost += prices.get("Инновайз");
        }

        // Добавление стоимости подарков
        if (gift1 != null && gift1.isSelected()) baseCost += prices.get(gift1.getText());
        if (gift2 != null && gift2.isSelected()) baseCost += prices.get(gift2.getText());
        if (gift3 != null && gift3.isSelected()) baseCost += prices.get(gift3.getText());

        // Проверка необходимости концерта
        if (concertYes.isSelected()) {
            baseCost += prices.get("Концерт");
        }

        // Применение скидки для постоянных клиентов
        if (regularClientYes.isSelected()) {
            baseCost *= 0.9; // 10% скидка
        }

        return baseCost;
    }

    @FXML
    protected void onBackButtonClick() {
        // Скрываем результат и кнопку "Назад"
        resultLabel.setVisible(false);
        backButton.setVisible(false);
        giftOptions.setVisible(false);

        // Сбрасываем выбранных  поздравителей
        greeter1.setSelected(false);
        greeter2.setSelected(false);

        // Сбрасываем чекбоксы подарков, если они были созданы
        if (gift1 != null) gift1.setSelected(false);
        if (gift2 != null) gift2.setSelected(false);
        if (gift3 != null) gift3.setSelected(false);

        // Сбрасываем выбор концерта и постоянного клиента
        concertYes.setSelected(false);
        concertNo.setSelected(false);
        regularClientYes.setSelected(false);
        regularClientNo.setSelected(false);
    }
}