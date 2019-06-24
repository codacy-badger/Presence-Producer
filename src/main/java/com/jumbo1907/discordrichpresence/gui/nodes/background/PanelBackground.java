package com.jumbo1907.discordrichpresence.gui.nodes.background;

import com.jumbo1907.discordrichpresence.FixedVariables;
import com.jumbo1907.discordrichpresence.gui.MainApplication;
import com.jumbo1907.discordrichpresence.gui.nodes.PanelHandler;
import com.jumbo1907.discordrichpresence.utils.Logger;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PanelBackground implements PanelHandler {

    private final MainApplication mainApplication;
    private ImageView imageView;
    private Rectangle rectangle;
    private Node node;

    public PanelBackground(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
        try {
            node = FXMLLoader.load(getClass().getClassLoader().getResource("nodes/NodeBackground.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        node.setPickOnBounds(true);
        //The orange bar
        rectangle = new Rectangle();
        rectangle.setWidth(FixedVariables.GUI_WIDTH);
        rectangle.setHeight(FixedVariables.GUI_HEIGHT);
        rectangle.setFill(Color.valueOf(FixedVariables.COLOR_ORANGE));

        //The logo
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("images/logo.png"));
        imageView = new ImageView(image);
        imageView.setX(FixedVariables.GUI_WIDTH / 2 - 100);
        imageView.setY(FixedVariables.GUI_HEIGHT / 2 - 100);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

    }

    public void addLayout() {
        ParallelTransition parallelTransition = new ParallelTransition(addStartupAnimation(imageView, true), addStartupAnimation(rectangle, false));
        parallelTransition.play();


        //Default avatar
        Circle avatar = (Circle) mainApplication.scene.lookup("#avatar");
        avatar.setFill(new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("images/default_avatar.png"))));


        //Adds image to visualiser
        Rectangle largeImage = (Rectangle) mainApplication.scene.lookup("#large_image");
        largeImage.setFill(new ImagePattern(new Image(getClass().getClassLoader().getResourceAsStream("images/default_avatar.png"))));

        //Add animation for visualiser
         mainApplication.visualiserAnimation = new ParallelTransition(
                addVisualiserAnimation(mainApplication.scene.lookup("#visualisor_top"), 0),
                addVisualiserAnimation(mainApplication.scene.lookup("#visualisor_middle"), 0),
                addVisualiserAnimation(mainApplication.scene.lookup("#visualisor_bottom"), 0),
                addVisualiserAnimation(mainApplication.scene.lookup("#visualisor_status_1"), 100),
                addVisualiserAnimation(mainApplication.scene.lookup("#visualisor_status_2"), 300),
                addVisualiserAnimation(mainApplication.scene.lookup("#visualisor_status_3"), 400),
                addVisualiserAnimation(mainApplication.scene.lookup("#avatar"), 100),
                addVisualiserAnimation(mainApplication.scene.lookup("#label_state"), 300),
                addVisualiserAnimation(mainApplication.scene.lookup("#label_details"), 200),
                addVisualiserAnimation(mainApplication.scene.lookup("#label_timeleft"), 400),
                addVisualiserAnimation(mainApplication.scene.lookup("#label_botname"), 100),
                addVisualiserAnimation(mainApplication.scene.lookup("#playing_a_game"), 0),
                addVisualiserAnimation(mainApplication.scene.lookup("#large_image"), 150)
        );
    }
    public void addButtonInteraction(Node node, boolean minimize) {
        node.setOnMouseEntered(event -> node.setCursor(Cursor.HAND));
        node.setOnMouseExited(event -> node.setCursor(Cursor.DEFAULT));

        if(minimize){
            node.setOnMouseClicked(event -> mainApplication.stage.setIconified(true));
        } else {
            node.setOnMouseClicked(event -> {
                //todo: Close the program, lol

                long millis = System.currentTimeMillis() - mainApplication.startDate;
                String length = String.format("%02d minutes and %02d seconds",
                        TimeUnit.MILLISECONDS.toMinutes(millis),
                        TimeUnit.MILLISECONDS.toSeconds(millis) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
                );


                Logger.SUCCESS.out("Program closed successfully. Bye! (" + length + ").");

                //This will minimize the application so it'll look like it's closing faster.
                mainApplication.stage.setIconified(true);
                System.exit(0);
            });
        }
    }

    public TranslateTransition addVisualiserAnimation(Node node, int extraDelay) {
        node.setLayoutX(node.getLayoutX() + 300);
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(node);
        translateTransition.setByX(-300);
        translateTransition.setDuration(Duration.millis(FixedVariables.ANIMATION_LENGTH / 2));

        translateTransition.setCycleCount(1);
        translateTransition.setDelay(Duration.millis(extraDelay));

        return translateTransition;
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
            translateTransition.setByX(FixedVariables.GUI_HEIGHT * 2);
        }
        translateTransition.setCycleCount(1);
        translateTransition.setDelay(Duration.millis(FixedVariables.ANIMATION_DELAY));

        return translateTransition;
    }
    
    public ImageView getImageView() {
        return imageView;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Node getNode() {
        return node;
    }
}
