package se.su.inlupp;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class PathGui extends Dialog<Path<Person>>{

    private TextField nameFieldOne = new TextField(), 
                    nameFieldTwo = new TextField();

    public PathGui(ListGraph<Person> graph){
        setTitle("Find your buddies!");
        setHeaderText("Write two names to see their connection!");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        grid.addRow(0, new Label("* First person:"), nameFieldOne);
        grid.addRow(1, new Label("* Second person:"), nameFieldTwo);

        ToggleGroup group = new ToggleGroup();
        RadioButton BFS = new RadioButton("Breadth First");
        RadioButton DFS = new RadioButton("Depth First");
        BFS.setToggleGroup(group);
        DFS.setToggleGroup(group);
        grid.addRow(2, BFS);
        grid.addRow(3, DFS);


        getDialogPane().setContent(grid);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        setResultConverter(buttonType -> {
            if(buttonType == ButtonType.OK){
                try{
                    String name1 = nameFieldOne.getText(),
                    name2 = nameFieldTwo.getText();
                    RadioButton selected = (RadioButton) group.getSelectedToggle();
                    if (selected == BFS){
                        BFSPathFinder<Person> pathFinder = new BFSPathFinder<>();
                        return pathFinder.findPath(graph, graph.getPerson(name1), graph.getPerson(name2));
                    }else {
                        DFSPathFinder<Person> pathFinder = new DFSPathFinder<>();
                        return pathFinder.findPath(graph, graph.getPerson(name1), graph.getPerson(name2));
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        });
    }
}
