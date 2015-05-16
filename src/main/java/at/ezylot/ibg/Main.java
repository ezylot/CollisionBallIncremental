package at.ezylot.ibg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public Stage getRootStage() {
        return RootStage;
    }

    public void setRootStage(Stage rootStage) {
        RootStage = rootStage;
    }

    private Stage RootStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.setRootStage(primaryStage);

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Collision Ball Incremental");
        primaryStage.setScene(new Scene(root, 600, 480));
        primaryStage.setResizable(true);
        primaryStage.show();

        Controller controller = loader.getController();
        controller.setMainApp(this);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
