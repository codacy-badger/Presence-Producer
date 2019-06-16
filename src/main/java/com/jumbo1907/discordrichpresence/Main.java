package com.jumbo1907.discordrichpresence;


import com.jumbo1907.discordrichpresence.filemanager.FileManager;
import com.jumbo1907.discordrichpresence.gui.MainApplication;
import com.jumbo1907.discordrichpresence.richpresence.DiscordRichPresence;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    public static MainApplication mainApplication;
    public static FileManager fileManager;

    public static void main(String[] args) {
        //This adds antialiasing for text in java
        System.setProperty("prism.lcdtext", "false");

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

