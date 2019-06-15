package com.jumbo1907.discordrichpresence.gui.nodes;

import com.jumbo1907.discordrichpresence.FixedVariables;
import com.jumbo1907.discordrichpresence.gui.MainApplication;
import com.jumbo1907.discordrichpresence.utils.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class PanelSelection {

    private final MainApplication mainApplication;
    private Node node;
    private String[] labels = {"configuration", "profiles", "selection", "prioritymanager", "presets", "settings"};

    public PanelSelection(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
        try {
            node = FXMLLoader.load(getClass().getClassLoader().getResource("nodes/NodePanelSelection.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //This will give every label the correct font
    public void addFonts() {
        Font font = Font.loadFont(getClass().getClassLoader().getResourceAsStream("fonts/Whitney-Semibold.otf"), 18);

        for(int i = 0; i < labels.length; i++) {
            Label label = (Label) mainApplication.scene.lookup("#label_selection_" + labels[i]);
            label.setFont(font);
        }
    }

    //This will give users feedback when they hover over an option
    public void addHovers() {
        ArrayList<String> blacklist = new ArrayList<>(Arrays.asList("selection"));

        for (int i = 0; i < labels.length; i++) {
            if (blacklist.contains(labels[i])) {
                //This means the effect won't get added to the label
                continue;
            }
            Rectangle rectangle = (Rectangle) mainApplication.scene.lookup("#label_selection_" + labels[i] + "_box");
            Label label = (Label) mainApplication.scene.lookup("#label_selection_" + labels[i]);

            EventHandler<MouseEvent> mouseEnteredEvent = event -> {
                rectangle.setFill(Color.GRAY);
                mainApplication.scene.setCursor(Cursor.HAND);
            };
            EventHandler<MouseEvent> mouseExitedEvent = event -> {
                rectangle.setFill(new Color(0, 0, 0, 0));
                mainApplication.scene.setCursor(Cursor.DEFAULT);
            };

            rectangle.setOnMouseEntered(mouseEnteredEvent);
            rectangle.setOnMouseExited(mouseExitedEvent);
            label.setOnMouseEntered(mouseEnteredEvent);
            label.setOnMouseExited(mouseExitedEvent);

            rectangle.setOnMouseExited(mouseExitedEvent);


        }
    }





    public Node getNode() {
        return node;
    }
}
