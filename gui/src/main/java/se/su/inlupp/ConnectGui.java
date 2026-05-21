package se.su.inlupp;

import java.util.ArrayList;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ConnectGui extends Dialog<Person> {

    private TextField nameField = new TextField(),
            relationName = new TextField(),
            relationScore = new TextField();

    public ConnectGui(PersonImage hasFocus, ListGraph<Person> graph) {
        setTitle("Write person to connect with!");
        setHeaderText("Score your relationship on a scale from 0 to 10, where 0 is the best and 10 is the worst");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        grid.addRow(0, new Label("* Person to connect:"), nameField);
        grid.addRow(2, new Label("* Name your relationship:"), relationName);
        grid.addRow(3, new Label("* Relationship score:"), relationScore);
        grid.addRow(4, new Label(""));
        grid.addRow(5, new Label("* Requiered field"));

        getDialogPane().setContent(grid);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                try {
                    String personName = nameField.getText(),
                            rn = relationName.getText();
                    int score = Integer.parseInt(relationScore.getText());
                    // Edge<Person> newEdge = new EdgeClass<Person>(hasFocus.getPerson(), rn,
                    // score);

                    ArrayList<Person> list = new ArrayList<Person>(graph.getNodes());
                                                                                                                                                                    
                    if (!list.contains(graph.getPerson(personName))) {
                        new ErrorMessage<>("Person does not exist");
                        return null;
                    }
                    for (Person p : list) {
                        if (p.getName().equals(nameField.getText())) {
                            if (graph.getEdgeBetween(hasFocus.getPerson(), p) != null) {
                                new ErrorMessage<>("The connection already exists");
                                return null;
                            } else {
                                graph.connect(hasFocus.getPerson(), p, rn, score);

                                return p;
                            }

                        }
                    }

                } catch (Exception e) {
                    System.out.println("Fel: " + e.getMessage());
                    e.printStackTrace();

                }
            }
            return null;
        });
    }

}
