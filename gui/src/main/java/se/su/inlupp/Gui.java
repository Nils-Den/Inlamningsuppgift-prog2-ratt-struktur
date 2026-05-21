package se.su.inlupp;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Gui extends Application {
  private ListGraph<Person> allPersons = new ListGraph<>();

  private int[][] positions = { { 100, 130 }, { 250, 200 }, { 350, 50 } };

  private PersonImage [] loadedData = new PersonImage [3];

  private PersonImage personToAdd;
  private PersonImage hasFocus;
  // private Canvas canvas = new Canvas(640, 480);

  private Pane pane = new Pane();

  Label placePerson;

  public void start(Stage stage) {

    BorderPane root = new BorderPane();

    root.setCenter(pane);

    // Testa att rita linje
    // gc.strokeLine(50, 0, 50, 250);

    // Här börjar topmenyn med tillhörande knappar
    HBox bottomMenu = new HBox(15);
    bottomMenu.setPadding(new Insets(10, 10, 10, 10));
    root.setBottom(bottomMenu);

    bottomMenu.setAlignment(Pos.TOP_CENTER);

    Button removeP = new Button("Remove Person");
    bottomMenu.getChildren().add(removeP);
    removeP.setOnAction(new RemoveHandler());

    Button connectP = new Button("Connect Friends");
    bottomMenu.getChildren().add(connectP);
    connectP.setOnAction(new ConnectHandler());

    Button addP = new Button("Add Person");
    bottomMenu.getChildren().add(addP);
    addP.setOnAction(new AddPersonHandler());

    Button findPath = new Button("Find friendship connection");
    bottomMenu.getChildren().add(findPath);
    findPath.setOnAction(new FindPathHandler());

    // Här är drop down menyn som ska ligga i vänstra hörnet:
    // Vi behöver handlers överallt
    VBox dropDown = new VBox();
    MenuBar menuBar = new MenuBar();
    dropDown.getChildren().add(menuBar);

    root.setTop(dropDown);

    Menu menu = new Menu("Menu");
    menuBar.getMenus().add(menu);

    MenuItem saveItem = new MenuItem("Save");
    menu.getItems().add(saveItem);
    // saveItem.setOnAction(new SaveHandler());

    MenuItem exitItem = new MenuItem("Exit");
    menu.getItems().add(exitItem);
    // exitItem.setOnAction(new ExitHandler());

    MenuItem openItem = new MenuItem("Open");
    menu.getItems().add(openItem);
    // openItem.setOnAction(new OpenHandler());

    MenuItem addPItem = new MenuItem("Add Person");
    menu.getItems().add(addPItem);
    addPItem.setOnAction(new AddPersonHandler());

    // HÄR TESTAR VI KOD:
    load();
    int i = 0;

    for (Person p : allPersons) {
      int x = positions[i][0];
      int y = positions[i][1];
      PersonImage pi = new PersonImage(null, p);
      drawPerson(pi, x, y);
      loadedData[i] = pi;
      i++;

    } 
    for(int n = 0; n < loadedData.length; n++){
      //Collection <Edge<Person>> edges= allPersons.getEdgesFrom(loadedData[n].getPerson());
      for (int x = 0; x < loadedData.length; x++){
        allPersons.getEdgeBetween(loadedData[n].getPerson(), loadedData[x].getPerson());
        if (allPersons.getEdgeBetween(loadedData[n].getPerson(), loadedData[x].getPerson()) != null){
          drawEdge(loadedData[n], loadedData[x]);
        }
      }
        
      
      
    }

    Scene scene = new Scene(root, 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  public void drawPerson(PersonImage pi, double x, double y) {
    // PersonImage pi = new PersonImage(p.getImage(), p);
    // ImageView iv = new ImageView(p.getImage());

    pi.setLayoutX(x);
    pi.setLayoutY(y);
    // pi.setPadding(new Insets(10, 10, 10, 10));
    pi.addEventHandler(MouseEvent.MOUSE_CLICKED, new FocusHandler(pi));
    pane.getChildren().add(pi);
    // new PersonImage(iv, p);


  }

  public void drawEdge(PersonImage pi1, PersonImage pi2 ){
    if(allPersons.getEdgeBetween(pi1.getPerson(), pi2.getPerson()) != null){
     // double [] pos1 = pi1.getCoordinates();
      //double [] pos2 = pi2.getCoordinates();
      Line line = new Line();
      line.startXProperty().bind(pi1.layoutXProperty());
      line.startYProperty().bind(pi1.layoutYProperty());
      line.endXProperty().bind(pi2.layoutXProperty());
      line.endYProperty().bind(pi2.layoutYProperty());

      pane.getChildren().add(line);
    }

  }

  public void load() {
    Person nellie = new Person("Nej", 1998, "Kvinna");
    Person erika = new Person("Erk", 1995, "Kvinna");
    Person nils = new Person("Nil", 1995, "Man");
    allPersons.add(nellie);
    allPersons.add(erika);
    allPersons.add(nils);
    allPersons.connect(nellie, erika, "fiender", 10);
    allPersons.connect(nellie, nils, "bästisar", 0);

  }

  public static void main(String[] args) {
    launch(args);
  }

  class AddPersonHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      // Skapar en dialogruta från klassen AddPersonGui
      AddPersonGui addPersonGui = new AddPersonGui(allPersons);
      Optional<PersonImage> newPerson = addPersonGui.showAndWait();
      newPerson.ifPresent(person -> {
        allPersons.add(newPerson.get().getPerson());
        personToAdd = newPerson.get();
      });
      if (!newPerson.isEmpty()) {
        placePerson = new Label("Click where you want to place the person!");
        // gc.fillText("Click where you want to place the person!", 200, 25);

        // Här hårdkodar vi positionen av labeln. Kan behöva snyggas till?
        placePerson.setLayoutX(200);
        pane.getChildren().add(placePerson);
        pane.setOnMouseClicked(new PlacePersonHandler());
      }

    }
  }

  class PlacePersonHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      double y = event.getY() - 50;
      double x = event.getX() - 50;
      drawPerson(personToAdd, x, y);
      pane.getChildren().remove(placePerson);
      pane.setOnMouseClicked(null);

    }
  }

  class RemoveHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      Label removePersonLabel = new Label("The person you have selected has been removed!");

      removePersonLabel.setLayoutX(200);
      pane.getChildren().add(removePersonLabel);
      allPersons.remove(hasFocus.getPerson());
      pane.getChildren().remove(hasFocus);
     

      PauseTransition pause = new PauseTransition(Duration.seconds(3));
      pause.setOnFinished(e -> pane.getChildren().remove(removePersonLabel));
      pause.play();

    }

  }

  class FocusHandler implements EventHandler<MouseEvent> {
    private PersonImage pi;

    public FocusHandler(PersonImage pi) {
      this.pi = pi;
    }

    @Override
    public void handle(MouseEvent event) {
      hasFocus = pi;
    }
  }

  class FindPathHandler implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent event){
      new PathGui();
    }
  }

  class ConnectHandler implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent event){

    }
  }

}
