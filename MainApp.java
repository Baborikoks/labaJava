package com.example.demo5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("greetingForm.fxml"));
        primaryStage.setTitle("Вычисление затрат на награждение");
        primaryStage.setScene(new Scene(root, 200, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}