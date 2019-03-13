package com.fidz;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginController implements Initializable {

    @FXML
    private Stage LoginStage;

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    private AnchorPane parentPane;

    public String loggedUser;
    double a, b;

    Main main = new Main();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoginStage = main.programInit(parentPane);
        makeDragable();
    }

    public void closeMe(){
        System.exit(0);
    }

    public void registerBtnClicked() throws IOException {
        main.fxmlLoad("Register.fxml");
        LoginStage.close();
    }

    public void forgotBtnClicked() throws IOException {
        main.fxmlLoad("ForgotPassword.fxml");
        LoginStage.close();
    }

    public void showBtnClicked() throws IOException {
        alert.setHeaderText(null);
        alert.setTitle("Creators");
        alert.setContentText("201610370311279 - Doni Wahyu Ramadhan \n201810370311205 - Mufid Zukhruf Bahtiar \n201810370311211 - Adellia Andrimida");
        alert.showAndWait();
    }

    public void loginBtnClicked() throws IOException {
        File file = new File("src/com/fidz/lib/database.file");

        if (Password.getText().isBlank() || Password.getText().isEmpty()) {
            alert.setHeaderText(null);
            alert.setTitle("Login");
            alert.setContentText("Password Must not Blank");
            alert.showAndWait();

            return;
        }
        int count = 0;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine() && count < 1) {
                String i = sc.nextLine();
                boolean isFound = i.contains("=>" + Username.getText().toLowerCase() + "->");

                if(isFound == true){
                    count += 1;
                    boolean isMatch = i.contains("=>" + Username.getText().toLowerCase() + "->" + Password.getText());
                    if (isMatch == true){
                        String[] splitter = i.split("=>");
                        this.loggedUser = splitter[0];
                        count +=1;

                        BufferedWriter writer = new BufferedWriter(new FileWriter("src/com/fidz/lib/session.ses"));
                        writer.write("session =>" + this.loggedUser);
                        writer.close();

                        LoginStage.close();
                        main.fxmlLoad("Clock.fxml");
                    } else {
                        count-=1;
                        alert.setHeaderText(null);
                        alert.setTitle("Login");
                        alert.setContentText("Password is Wrong!! Try Again");
                        alert.showAndWait();
                    }
                } else if(!isFound && !sc.hasNextLine()){
                    alert.setHeaderText(null);
                    alert.setTitle("Login");
                    alert.setContentText("User Not Found!!");
                    alert.showAndWait();
                }
            }

            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void makeDragable() {

        parentPane.setOnMousePressed(((event) -> {
            this.a = event.getSceneX();
            this.b = event.getSceneY();
        }));

        parentPane.setOnMouseDragged(((event) ->{
            LoginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            LoginStage.setX(event.getScreenX() - this.a);
            LoginStage.setY(event.getScreenY() - this.b);
        }));
    }

}
