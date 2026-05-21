package se.su.inlupp;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class AddPersonGui extends Dialog<PersonImage> {

    private TextField nameField = new TextField(),
            yearOfBirthField = new TextField(),
            genderField = new TextField(),
            imageField = new TextField();

    public AddPersonGui(ListGraph graph) {
        setTitle("Add a new person!");
        setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        grid.addRow(0, new Label("* Username:"), nameField);
        grid.addRow(1, new Label("* Year of birth:"), yearOfBirthField);
        grid.addRow(2, new Label("* Gender:"), genderField);
        grid.addRow(3, new Label("Image:"), imageField); // Hur löser vi bildinmatningen snyggt?
        grid.addRow(4, new Label(""));
        grid.addRow(5, new Label("* Requiered field"));

        getDialogPane().setContent(grid);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                ArrayList <Person> list = new ArrayList<Person>(graph.getNodes());                
                for(Person p : list ){
                    if(p.getName().equals(nameField.getText())){
                        new ErrorMessage<>("Name already exists");
                    }
                }
                
                }//anropa alert för ej flera med samma namn
                try {
                    String name = nameField.getText();
                    int yearOfBirth = Integer.parseInt(yearOfBirthField.getText());
                    String gender = genderField.getText();
                    // Hur gör vi. med Image?? en till If sats?
                    Person newPerson = new Person(name, yearOfBirth, gender);
                    if (imageField.getText().isEmpty()) {
                        PersonImage newPersonImage = new PersonImage(null, newPerson);
                        return newPersonImage;
                    } else {
                        ImageView profilePic = new ImageView(imageField.getText());
                        PersonImage newPersonImage = new PersonImage(profilePic, newPerson);
                        return newPersonImage;
                    }
                } catch (Exception e) {
                    // Felmeddelande klassen ska in här!!!
                    return null;
                }
            }
            return null;
        });

    }

}
