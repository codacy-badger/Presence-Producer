package com.jumbo1907.discordrichpresence;


import com.jumbo1907.discordrichpresence.config.ConfigManager;
import com.jumbo1907.discordrichpresence.filemanager.FileManager;
import com.jumbo1907.discordrichpresence.gui.MainApplication;
import com.jumbo1907.discordrichpresence.richpresence.DiscordRichPresence;
import com.jumbo1907.discordrichpresence.userdata.profiles.ProfileManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application {

    public static MainApplication mainApplication;
    public static FileManager fileManager;
    public static ConfigManager configManager;
    public static HashMap<String, ProfileManager> profileManagerHashMap;

    public static void main(String[] args) {
        //This adds antialiasing for text in java
        System.setProperty("prism.lcdtext", "false");
        profileManagerHashMap = new HashMap<>();

        configManager = new ConfigManager();

        //This will load the directory, duh!
        fileManager = new FileManager();
        fileManager.loadDirectory();

        DiscordRichPresence discordRichPresence = new DiscordRichPresence("589848399021342742");
        discordRichPresence.login();

        //This will launch the application
        mainApplication = new MainApplication();
        launch();

    }

    @Override
    public void start(Stage primaryStage) {
        mainApplication.start(primaryStage);
    }
}

