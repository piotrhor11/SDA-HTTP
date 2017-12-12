package javaFx;

import commons.CryptoCurrencyDTO;
import http.Exercise3;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainJavaFx extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

//        Parent root = FXMLLoader.load(getClass().getResource("/MainJavaFx.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainJavaFx.fxml"));
        Pane pane = loader.load();

        //Getting instance of the controller (declared in fxml) to initialize data in choiceBox
        MainJavaFxController controller = loader.getController();
        HashSet<CryptoCurrencyDTO> ccDTOHashSet = Exercise3.getCryptoCurrencyDTOHashSet();

        List<String> list = new ArrayList<>();
        for (CryptoCurrencyDTO ccDTO: ccDTOHashSet) {
            list.add(ccDTO.getName());
        }
        ObservableList obList = FXCollections.observableList(list);
        ChoiceBox<String> choiceBox = controller.getChoiceBox();
        choiceBox.setItems(obList);
        choiceBox.getSelectionModel().select(0);            //Default selection
        controller.setChoiceBox(choiceBox);

        primaryStage.setTitle("Crypto currency exchange rates");
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

//    public static void main() {
//        launch();
//    }

}
