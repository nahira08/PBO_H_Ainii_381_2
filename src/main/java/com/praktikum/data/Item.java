package com.praktikum.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item {

    private String itemName;
    private String description;
    private String location;
    private String pelapor;
    private boolean claimed;

    // Constructor lengkap (5 parameter)
    public Item(String itemName, String description, String location, String pelapor, boolean claimed) {
        this.itemName = itemName;
        this.description = description;
        this.location = location;
        this.pelapor = pelapor;
        this.claimed = claimed;
    }

    // Getter & Setter
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPelapor() {
        return pelapor;
    }

    public void setPelapor(String pelapor) {
        this.pelapor = pelapor;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public void setClaimed(boolean claimed) {
        this.claimed = claimed;
    }

    // Untuk keperluan TableView binding
    public StringProperty itemNameProperty() {
        return new SimpleStringProperty(itemName);
    }

    public StringProperty locationProperty() {
        return new SimpleStringProperty(location);
    }

    public StringProperty pelaporProperty() {
        return new SimpleStringProperty(pelapor);
    }

    public StringProperty descriptionProperty() {
        return new SimpleStringProperty(description);
    }
}
