package com.example.demo13;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private TableView<Employee> tableView;
    @FXML
    private TableColumn<Employee, Integer> numberColumn;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> positionColumn;
    @FXML
    private TextField nameField;
    @FXML
    private TextField positionField;
    @FXML
    private Button addButton;
    @FXML
    private Button loadButton; // Кнопка для загрузки
    @FXML
    private Button saveButton; // Кнопка для сохранения

    private List<Employee> employees = new ArrayList<>();

    @FXML
    public void initialize() {
        numberColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getNumber()).asObject());
        nameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFullName()));
        positionColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPosition()));

        addButton.setOnAction(event -> addEmployee());
        loadButton.setOnAction(event -> loadEmployees()); // Загрузка
        saveButton.setOnAction(event -> saveEmployees()); // Сохранение
    }

    private void addEmployee() {
        String fullName = nameField.getText().trim();
        String position = positionField.getText().trim();

        if (fullName.isEmpty() || position.isEmpty()) {
            System.out.println("Name and position must not be empty!");
            return;
        }

        int number = employees.size() + 1; // Можно улучшить для уникальности
        Employee emp = new Employee(number, fullName, position);
        employees.add(emp);
        tableView.getItems().add(emp);

        nameField.clear();
        positionField.clear();
    }

    private void loadEmployees() {
        employees.clear(); // Очистка существующих сотрудников
        tableView.getItems().clear(); // Очистка TableView

        try (BufferedReader br = new BufferedReader(new FileReader("employees.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) continue; // Пропустить некорректные строки
                int number = Integer.parseInt(parts[0]);
                String fullName = parts[1];
                String position = parts[2];
                Employee emp = new Employee(number, fullName, position);
                employees.add(emp);
                tableView.getItems().add(emp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveEmployees() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("employees.csv"))) {
            for (Employee emp : employees) {
                bw.write(emp.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}