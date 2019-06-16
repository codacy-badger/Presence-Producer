package com.jumbo1907.discordrichpresence.filemanager;

import com.jumbo1907.discordrichpresence.FixedVariables;
import com.jumbo1907.discordrichpresence.utils.Logger;

import java.io.File;

public class FileManager {

    public LogWriter logWriter;
    private File directory;

    public FileManager() {
        directory = new File(FixedVariables.USERDIRECTORY);

        logWriter = new LogWriter(FixedVariables.USERDIRECTORY + File.separator + "logs");
        logWriter.createLog();
    }

    public void loadDirectory(){
        if(!directory.exists()){
            Logger.INFO.out("User has not used Presence Producer before. New directory will be created.");
            directory.mkdir();
            createData();
        } else Logger.INFO.out("User has used Presence Producer before.");
    }

    //This will create all the data needed for the program to run. This method
    //will only be used if the directory is non-existing.
    private void createData(){
        Logger.INFO.out("Program will start to create default directories and files.");

        new File(FixedVariables.USERDIRECTORY + File.separator + "logs").mkdir();
        Logger.SUCCESS.out("Successfully created \"logs\" sub-directory.");

        new File(FixedVariables.USERDIRECTORY + File.separator + "profiles").mkdir();
        Logger.SUCCESS.out("Successfully created \"profiles\" sub-directory.");

        new File(FixedVariables.USERDIRECTORY + File.separator + "saves").mkdir();
        Logger.SUCCESS.out("Successfully created \"saves\" sub-directory.");

    }
}
