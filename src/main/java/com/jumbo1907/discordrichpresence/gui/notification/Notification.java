package com.jumbo1907.discordrichpresence.gui.notification;

import com.jumbo1907.discordrichpresence.Main;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import org.controlsfx.control.Notifications;


public class Notification {

    private Group root;
    private Rectangle box;
    private Rectangle progressBar;

    private PanelNotification panelNotification;

    public Notification(Group root) {
        this.root = root;
        panelNotification = new PanelNotification(Main.mainApplication);

        sendNotification(null);
    }

    public void sendNotification(NotificationMessage notificationMessage) {


        Platform.runLater(() -> {
            Notifications.create().graphic(panelNotification.getNode()).hideCloseButton().show();
            Notifications.create().graphic(panelNotification.getNode()).hideCloseButton().show();
            Notifications.create().graphic(panelNotification.getNode()).hideCloseButton().show();
            Notifications.create().graphic(panelNotification.getNode()).hideCloseButton().show();
        });

    }

}


