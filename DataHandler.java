package com.example.demo12;

import java.util.ArrayList;
import java.util.List;

public class DataHandler {

    // Пример данных
    private List<DataModelSQL> dataList;

    public DataHandler() {
        dataList = new ArrayList<>();
        // Заполнение данными для примера
        loadData();
    }

    private void loadData() {
        dataList.add(new DataModelSQL("1", "A", "Value 1", "User 1", "Parent 1", "2024-01-01", "Yes"));
        dataList.add(new DataModelSQL("2", "B", "Value 2", "User 2", "Parent 2", "2024-01-02", "No"));
        // Добавьте больше данных по мере необходимости
    }

    public List<DataModelSQL> getDataList() {
        return dataList;
    }
}
