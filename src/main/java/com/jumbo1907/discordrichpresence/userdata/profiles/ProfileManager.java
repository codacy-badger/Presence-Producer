package com.jumbo1907.discordrichpresence.userdata.profiles;

import com.jumbo1907.discordrichpresence.userdata.saves.SaveManager;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfileManager {

    private String name;
    private long dateCreated;
    private long lastUsed;
    private ArrayList<SaveManager> saves;

    public ProfileManager(String name, long dateCreated, long lastUsed, ArrayList<SaveManager> saves) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.lastUsed = lastUsed;
        this.saves = saves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getdateCreated() {
        return dateCreated;
    }

    public void setdateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public long getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(long lastUsed) {
        this.lastUsed = lastUsed;
    }

    public ArrayList<SaveManager> getSaves() {
        return saves;
    }

    public void setSaves(ArrayList<SaveManager> saves) {
        this.saves = saves;
    }

    public JSONObject toJsonObject(){
        JSONObject object = new JSONObject();
        object.put("name",name);
        object.put("dateCreated",dateCreated);
        object.put("lastUsed",lastUsed);
        return toJsonObject();
    }

    public void saveProfile(){

    }
}
