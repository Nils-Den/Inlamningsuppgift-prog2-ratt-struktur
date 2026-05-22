package se.su.inlupp;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class ErrorMessage<T> extends Dialog<T> {

    public ErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.isPresent() && answer.get() == ButtonType.OK) {
            if (message.equals("Username already exists")) {

            }
            if (message.equals("Person does not exist")) {

            }
            if (message.equals("The connection already exists")) {

            }
            if (message.equals("The changes have not been saved. Do you still want to quit?")) {

            }
            if (message.equals("These two people have no connection")){
                
            }
        }
    }

}
