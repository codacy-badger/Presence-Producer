package com.jumbo1907.discordrichpresence.gui.nodes.profiles;

import com.jumbo1907.discordrichpresence.FixedVariables;
import com.jumbo1907.discordrichpresence.gui.MainApplication;
import com.jumbo1907.discordrichpresence.gui.nodes.PanelHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;

import java.io.IOException;

public class PanelProfiles implements PanelHandler {

    private final MainApplication mainApplication;
    private Node node;

    @FXML
    private TableView profiles_tableview;
    @FXML
    private Button buttonRename;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonRemove;
    @FXML
    private Button buttonUseProfile;
    @FXML
    private Label labelChangeName;

    public void initialize() {
        editTableView();
        editButtons(buttonAdd, buttonRemove, buttonUseProfile, buttonRename);

        labelChangeName.setFont(mainApplication.font);
    }

    public PanelProfiles(MainApplication mainApplication) {
        this.mainApplication = mainApplication;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("nodes/NodeProfiles.fxml"));
            fxmlLoader.setController(this);

            node = fxmlLoader.load();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editButtons(Button... buttons) {
        //Doesn't add the blue ugly box

        for (Button button : buttons) {
            button.setFocusTraversable(false);

            button.setStyle("-fx-border-width: 0 0 0 0" + ";" +
                    " -fx-background-color: " + FixedVariables.COLOR_BLURPLE_DARKER + ";" +
                    " -fx-text-fill: #ffffff;" +
                    "-fx-font-family: Whitney-Semibold;"
                    + "-fx-font-size: 18;");
            button.setBorder(Border.EMPTY);
        }
    }



    private void editTableView() {
        TableColumn name = new TableColumn("Name");
        TableColumn date_created = new TableColumn("Date created");
        TableColumn last_used = new TableColumn("Last used");
        TableColumn saves = new TableColumn("Amount of saves");

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date_created.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        last_used.setCellValueFactory(new PropertyValueFactory<>("lastUsed"));
        saves.setCellValueFactory(new PropertyValueFactory<>("amountUsed"));


        profiles_tableview.getColumns().clear();
        profiles_tableview.getColumns().addAll(name, date_created, last_used, saves);

        //This will make sure each tab is evenly spaced
        profiles_tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        profiles_tableview.setEditable(false);

        profiles_tableview.setItems(studentsModels);
        profiles_tableview.setStyle("-fx-background-color: " + FixedVariables.COLOR_DARK + ";"
                + "-fx-border-width: 0;" + "-fx-background-insets: 0;" + "-fx-padding:0");
    }

    @Override
    public Node getNode() {
        node.setLayoutX(190);
        return node;
    }

    private ObservableList<ProfilesModel> studentsModels = FXCollections.observableArrayList(

            new ProfilesModel("default", "INSERT DATE", "INSERT DATE", 1),
            new ProfilesModel("test", "INSERT DATE", "INSERT DATE", 25),
            new ProfilesModel("Xander", "INSERT DATE", "INSERT DATE", 8),
            new ProfilesModel("Bas", "INSERT DATE", "INSERT DATE", 0)
    );

}
