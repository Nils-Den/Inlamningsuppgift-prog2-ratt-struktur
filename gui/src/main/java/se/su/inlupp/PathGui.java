package se.su.inlupp;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PathGui extends Dialog<PersonImage>{

    private TextField nameFieldOne = new TextField(), 
                    nameFieldTwo = new TextField();

    public PathGui(){
        setTitle("Write two names to see their connection!");
        setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        grid.addRow(0, new Label("* First person:"), nameFieldOne);
        grid.addRow(1, new Label("* Second person:"), nameFieldTwo);

        getDialogPane().setContent(grid);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        setResultConverter(buttonType -> {
            if(buttonType == ButtonType.OK){
                try{
                    String name1 = nameFieldOne.getText(),
                    name2 = nameFieldTwo.getText();

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        });
    }
}
