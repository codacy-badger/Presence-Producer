package com.jumbo1907.discordrichpresence.userdata.saves;

import com.jumbo1907.discordrichpresence.enums.Event;
import com.jumbo1907.discordrichpresence.enums.TimeStamp;
import com.jumbo1907.discordrichpresence.userdata.profiles.ProfileManager;
import org.json.JSONObject;

public class SaveManager {

    private ProfileManager profile;
    private String name;
    private Event event;
    private long dateCreated;
    private long lastUsed;

    private String state;
    private String details;
    private TimeStamp timeStampType;

    private String clientID;

    //Website, application, (more)
    //null if startup
    private String launchData;


    public SaveManager(ProfileManager profile, String name, Event event, long dateCreated, long lastUsed, String state, String details, TimeStamp timeStampType, String clientID, String launchData) {
        this.profile = profile;
        this.name = name;
        this.event = event;
        this.dateCreated = dateCreated;
        this.lastUsed = lastUsed;
        this.state = state;
        this.details = details;
        this.timeStampType = timeStampType;
        this.clientID = clientID;
        this.launchData = launchData;
    }

    public JSONObject toJsonObject() {
        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("profile", profile.getName());
        object.put("lastUsed", lastUsed);
        object.put("event", event.toString());
        object.put("dateCreated", dateCreated);
        object.put("state", state);
        object.put("details", details);
        object.put("clientID", clientID);
        object.put("launchData", launchData);
        object.put("timeStampType", timeStampType.toString());
        return toJsonObject();
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getlaunchData() {
        return launchData;
    }

    public void setlaunchData(String launchData) {
        this.launchData = launchData;
    }

    public ProfileManager getProfile() {
        return profile;
    }

    public void setProfile(ProfileManager profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public TimeStamp getTimeStampType() {
        return timeStampType;
    }

    public void setTimeStampType(TimeStamp timeStampType) {
        this.timeStampType = timeStampType;
    }
}
