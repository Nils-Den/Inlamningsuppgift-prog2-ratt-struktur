package se.su.inlupp;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class PathResultGui extends Dialog<String> {

    public PathResultGui(Path<Person> path){
        setTitle("Your path");
        setHeaderText("Shows the path between the chosen people");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        grid.addRow(0, new Label("Your journey: ", new Label (path.toString())));
        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    }
}
