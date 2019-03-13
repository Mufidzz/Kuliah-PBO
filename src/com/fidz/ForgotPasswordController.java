package com.fidz;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;


public class ForgotPasswordController implements Initializable {

    @FXML
    private AnchorPane parent;

    @FXML
    private TextField Username;

    @FXML
    private Stage ForgotStage;

    double a,b;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    Main main = new Main();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ForgotStage = main.programInit(parent);
        alert.initStyle(StageStyle.UNDECORATED);
        makeDragable();
    }

    public void forgotBtnClicked() {
        File file = new File("src/com/fidz/lib/database.file");
        int count = 0;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine() && count < 2) {
                String i = sc.nextLine();
                boolean isFound = i.contains("=>" + Username.getText().toLowerCase() + "->");

                if (isFound == true) {
                    count += 1;
                    String[] pw = i.split("->");
                    alert.setHeaderText(null);
                    alert.setTitle("Creators");
                    alert.setContentText("Password : "+pw[1]);
                    alert.showAndWait();
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeMe() throws IOException {
        main.fxmlLoad("Login.fxml");
        ForgotStage.close();
    }

    private void makeDragable() {

        parent.setOnMousePressed(((event) -> {
            this.a = event.getSceneX();
            this.b = event.getSceneY();
        }));

        parent.setOnMouseDragged(((event) ->{
            ForgotStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ForgotStage.setX(event.getScreenX() - this.a);
            ForgotStage.setY(event.getScreenY() - this.b);
        }));
    }
}
