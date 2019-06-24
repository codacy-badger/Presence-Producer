package com.jumbo1907.discordrichpresence.gui.notification;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class NotificationMessage {

    private ArrayList<String> message;
    private Color color;
    private NotificationType type;
    private long length; //Seconds
    private boolean progressbar;
    private boolean closeable;
    private Runnable closeEvent;


    public NotificationMessage(ArrayList<String> message,NotificationType type) {
        this.message = message;
        this.type = type;
    }

    public NotificationMessage(String message,NotificationType type) {
        this.message = new ArrayList<>(Arrays.asList(message));
        this.type = type;
    }



    public ArrayList<String> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<String> message) {
        this.message = message;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public boolean isProgressbar() {
        return progressbar;
    }

    public void setProgressbar(boolean progressbar) {
        this.progressbar = progressbar;
    }

    public boolean isCloseable() {
        return closeable;
    }

    public void setCloseable(boolean closeable) {
        this.closeable = closeable;
    }

    public Runnable getCloseEvent() {
        return closeEvent;
    }

    public void setCloseEvent(Runnable closeEvent) {
        this.closeEvent = closeEvent;
    }
}
