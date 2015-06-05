package at.ezylot.ibg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public Stage getRootStage() {
        return rootStage;
    }

    public void setRootStage(Stage rootStage) {
        this.rootStage = rootStage;
    }

    private Stage rootStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.setRootStage(primaryStage);

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/fxml/Game.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Collision Ball Incremental");
        primaryStage.setScene(new Scene(root, Setting.SCENE_WIDTH, Setting.SCENE_HEIGHT));
        primaryStage.setResizable(true);
        primaryStage.show();

        Controller controller = loader.getController();
        controller.setMainApp(this);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
