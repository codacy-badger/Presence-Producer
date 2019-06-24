package com.jumbo1907.discordrichpresence.filemanager;

import com.jumbo1907.discordrichpresence.FixedVariables;
import com.jumbo1907.discordrichpresence.Main;
import com.jumbo1907.discordrichpresence.utils.Logger;

import java.io.*;

public class FileManager {

    public LogWriter logWriter;
    private File directory;

    public FileManager() {
        directory = new File(FixedVariables.USERDIRECTORY);


    }

    public void loadDirectory(){
        if(!directory.exists()){
            Logger.INFO.out("User has not used Presence Producer before. New directory will be created.");
            directory.mkdir();
            try {
                createData();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else Logger.INFO.out("User has used Presence Producer before.");

        logWriter = new LogWriter(FixedVariables.USERDIRECTORY + File.separator + "logs");
        logWriter.createLog();

        //Load userdata
        Main.configManager.loadAllProfiles();
    }

    //This will create all the data needed for the program to run. This method
    //will only be used if the directory is non-existing.
    private void createData() throws IOException {
        Logger.INFO.out("Program will start to create default directories and files.");

        new File(FixedVariables.USERDIRECTORY + File.separator + "logs").mkdir();
        Logger.SUCCESS.out("Successfully created \"logs\" sub-directory.");

        new File(FixedVariables.USERDIRECTORY + File.separator + "profiles").mkdir();
        Logger.SUCCESS.out("Successfully created \"profiles\" sub-directory.");

        //Default profile folder
        new File(FixedVariables.USERDIRECTORY + File.separator + "profiles" + File.separator +"default").mkdir();
        Logger.SUCCESS.out("Successfully created \"profiles" + File.separator + "default\" sub-directory.");

        //Default profile saves folder
        new File(FixedVariables.USERDIRECTORY + File.separator + "profiles" + File.separator +"default" + File.separator + "saves").mkdir();
        Logger.SUCCESS.out("Successfully created \"profiles" + File.separator + "default" + File.separator + "saves\" sub-directory.");

        //Create config file
        PrintWriter configWriter = new PrintWriter(FixedVariables.USERDIRECTORY+File.separator+"config.json", "UTF-8");
        configWriter.println(Main.configManager.getDefaultConfig().toString());
        configWriter.close();

        //Create first profile.json file
        PrintWriter profileWriter = new PrintWriter(FixedVariables.USERDIRECTORY+File.separator+ "profiles" + File.separator  + "default" + File.separator + "profile.json", "UTF-8");
        profileWriter.println(Main.configManager.getDefaultProfile().toString());
        profileWriter.close();
    }
}
