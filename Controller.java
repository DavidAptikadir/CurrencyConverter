package sample;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.Optional;


public class Controller {
    @FXML
    TextField textField1;
    @FXML
    TextField textField2;
    @FXML
    ChoiceBox choiceBox1;
    @FXML
    ChoiceBox choiceBox2;

    private Currency currency=new Currency();
    String currencyFrom=null;
    String currencyTo=null;


    private ObservableList<String> currencies= FXCollections.observableArrayList(currency.getCurrencies());


    @FXML
    public void initialize(){
        choiceBox1.setItems(currencies);
        choiceBox2.setItems(currencies);
        textField2.setDisable(true);

        //set initial values
        choiceBox1.setValue(currencies.get(0));
        choiceBox2.setValue(currencies.get(1));
        currencyFrom="USD";
        currencyTo="EUR";
        textField1.setText("1.00");
        textField2.setText("0.83");

        //GET  SELECTED CURRENCIES
        choiceBox1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                currencyFrom=choiceBox1.getSelectionModel().getSelectedItem().toString();
            }
        });

        choiceBox2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                currencyTo=choiceBox2.getSelectionModel().getSelectedItem().toString();
            }
        });
    }

    @FXML
    public void convertCurrency(){
        String textFromTextField=textField1.getText();
        try{
            double amount=Double.parseDouble(textFromTextField);
            double result=currency.convert(amount,currencyFrom,currencyTo);
            textField2.setText(Double.toString(result));
        }catch(Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("WRONG INPUT");
            alert.setContentText("WRONG INPUT PRESS OKAY AND TRY AGAIN");
            Optional<ButtonType> result=alert.showAndWait();
            if(result.isPresent()&&result.get().equals(ButtonType.OK)){
                textField1.setText("1.00");
            }
        }
    }
}
