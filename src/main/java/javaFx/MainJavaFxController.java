package javaFx;

import commons.CryptoCurrencyDTO;
import http.Exercise3;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

public class MainJavaFxController implements Initializable {

    @FXML
    private Label result;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField amount;

    @FXML
    private TextArea console;

    @FXML
    private void convert(ActionEvent event){

        BigDecimal rate = BigDecimal.ZERO;
        BigDecimal number = new BigDecimal(amount.getText());
        String choice = choiceBox.getValue();

        HashSet<CryptoCurrencyDTO> ccDTOHashSet = Exercise3.getCryptoCurrencyDTOHashSet();

        for (CryptoCurrencyDTO ccDTO: ccDTOHashSet) {
            if (choice.equals(ccDTO.getName())){
                rate = ccDTO.getPrice_usd();
            }
        }
        result.setText((number.multiply(rate)).toString());
        console.appendText("\nLook above for your result :)");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Getting data from Exercise3 class and use it to populate initial data for choiceBox
        HashSet<CryptoCurrencyDTO> ccDTOHashSet = Exercise3.getCryptoCurrencyDTOHashSet();
        List<String> list = new ArrayList<>();
        for (CryptoCurrencyDTO ccDTO: ccDTOHashSet) {
            list.add(ccDTO.getName());
        }
        ObservableList obList = FXCollections.observableList(list);
        choiceBox.setItems(obList);
        choiceBox.getSelectionModel().select(0);            //Default selection

        //Adds auto scroll functionality to TextArea console
        console.textProperty().addListener(((observable, oldValue, newValue) -> {
            console.setScrollTop(Double.MAX_VALUE);
        }));

        //Input validation - accepts only numeric characters
        amount.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")){
                amount.setText(oldValue);
                console.appendText("\n\"Amount\" accepts only a numeric values");
            }
        });



        console.setText("Choose Crypto Currency and enter an amount. The convert button will give you its current equivalent in USD");
    }
}
