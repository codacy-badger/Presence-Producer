package com.jumbo1907.discordrichpresence.gui.notification;

import com.jumbo1907.discordrichpresence.gui.MainApplication;
import com.jumbo1907.discordrichpresence.gui.nodes.PanelHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class PanelNotification implements PanelHandler {

    private final MainApplication mainApplication;
    private Node node;


    @FXML
    private Rectangle rectangleBackground;
    @FXML
    private Rectangle rectangleSmall;
    @FXML
    private Label labelText;


    public PanelNotification(MainApplication mainApplication) {
        this.mainApplication = mainApplication;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("nodes/NodeNotification.fxml"));
            fxmlLoader.setController(this);

            node = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Node getNode() {
        node.resize(350,80);
        return node;
    }
}
