package at.ezylot.ibg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage RootStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        RootStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Collision Ball Incremental");
        primaryStage.setScene(new Scene(root, 600, 480));
        primaryStage.setResizable(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
