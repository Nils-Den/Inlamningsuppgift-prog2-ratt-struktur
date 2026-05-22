package se.su.inlupp;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class PersonImage extends BorderPane {

    ImageView image;
    Person person;
    double startX;
    double startY;


    public PersonImage(String image, Person person) {
        Image profilePic;
        if (image == null){
            profilePic = new Image (Person.class.getResourceAsStream("idea.png"));
            
        }else {
         profilePic = new Image (Person.class.getResourceAsStream(image));
        }
        this.image = new ImageView(profilePic); 

        this.person = person;
        this.image.setFitWidth(100);
        this.image.setFitHeight(100);
        setCenter(this.image);
        setBackground(Background.fill(Color.BLANCHEDALMOND));
        Label nameBar = new Label(this.person.getName());
        HBox nameBox = new HBox(nameBar);
        nameBox.setAlignment(Pos.TOP_CENTER);
        setTop(nameBox);

        //setOnMouseClicked(new MarkedNode());
        setOnMousePressed(new StartDragHandler());
        setOnMouseDragged(new DragHandler());

        setOnMouseClicked((event) -> {
            nameBox.setBackground(Background.fill(Color.CORAL));
            requestFocus();
            //hasFocus = this;
        });
        focusedProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue){
                requestFocus();
                //hasFocus = this;
                nameBox.setBackground(Background.fill(Color.CORAL));
            }
            else{
                nameBox.setBackground(Background.fill(Color.BLANCHEDALMOND));
            }
        });
    }

    public Person getPerson() {
        return this.person;
    }

    public ImageView getImageView() {
        return this.image;
    }

    public String getImagePath(){
        Image returnImage = image.getImage();
        return returnImage.getUrl();
    }

    //public static PersonImage getPersonImage(String name){

    //}

   /*  public double [] getCoordinates(){
        double [] coordinates = {startX, startY};
        return coordinates;
    }
*/
/*    class MarkedNode implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            setBackground(Background.fill(Color.AZURE));
        }
    } */

    class StartDragHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event){
             startX = event.getX();
             startY = event.getY();
        }
    }

    class DragHandler implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent event){
            double newX = getLayoutX() + event.getX() - startX;
            double newY = getLayoutY() + event.getY() - startY;
            relocate(newX, newY);
        }
    }

}
