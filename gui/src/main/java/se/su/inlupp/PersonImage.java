package se.su.inlupp;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PersonImage extends BorderPane {

    ImageView image;
    Person person;

    public PersonImage(ImageView image, Person person) {
        if (image == null){
            Image profilePic = new Image (Person.class.getResourceAsStream("idea.png"));
            this.image = new ImageView(profilePic);
        }else {
        this.image = image;
        }
        this.person = person;
        this.image.setFitWidth(100);
        this.image.setFitHeight(100);
        setCenter(this.image);
        setBackground(Background.fill(Color.BLUE));
        Label nameBar = new Label(this.person.getName());
        HBox nameBox = new HBox(nameBar);
        nameBox.setAlignment(Pos.TOP_CENTER);
        setTop(nameBox);

        setOnMouseClicked(new MarkedNode());
    }

    public Person getPerson() {
        return this.person;
    }

    public ImageView getImage() {
        return this.image;
    }

    class MarkedNode implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            setBackground(Background.fill(Color.AZURE));
        }
    }

}
