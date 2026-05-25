//PROG2 VT2026, Inlämningsuppgift
//Grupp 58
//Nils Denward nide8018
//Erika Lundblad erlu6715
//Nellie Åkerström neak7375
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

        grid.addRow(0, new Label (path.toString()));
        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    }
}
