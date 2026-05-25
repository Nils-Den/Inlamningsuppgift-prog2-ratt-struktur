//PROG2 VT2026, Inlämningsuppgift
//Grupp 58
//Nils Denward nide8018
//Erika Lundblad erlu6715
//Nellie Åkerström neak7375
package se.su.inlupp;

import javafx.event.EventHandler;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import java.io.File;

public class AddPersonGui extends Dialog<PersonImage> {

    private TextField nameField = new TextField(),
            yearOfBirthField = new TextField(),
            genderField = new TextField();
    
    private Button imageButton = new Button("Choose picture");
    private boolean pictureAdded = false;
    private Image profile;

    public AddPersonGui(ListGraph<Person> graph) {
        setTitle("Add a new person!");
        setHeaderText(null);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);

        grid.addRow(0, new Label("* Username:"), nameField);
        grid.addRow(1, new Label("* Year of birth:"), yearOfBirthField);
        grid.addRow(2, new Label("* Gender:"), genderField);
        grid.addRow(3, new Label("Choose profile picture:"), imageButton );
        imageButton.setOnAction(new ImageHandler());
        grid.addRow(4, new Label(""));
        grid.addRow(5, new Label("* Requiered field"));

        getDialogPane().setContent(grid);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        setResultConverter(buttonType -> {
            
            if (buttonType == ButtonType.OK) {
                ArrayList <Person> list = new ArrayList<Person>(graph.getNodes());                
                for(Person p : list ){
                    if(p.getName().equals(nameField.getText())){
                        new ErrorMessage<>("Username already exists");
                        return null;
                    }
                }               
                
                try {
                    String name = nameField.getText();
                    int yearOfBirth = Integer.parseInt(yearOfBirthField.getText());
                    String gender = genderField.getText();
                    // Hur gör vi. med Image?? en till If sats?
                    Person newPerson = new Person(name, yearOfBirth, gender);
                    if (!pictureAdded) {
                        PersonImage newPersonImage = new PersonImage(null, newPerson);
                        return newPersonImage;
                    } else {
                        PersonImage newPersonImage = new PersonImage(profile.getUrl(), newPerson);
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
  class ImageHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(getOwner());
            if(file != null){
                //FileReader filereader = new FileReader(file);
               // BufferedReader bufferedReader = new BufferedReader(filereader);
                profile = new Image(file.toURI().toString());
                pictureAdded = true;
            }

        }   
    }
   
}
