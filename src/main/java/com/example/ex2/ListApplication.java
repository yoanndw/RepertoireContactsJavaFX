package com.example.ex2;

import com.example.ex2.model.RepertoryList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ListApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ListApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 480);
        stage.setTitle("Répertoire");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}