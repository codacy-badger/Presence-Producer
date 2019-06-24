package com.jumbo1907.discordrichpresence.gui.nodes.profiles;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProfilesModel {

    private SimpleStringProperty name;
    private SimpleStringProperty dateCreated;
    private SimpleStringProperty lastUsed;
    private SimpleIntegerProperty amountUsed;

    public ProfilesModel(String name, String dateCreated, String lastUsed, int amountUsed) {
        this.name =  new SimpleStringProperty(name);
        this.dateCreated =  new SimpleStringProperty(dateCreated);
        this.lastUsed =  new SimpleStringProperty(lastUsed);
        this.amountUsed =  new SimpleIntegerProperty(amountUsed);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name.set(name);
    }

    public String getDateCreated() {
        return dateCreated.get();
    }

    public SimpleStringProperty dateCreatedProperty() {
        return dateCreated;
    }

    public void setDateCreated(String datecreated) {
        this.dateCreated.set(datecreated);
    }

    public String getLastUsed() {
        return lastUsed.get();
    }

    public SimpleStringProperty lastUsedProperty() {
        return lastUsed;
    }

    public void setLastUsed(String lastUsed) {
        this.lastUsed.set(lastUsed);
    }

    public int getAmountUsed() {
        return amountUsed.get();
    }

    public SimpleIntegerProperty amountUsedProperty() {
        return amountUsed;
    }

    public void setAmountUsed(int amountUsed) {
        this.amountUsed.set(amountUsed);
    }
}
