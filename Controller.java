package com.example.demo12;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {

    @FXML
    private Button buttonClick;

    @FXML
    private TableColumn<DataModelSQL, String> columnOne;
    @FXML
    private TableColumn<DataModelSQL, String> columnTwo;
    @FXML
    private TableColumn<DataModelSQL, String> columnThree;
    @FXML
    private TableColumn<DataModelSQL, String> columnFour;
    @FXML
    private TableColumn<DataModelSQL, String> columnFive;
    @FXML
    private TableColumn<DataModelSQL, String> columnSix;
    @FXML
    private TableColumn<DataModelSQL, String> columnSeven;

    @FXML
    private TableView<DataModelSQL> tableShow;

    private DataHandler dataHandler;

    @FXML
    void initialize() {
        dataHandler = new DataHandler();

        columnOne.setCellValueFactory(new PropertyValueFactory<>("idV"));
        columnTwo.setCellValueFactory(new PropertyValueFactory<>("idS"));
        columnThree.setCellValueFactory(new PropertyValueFactory<>("val"));
        columnFour.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        columnFive.setCellValueFactory(new PropertyValueFactory<>("idVParent"));
        columnSix.setCellValueFactory(new PropertyValueFactory<>("dateAdd"));
        columnSeven.setCellValueFactory(new PropertyValueFactory<>("visible"));

        buttonClick.setOnAction(event -> loadData());
    }

    private void loadData() {
        ObservableList<DataModelSQL> list = FXCollections.observableArrayList(dataHandler.getDataList());
        tableShow.setItems(list);
    }
}