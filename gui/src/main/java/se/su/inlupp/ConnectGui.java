package se.su.inlupp;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ConnectGui extends Dialog<Edge<Person>>{

     private TextField nameFieldOne = new TextField(), 
                    nameFieldTwo = new TextField(),
                    relationName = new TextField(),
                    relationScore = new TextField();

    public ConnectGui(){
        setTitle("Write two names to connect them!");
        setHeaderText("Score your relationship on a scale from 0 to 10, where 0 is the best and 10 is the worst");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        grid.addRow(0, new Label("* First person:"), nameFieldOne);
        grid.addRow(1, new Label("* Second person:"), nameFieldTwo);
        grid.addRow(2, new Label("* Name your relationship:"), relationName);
        grid.addRow(3, new Label("* Relationship score:"), relationScore);
        grid.addRow(4, new Label(""));
        grid.addRow(5, new Label("* Requiered field"));

        getDialogPane().setContent(grid);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        setResultConverter(buttonType -> {
            if(buttonType == ButtonType.OK){
                try{
                    String name1 = nameFieldOne.getText(),
                    name2 = nameFieldTwo.getText(),
                    rn = relationName.getText();
                    int score = Integer.parseInt(relationScore.getText());
                    Edge<Person> newEdge = new EdgeClass(null, name2, score);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        });
    }

}
