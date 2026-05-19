package se.su.inlupp;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddPersonGui extends Dialog<Person>{

    private TextField nameField = new TextField(),
                    yearOfBirthField = new TextField(),
                    genderField = new TextField(),
                    imageField = new TextField();
    
    public AddPersonGui(){
        setTitle("Add a new person!");
        setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);


        grid.addRow(0, new Label("Name:"), nameField);
        grid.addRow(1, new Label("Year of birth:"), yearOfBirthField);
        grid.addRow(2, new Label("Gender:"), genderField);
        grid.addRow(3, new Label("Image:"), imageField); //Hur löser vi bildinmatningen snyggt?

        getDialogPane().setContent(grid);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        setResultConverter(buttonType ->{ 
            if(buttonType == ButtonType.OK){
                try{ String name = nameField.getText();
                    int yearOfBirth = Integer.parseInt(yearOfBirthField.getText());
                    String gender = genderField.getText();
                    //Hur gör vi. med Image?? en till If sats?
                    return new Person(name, yearOfBirth, gender);
                }catch (Exception e){
                    //Felmeddelande klassen ska in här!!!
                    return null;
                }
            }
            return null;
        });
    

    }


}
