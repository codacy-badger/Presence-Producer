package com.jumbo1907.discordrichpresence.utils;

import com.jumbo1907.discordrichpresence.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public enum Logger {

    SUCCESS("[SUCCESS]"), ERROR("[ERROR]"), INFO("[INFO]"), WARNING("[WARNING]");

    String prefix;

    Logger(String prefix) {
        this.prefix = prefix;
    }

    //This will write the logging message in the console AND write it in a log file in the future.
    public void out(String message) {
        String logMessage = "[" + getCurrentTimeStamp() + "] " + prefix + " " + message;
        System.out.println(logMessage);

        //Write to file
        if(Main.fileManager != null && Main.fileManager.logWriter != null && Main.fileManager.logWriter.logPrintWriter != null){
            Main.fileManager.logWriter.logPrintWriter.println(logMessage);
            Main.fileManager.logWriter.logPrintWriter.flush();
        }

    }

    //This will return the formatted timestamp
    private String getCurrentTimeStamp() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}
