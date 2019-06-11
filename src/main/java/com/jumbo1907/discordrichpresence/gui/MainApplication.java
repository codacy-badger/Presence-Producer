package com.jumbo1907.discordrichpresence.gui;

import com.jumbo1907.discordrichpresence.FixedVariables;
import com.jumbo1907.discordrichpresence.utils.Logger;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.LabelBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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


public class MainApplication {

    public MainApplication() {

    }

    private Stage stage;
    private Scene scene;
    private Group root;
    private ParallelTransition visualiserAnimation;
    private long startDate = System.currentTimeMillis();

    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        //Remove the top bar
        primaryStage.initStyle(StageStyle.UNDECORATED);

        //The orange bar
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(FixedVariables.GUI_WIDTH);
        rectangle.setHeight(FixedVariables.GUI_HEIGHT);
        rectangle.setFill(Color.valueOf(FixedVariables.COLOR_ORANGE));

        //The logo
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("logo.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(FixedVariables.GUI_WIDTH / 2 - 100);
        imageView.setY(FixedVariables.GUI_HEIGHT / 2 - 100);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        ParallelTransition parallelTransition = new ParallelTransition(addStartupAnimation(imageView, true), addStartupAnimation(rectangle, false));
        parallelTransition.play();

        root = new Group(FXMLLoader.load(getClass().getClassLoader().getResource("design.fxml")), rectangle, imageView);
        addGuiMovingListeners(root);

        scene = new Scene(root, 1200, 600);

        //Add minimize button
        ImageView minimizeNode = (ImageView) scene.lookup("#button_minimize");
        minimizeNode.setImage(new Image(getClass().getClassLoader().getResourceAsStream("button_minimize.png")));

        //Add close button
        ImageView exitNode = (ImageView) scene.lookup("#button_close");
        exitNode.setImage(new Image(getClass().getClassLoader().getResourceAsStream("button_close.png")));

        //Default avatar
        Circle avatar = (Circle) scene.lookup("#avatar");
        avatar.setFill(new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("default_avatar.png"))));

        //Add button functionality
        addButtonInteraction(scene);

        //Add animation for visualiser
        visualiserAnimation = new ParallelTransition(
                addVisualiserAnimation(scene.lookup("#visualisor_top"), 0),
                addVisualiserAnimation(scene.lookup("#visualisor_middle"), 0),
                addVisualiserAnimation(scene.lookup("#visualisor_bottom"), 0),
                addVisualiserAnimation(scene.lookup("#visualisor_status_1"), 100),
                addVisualiserAnimation(scene.lookup("#visualisor_status_2"), 300),
                addVisualiserAnimation(scene.lookup("#visualisor_status_3"), 400),
                addVisualiserAnimation(scene.lookup("#avatar"), 100)
        );

        scene.setFill(Color.valueOf(FixedVariables.COLOR_DARK));
        primaryStage.setTitle(FixedVariables.GUI_NAME);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private TranslateTransition addStartupAnimation(Node node, boolean picture) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(node);

        if (picture) {
            translateTransition.setByX(500);
            translateTransition.setByY(-200);
            translateTransition.setDuration(Duration.millis(FixedVariables.ANIMATION_LENGTH));

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(FixedVariables.ANIMATION_LENGTH));
            scaleTransition.setNode(node);
            scaleTransition.setDelay(Duration.millis(FixedVariables.ANIMATION_DELAY));
            scaleTransition.setByX(-.3f);
            scaleTransition.setByY(-.3f);
            scaleTransition.play();

        } else {
            translateTransition.setDuration(Duration.millis(FixedVariables.ANIMATION_LENGTH * 1.35));
            translateTransition.setByX(600 * 2);
        }
        translateTransition.setCycleCount(1);
        translateTransition.setDelay(Duration.millis(FixedVariables.ANIMATION_DELAY));

        return translateTransition;
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
        Font font = Font.loadFont(getClass().getClassLoader().getResourceAsStream("Whitney-Semibold.otf"), 18);

        HBox usernameBox = HBoxBuilder.create().spacing(0).children(
                LabelBuilder.create().text(username.split("#")[0]).textFill(Color.WHITE).font(font).build(),
                LabelBuilder.create().text("#" + username.split("#")[1]).textFill(Color.valueOf("#C4CFF0")).font(font).build()
        ).build();
        usernameBox.setLayoutX(917);
        usernameBox.setLayoutY(347);
        usernameBox.setPrefWidth(269);
        usernameBox.setPrefHeight(20);
        usernameBox.setAlignment(Pos.CENTER);

        Platform.runLater(
                () -> {
                    root.getChildren().add(usernameBox);
                    visualiserAnimation.getChildren().add(addVisualiserAnimation(usernameBox,0));

                    long delay = (startDate + FixedVariables.ANIMATION_DELAY + FixedVariables.ANIMATION_LENGTH + 550) - (System.currentTimeMillis());
                    if(delay<0) delay = 0;
                    System.out.println("delay is " + delay);
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

    private void addButtonInteraction(Scene scene) {
        //Minimize button
        Node minimizeNode = scene.lookup("#button_minimize");
        minimizeNode.setOnMouseEntered(event -> minimizeNode.setCursor(Cursor.HAND));
        minimizeNode.setOnMouseExited(event -> minimizeNode.setCursor(Cursor.DEFAULT));
        minimizeNode.setOnMouseClicked(event -> stage.setIconified(true));

        //Exit button
        Node exitNode = scene.lookup("#button_close");
        exitNode.setOnMouseEntered(event -> exitNode.setCursor(Cursor.HAND));
        exitNode.setOnMouseExited(event -> exitNode.setCursor(Cursor.DEFAULT));
        exitNode.setOnMouseClicked(event -> {
            //todo: Close the program, lol
            Logger.SUCCESS.out("Program closed successfully. Bye!");

            //This will minimize the application so it'll look like it's closing faster.
            stage.setIconified(true);
            System.exit(0);
        });
    }

    private TranslateTransition addVisualiserAnimation(Node node, int extraDelay) {
        node.setLayoutX(node.getLayoutX() + 300);
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(node);
        translateTransition.setByX(-300);
        translateTransition.setDuration(Duration.millis(FixedVariables.ANIMATION_LENGTH / 2));

        translateTransition.setCycleCount(1);
        translateTransition.setDelay(Duration.millis(extraDelay));

        return translateTransition;
    }
}
