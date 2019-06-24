package com.jumbo1907.discordrichpresence.gui;

import com.jumbo1907.discordrichpresence.FixedVariables;
import com.jumbo1907.discordrichpresence.gui.nodes.background.PanelBackground;
import com.jumbo1907.discordrichpresence.gui.nodes.profiles.PanelProfiles;
import com.jumbo1907.discordrichpresence.gui.nodes.selection.PanelSelection;
import com.jumbo1907.discordrichpresence.gui.nodes.selection.SectionType;
import com.jumbo1907.discordrichpresence.gui.notification.Notification;
import javafx.animation.ParallelTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;


public class MainApplication {

    public Stage stage;
    public Scene scene;
    private Group root;

    public long startDate = System.currentTimeMillis();
    public Font font = Font.loadFont(getClass().getClassLoader().getResourceAsStream("fonts/Whitney-Semibold.otf"), 18);

    private PanelBackground panelBackground;
    public PanelSelection panelSelection;
    private PanelProfiles panelProfiles;

    public ParallelTransition visualiserAnimation;

    private HashMap<SectionType, PanelProfiles> sectionList;

    public void start(Stage primaryStage) {
        //Load the nodes
        panelBackground = new PanelBackground(this);
        panelSelection = new PanelSelection(this);
        panelProfiles = new PanelProfiles(this);

        sectionList = new HashMap<>();
        sectionList.put(SectionType.PROFILES, panelProfiles);

        stage = primaryStage;

        //Remove the top bar
        primaryStage.initStyle(StageStyle.UNDECORATED);

        //Close and minimize buttons
        Rectangle button_minimize = new Rectangle();
        button_minimize.setX(1139);
        button_minimize.setY(3);
        button_minimize.setWidth(27);
        button_minimize.setHeight(27);
        button_minimize.setFill(new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("images/button_minimize.png"))));
        panelBackground.addButtonInteraction(button_minimize, true);

        Rectangle button_exit = new Rectangle();
        button_exit.setX(1166);
        button_exit.setY(3);
        button_exit.setWidth(27);
        button_exit.setHeight(27);
        button_exit.setFill(new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("images/button_close.png"))));

        panelBackground.addButtonInteraction(button_exit, true);

        root = new Group(panelBackground.getNode(),sectionList.get(panelSelection.currentSection).getNode(),panelSelection.getNode(), panelBackground.getRectangle(), panelBackground.getImageView(), button_exit, button_minimize);
        addGuiMovingListeners(root);
        scene = new Scene(root, FixedVariables.GUI_WIDTH, FixedVariables.GUI_HEIGHT);

        //Hook notifications
        new Notification(root);

        //This will add the layout background has
        panelBackground.addLayout();


        //Add font to selection
        panelSelection.addFonts();
        panelSelection.addHovers();
        panelSelection.updateSelectedColor(null);
        panelSelection.addClickListener();

        //Add theme
        scene.getStylesheets().add(getClass().getClassLoader().getResource("themes/jbootx3.css").toExternalForm());

        scene.setFill(Color.valueOf(FixedVariables.COLOR_DARK));
        primaryStage.setTitle(FixedVariables.GUI_NAME);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void addUserInformation(String userId, String avatarID, String username) throws IOException {
        //Add avatar
        System.setProperty("http.agent", "Chrome");
        URL url = new URL("https://cdn.discordapp.com/avatars/" + userId + "/" + avatarID + ".png?size=128");
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        Image image = new Image(inputStream);
        Circle avatar = (Circle) scene.lookup("#avatar");
        avatar.setFill(new ImagePattern(image));
        inputStream.close();

        //Add user name

        HBox usernameBox = HBoxBuilder.create().spacing(0).children(
                LabelBuilder.create().text(username.split("#")[0]).textFill(Color.WHITE).font(font).build(),
                LabelBuilder.create().text("#" + username.split("#")[1]).textFill(Color.valueOf("#C4CFF0")).font(font).build()
        ).build();
        usernameBox.setLayoutX(917);
        usernameBox.setLayoutY(335);
        usernameBox.setPrefWidth(269);
        usernameBox.setPrefHeight(20);
        usernameBox.setAlignment(Pos.CENTER);

        Platform.runLater(
                () -> {
                    root.getChildren().add(usernameBox);
                    visualiserAnimation.getChildren().add(panelBackground.addVisualiserAnimation(usernameBox, 0));
                    long delay = (startDate + FixedVariables.ANIMATION_DELAY + FixedVariables.ANIMATION_LENGTH + 550) - (System.currentTimeMillis());
                    if (delay < 0) delay = 0;
                    visualiserAnimation.setDelay(Duration.millis(delay));
                    visualiserAnimation.play();
                }
        );
    }

    //Move GUI
    private double xOffset = 0;
    private double yOffset = 0;
    private boolean goAhead = false;

    private void addGuiMovingListeners(Group root) {
        root.setOnMousePressed(event -> {
            if (event.getY() < 33) {
                goAhead = true;
            }
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            if (goAhead) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        root.setOnMouseReleased(event -> goAhead = false);
    }

    public void setBotName(String text) {
        changeLabel("label_botname", text);
    }

    public void setDetails(String text) {
        changeLabel("label_details", text);
    }

    public void setTimeStamp(String text) {
        changeLabel("label_timestamp", text);
    }

    public void setState(String text) {
        changeLabel("label_state", text);
    }

    private void changeLabel(String labelName, String text) {
        Label label = (Label) scene.lookup("#" + labelName);
        label.setFont(font);

        label.setText(text);
    }


}
