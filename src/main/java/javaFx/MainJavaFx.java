// Works only with JDK8, doesn't work with JDK9 even under language level 8
package javaFx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainJavaFx extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/MainJavaFx.fxml"));

        //Alternative approach, which allows us also to extract controller
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainJavaFx.fxml"));
//        Pane pane = loader.load();
//        MainJavaFxController controller = loader.getController();         //extracting controller

        primaryStage.setTitle("Crypto currency exchange rates");
        primaryStage.setScene(new Scene(root));
//        primaryStage.setScene(new Scene(pane));       //Scene setting for the alternative approach
        primaryStage.show();
    }

//    public static void main() {           //In case we would like to start our javaFx application from here
//        launch();
//    }

}
