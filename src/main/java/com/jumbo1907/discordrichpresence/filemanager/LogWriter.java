package com.jumbo1907.discordrichpresence.filemanager;

import com.jumbo1907.discordrichpresence.FixedVariables;
import com.jumbo1907.discordrichpresence.utils.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LogWriter {

    private String logDirectory;
    public PrintWriter logPrintWriter;

    public LogWriter(String logDirectory) {
        this.logDirectory = logDirectory;
    }

    //This will create a new log file to the date and time
    public void createLog() {
        //This will create the name of the log file (format: 2019-06-16)
        String formatted = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now());

        File logFile = new File(logDirectory + File.separator + formatted + ".txt");

        //This will create the new file and make sure it doesn't exist already. If it already exist, it'll add a number to the end
        int counter = 0;
        while (logFile.exists()) {
            counter++;
            logFile = new File(logDirectory + File.separator + formatted + " " + counter + ".txt");
        }
        if (counter != 0) {
            Logger.SUCCESS.out("Logfile successfully created! (\"" + logDirectory + File.separator + formatted + " " + counter + ".txt\")");
        } else
            Logger.SUCCESS.out("Logfile successfully created! (\"" + logDirectory + File.separator + formatted + ".txt\")");
        try {
            logFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            logPrintWriter = new PrintWriter(logFile);
            logPrintWriter.println("  _____                                     _____               _                     ");
            logPrintWriter.println(" |  __ \\                                   |  __ \\             | |                    ");
            logPrintWriter.println(" | |__) | __ ___  ___  ___ _ __   ___ ___  | |__) | __ ___   __| |_   _  ___ ___ _ __ ");
            logPrintWriter.println(" |  ___/ '__/ _ \\/ __|/ _ \\ '_ \\ / __/ _ \\ |  ___/ '__/ _ \\ / _` | | | |/ __/ _ \\ '__|");
            logPrintWriter.println(" | |   | | |  __/\\__ \\  __/ | | | (_|  __/ | |   | | | (_) | (_| | |_| | (_|  __/ |   ");
            logPrintWriter.println(" |_|   |_|  \\___||___/\\___|_| |_|\\___\\___| |_|   |_|  \\___/ \\__,_|\\__,_|\\___\\___|_|   ");
            logPrintWriter.println("                                                                                      ");
            logPrintWriter.println("Version: " + FixedVariables.VERSION);
            logPrintWriter.println("Authors: Bas950 & Jumbo_1907");
            logPrintWriter.println("Date launched: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
            logPrintWriter.println("");
            logPrintWriter.println("");
            logPrintWriter.println("");
            logPrintWriter.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
