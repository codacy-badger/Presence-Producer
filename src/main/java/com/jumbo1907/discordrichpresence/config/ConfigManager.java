package com.jumbo1907.discordrichpresence.config;

import com.jumbo1907.discordrichpresence.FixedVariables;
import com.jumbo1907.discordrichpresence.enums.Event;
import com.jumbo1907.discordrichpresence.enums.TimeStamp;
import com.jumbo1907.discordrichpresence.userdata.profiles.ProfileManager;
import com.jumbo1907.discordrichpresence.userdata.saves.SaveManager;
import com.jumbo1907.discordrichpresence.utils.Logger;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class ConfigManager {
    private String sep = File.separator;


    public JSONObject getDefaultConfig() {
        JSONObject object = new JSONObject();
        object.put("version", FixedVariables.VERSION);
        object.put("lastUsed", System.currentTimeMillis());
        object.put("firstUsed", System.currentTimeMillis());
        object.put("theme", "dark");

        return object;
    }

    public JSONObject getDefaultProfile() {
        JSONObject object = new JSONObject();
        object.put("name", "default");
        object.put("lastUsed", System.currentTimeMillis());
        object.put("firstUsed", System.currentTimeMillis());

        return object;
    }

    //This will find and return all the profiles (and saves of course within the profile variable)
    public HashMap<String, ProfileManager> loadAllProfiles() {

        File file = new File(FixedVariables.USERDIRECTORY + sep + "profiles");
        String[] directories = file.list((current, name) -> new File(current, name).isDirectory());

        HashMap<String, ProfileManager> profiles = new HashMap<>();

        for (String profileName : directories) {
            Logger.INFO.out("Currently loading profile \"" + profileName+"\"");
            String content;
            try {
                content = new String(Files.readAllBytes(Paths.get(FixedVariables.USERDIRECTORY + sep + "profiles" + sep + profileName + sep + "profile.json")));
            } catch (IOException e) {
                e.printStackTrace();
                Logger.ERROR.out("Could not read profile.json from \"" + profileName + "\"");
                continue;
            }
            JSONObject jsonObject = new JSONObject(content);

            String name = jsonObject.getString("name");
            long dateCreated = jsonObject.getLong("firstUsed");
            long lastUsed = jsonObject.getLong("lastUsed");
            ArrayList<SaveManager> saves = getSaves(profileName);

            ProfileManager profileManager = new ProfileManager(name, dateCreated, lastUsed, saves);

            //Adds the correct ProfileManager variable to the Save
            for (SaveManager saveManager : profileManager.getSaves()) {
                saveManager.setProfile(profileManager);
            }

            profiles.put(profileName, profileManager);
            Logger.SUCCESS.out("Successfully read profile \"" + profileName + "\" (" + saves.size() + " saves found)");
        }

        return profiles;
    }

    private ArrayList<SaveManager> getSaves(String profileName) {
        ArrayList<SaveManager> saveManagers = new ArrayList<>();

        File file = new File(FixedVariables.USERDIRECTORY + sep + "profiles" + sep+ profileName + sep + "saves");

        if (!file.isDirectory()) file.mkdir();

        String[] directories = file.list((current, name) -> new File(current, name).isDirectory());

        for (String saveName : directories) {
            String content;
            try {
                content = new String(Files.readAllBytes(Paths.get(FixedVariables.USERDIRECTORY + sep + "profiles" + sep + profileName + sep + "saves" + sep + "save" + ".json")));
            } catch (IOException e) {
                e.printStackTrace();
                Logger.ERROR.out("Could not read save.json from \"" + profileName + "/" + saveName + "\"");
                continue;
            }
            JSONObject jsonObject = new JSONObject(content);
            String name = jsonObject.getString("name");
            String event = jsonObject.getString("event");
            long dateCreated = jsonObject.getLong("dateCreated");
            long lastUsed = jsonObject.getLong("lastUsed");
            String state = jsonObject.getString("state");
            String details = jsonObject.getString("details");
            String timeStampType = jsonObject.getString("timeStampType");
            String clientID = jsonObject.getString("clientID");
            String launchData = jsonObject.getString("launchData");

            saveManagers.add(new SaveManager(null, name, Event.valueOf(event.toUpperCase()), dateCreated, lastUsed, state, details, TimeStamp.valueOf(timeStampType.toUpperCase()), clientID, launchData));

        }
        return saveManagers;
    }
}
