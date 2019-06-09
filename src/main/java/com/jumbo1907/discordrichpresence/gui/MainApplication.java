package com.jumbo1907.discordrichpresence.gui;

import com.jumbo1907.discordrichpresence.FixedVariables;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainApplication {

    public MainApplication() {

    }

    public void start(Stage primaryStage) throws FileNotFoundException {
        //Remove the top bar
        primaryStage.initStyle(StageStyle.UNDECORATED);

        //The orange bar
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(FixedVariables.GUI_WIDTH);
        rectangle.setHeight(FixedVariables.GUI_HEIGHT);
        rectangle.setFill(Color.valueOf(FixedVariables.COLOR_ORANGE));


        //The logo
        Image image = new Image(new FileInputStream("C:\\Users\\Xander\\IdeaProjects\\DiscordRichPresence\\src\\main\\resources\\logo.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(FixedVariables.GUI_WIDTH / 2 - 100);
        imageView.setY(FixedVariables.GUI_HEIGHT / 2 - 100);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);


        ParallelTransition parallelTransition = new ParallelTransition(addStartupAnimation(imageView), addStartupAnimation(rectangle));
        parallelTransition.play();

        Group root = new Group(rectangle, imageView);
        Scene scene = new Scene(root, 1200, 600);

        scene.setFill(Color.valueOf(FixedVariables.COLOR_DARK));
        primaryStage.setTitle(FixedVariables.GUI_NAME);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TranslateTransition addStartupAnimation(Node node) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(FixedVariables.ANIMATION_LENGTH));
        translateTransition.setNode(node);
        translateTransition.setByX(600 * 2);
        translateTransition.setCycleCount(1);
        translateTransition.setDelay(Duration.millis(FixedVariables.ANIMATION_DELAY));

        return translateTransition;
    }
}
