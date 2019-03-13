package com.fidz;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ClockController implements Initializable  {

    @FXML
    AnchorPane parent;

    @FXML
    private Label Hello;

    @FXML
    private Label ClockLbl;

    @FXML
    private Label Date;

    @FXML
    Stage clockStage;

    double a,b;

    Main main = new Main();
    LoginController lc = new LoginController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clockStage = main.programInitOnly(parent);

        File file = new File("src/com/fidz/lib/session.ses");
        try {
            Scanner sc = new Scanner(file);
            String i = sc.nextLine();

            String[] temp = i.split("=>");
            System.out.println(temp[1]);
            Hello.setText("HELLO, " + temp[1].toUpperCase() + " !");


        } catch (Exception e){
            Hello.setText("Error " + e + "While Get Session");
        }

        runClock();
        clockStage.show();
    }

    public void runClock(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMMM yyyy - zzzz");
        ClockLbl.setText(sdf.format(cal.getTime()) );
        Date.setText(sdf2.format(cal.getTime()) );
    }
    private void makeDragable() {

        parent.setOnMousePressed(((event) -> {
            this.a = event.getSceneX();
            this.b = event.getSceneY();
        }));

        parent.setOnMouseDragged(((event) ->{
            clockStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            clockStage.setX(event.getScreenX() - this.a);
            clockStage.setY(event.getScreenY() - this.b);
        }));
    }

    public void closeMe(){
        System.exit(0);
    }
}
