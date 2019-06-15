package com.jumbo1907.discordrichpresence.gui.nodes;

import com.jumbo1907.discordrichpresence.FixedVariables;
import com.jumbo1907.discordrichpresence.gui.MainApplication;
import com.jumbo1907.discordrichpresence.utils.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class PanelSelection {

    private final MainApplication mainApplication;
    private Node node;
    private String[] labels = {"configuration", "profiles", "selection", "prioritymanager", "presets", "settings"};
    private SectionType currentSection = SectionType.CONFIGURATION;

    //This means this won't get used in click and hover
    private ArrayList<String> blacklist = new ArrayList<>(Arrays.asList("selection"));

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

        for (final String labelName : labels) {
            Label label = (Label) mainApplication.scene.lookup("#label_selection_" + labelName);
            label.setFont(font);
        }
    }

    //This make the current selection another color so you see it's highlighted
    //If newSectionType is null, it'll won't replace the old one and just update the box
    public void updateSelectedColor(SectionType newSectionType) {
        if (newSectionType != null) {
            Rectangle rectangle = (Rectangle) mainApplication.scene.lookup("#label_selection_" + currentSection.toString().toLowerCase() + "_box");
            rectangle.setFill(new Color(0, 0, 0, 0));
            currentSection = newSectionType;
        }

        Rectangle newRectangle = (Rectangle) mainApplication.scene.lookup("#label_selection_" + currentSection.toString().toLowerCase() + "_box");
        newRectangle.setFill(Paint.valueOf(FixedVariables.COLOR_BLURPLE_DARKER));

    }

    //This will give users feedback when they hover over an option
    public void addClickListener() {
        for (final String labelName : labels) {
            if (blacklist.contains(labelName)) {
                //This means the effect won't get added to the label
                continue;
            }

            Rectangle rectangle = (Rectangle) mainApplication.scene.lookup("#label_selection_" + labelName + "_box");
            Label label = (Label) mainApplication.scene.lookup("#label_selection_" + labelName);


            EventHandler<MouseEvent> mouseClickEvent = event -> {

                //This means the section was already selected.
                if(currentSection == SectionType.valueOf(labelName.toUpperCase())){
                    Logger.WARNING.out("User already selected this section (" + labelName.toLowerCase() + ").");
                    return;
                }

                updateSelectedColor(SectionType.valueOf(labelName.toUpperCase()));

                //todo: Make sure the gui updates after click
                Logger.INFO.out("User switched to section \"" + labelName + "\".");
            };

            rectangle.setOnMouseClicked(mouseClickEvent);
            label.setOnMouseClicked(mouseClickEvent);
        }
    }

    //This will handle if you click a section
    public void addHovers() {
        for (final String labelName : labels) {
            if (blacklist.contains(labelName)) {
                //This means the effect won't get added to the label
                continue;
            }

            Rectangle rectangle = (Rectangle) mainApplication.scene.lookup("#label_selection_" + labelName + "_box");
            Label label = (Label) mainApplication.scene.lookup("#label_selection_" + labelName);

            EventHandler<MouseEvent> mouseEnteredEvent = event -> {
                rectangle.setFill(Paint.valueOf(FixedVariables.COLOR_BLURPLE_DARKER));
                mainApplication.scene.setCursor(Cursor.HAND);
            };
            EventHandler<MouseEvent> mouseExitedEvent = event -> {
                rectangle.setFill(new Color(0, 0, 0, 0));
                mainApplication.scene.setCursor(Cursor.DEFAULT);

                updateSelectedColor(null);
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
