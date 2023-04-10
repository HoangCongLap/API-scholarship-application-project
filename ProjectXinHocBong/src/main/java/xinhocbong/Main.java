package xinhocbong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AddOrganization.fxml"));
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AddStudent.fxml"));
            setUserAgentStylesheet(STYLESHEET_CASPIAN);

//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AddStudent.fxml"));
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Statistical.fxml"));
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

