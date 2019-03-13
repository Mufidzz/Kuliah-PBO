package com.fidz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader.load(getClass().getResource("Login.fxml"));
    }

    public Stage programInit(Parent parent){
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        return  stage;
    }

    public Stage programInitOnly(Parent parent){
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UNDECORATED);

        return  stage;
    }

    public void fxmlLoad(String fxml) throws IOException {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}