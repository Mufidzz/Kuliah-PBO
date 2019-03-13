package com.fidz;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private AnchorPane registerParent;

    @FXML
    private Stage registerStage;

    @FXML
    private TextField FullName;

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    private CheckBox checkBox;

    double a,b;

    Main main = new Main();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerStage = main.programInit(registerParent);
        alert.initStyle(StageStyle.UNDECORATED);
        makeDragable();
    }
    public void registerBtnClicked(){
        try {
            if(!checkBox.isSelected()) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("You must accept term and condition");
                alert.showAndWait();
                throw new Exception("001");
            } else if (FullName.getText() == "" || Username.getText() == "" || Password.getText() == ""){
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Form Cannot be Empty");
                alert.showAndWait();
                throw new Exception("001");
            }


            BufferedWriter writer = new BufferedWriter(new FileWriter("src/com/fidz/lib/database.file", true));
            writer.append(FullName.getText() + "=>" + Username.getText().toLowerCase() + "->" + Password.getText() + "\n");
            writer.close();

            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Success");
            alert.setContentText("Register Success");
            alert.showAndWait();

            registerStage.close();
            main.fxmlLoad("Login.fxml");

        } catch (Exception e){
            alert.setTitle("Error : " + e);
            alert.setContentText("Register Failed");
            alert.showAndWait();
        }
    }

    private void makeDragable() {

        registerParent.setOnMousePressed(((event) -> {
            this.a = event.getSceneX();
            this.b = event.getSceneY();
        }));

        registerParent.setOnMouseDragged(((event) ->{
            registerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            registerStage.setX(event.getScreenX() - this.a);
            registerStage.setY(event.getScreenY() - this.b);
        }));
    }

    public void closeMe()throws IOException {
        main.fxmlLoad("Login.fxml");
        registerStage.close();
    }
}
