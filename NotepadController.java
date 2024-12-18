package com.example.demo6;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class NotepadController {

    @FXML
    private TextField singleLineInput;

    @FXML
    private TextArea multiLineInput;

    @FXML
    public void initialize() {
        singleLineInput.setOnAction(event -> { // срабатывает когда нажали энтер
            String text = singleLineInput.getText();
            if (!text.isEmpty()) {
                multiLineInput.appendText(text + "\n");
                singleLineInput.clear(); // очистка однострочного окна
            }
        });
    }

    @FXML
    private void handleSave() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить файл");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog((Stage) singleLineInput.getScene().getWindow());

        if (file != null) {
            saveToFile(file);
        }
    }

    @FXML
    private void handleLoad() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть файл");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showOpenDialog((Stage) singleLineInput.getScene().getWindow());

        if (file != null) {
            loadFromFile(file);
        }
    }

    private void loadFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            multiLineInput.clear(); // Очищаем текущее содержимое
            String line;
            while ((line = reader.readLine()) != null) {
                multiLineInput.appendText(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();  // Обработка ошибок
        }
    }

    private void saveToFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(multiLineInput.getText());
            // Уведомление об успешном сохранении можно добавить здесь
        } catch (IOException e) {
            e.printStackTrace();  // Обработка ошибок
        }
    }
}