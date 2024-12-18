package com.example.demo12;

import javafx.beans.property.SimpleStringProperty;

public class DataModelSQL {

    private SimpleStringProperty idV;
    private SimpleStringProperty idS;
    private SimpleStringProperty val;
    private SimpleStringProperty idUser;
    private SimpleStringProperty idVParent;
    private SimpleStringProperty dateAdd;
    private SimpleStringProperty visible;

    public DataModelSQL(String idV, String idS, String val, String idUser,
                        String idVParent, String dateAdd, String visible) {
        this.idV = new SimpleStringProperty(idV);
        this.idS = new SimpleStringProperty(idS);
        this.val = new SimpleStringProperty(val);
        this.idUser = new SimpleStringProperty(idUser);
        this.idVParent = new SimpleStringProperty(idVParent);
        this.dateAdd = new SimpleStringProperty(dateAdd);
        this.visible = new SimpleStringProperty(visible);
    }

    public String getIdV() {
        return idV.get();
    }

    public void setIdV(String value) {
        idV.set(value);
    }

    public String getIdS() { // Исправлено на getIdS
        return idS.get();
    }

    public void setIdS(String value) {
        idS.set(value);
    }

    public String getVal() {
        return val.get();
    }

    public void setVal(String value) {
        val.set(value);
    }

    public String getIdUser() {
        return idUser.get();
    }

    public void setIdUser(String value) {
        idUser.set(value);
    }

    public String getIdVParent() {
        return idVParent.get();
    }

    public void setIdVParent(String value) {
        idVParent.set(value);
    }

    public String getDateAdd() {
        return dateAdd.get();
    }

    public void setDateAdd(String value) {
        dateAdd.set(value);
    }

    public String getVisible() {
        return visible.get();
    }

    public void setVisible(String value) {
        visible.set(value);
    }
}