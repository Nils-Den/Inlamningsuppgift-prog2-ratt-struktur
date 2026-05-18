package se.su.inlupp;

import java.util.List;
import java.util.Random;

import javafx.application.Application;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Gui extends Application {
  ListGraph<Person> allPersons = new ListGraph<>();

  int[][] positions = {{100, 100}, {200, 100}, {300, 100}};


  public void start(Stage stage) {
    //ISAKS KOD:
    //Graph<String> graph = new ListGraph<String>();
    //String javaVersion = System.getProperty("java.version");
    //String javafxVersion = System.getProperty("javafx.version");
    //Label label =
    //    new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
    

    BorderPane root = new BorderPane();

    Canvas canvas = new Canvas(640, 480);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    root.setCenter(canvas);

    //Testa att rita linje
    gc.strokeLine(50, 0, 50, 250);



    //Här börjar topmenyn med tillhörande knappar
    HBox bottomMenu = new HBox(15);
    bottomMenu.setPadding(new Insets (10, 10, 10, 10));
    root.setBottom(bottomMenu);

    bottomMenu.setAlignment(Pos.TOP_CENTER);

    Button removeP = new Button("Remove Person");
    bottomMenu.getChildren().add(removeP);

    Button connectP = new Button("Connect Friends");
    bottomMenu.getChildren().add(connectP);

    Button addP = new Button("Add Person");
    bottomMenu.getChildren().add(addP);


    //Här är drop down menyn som ska ligga i vänstra hörnet:
    //Vi behöver handlers överallt
    VBox dropDown = new VBox();
    MenuBar menuBar = new MenuBar();
    dropDown.getChildren().add(menuBar);

    root.setTop(dropDown);


    Menu menu = new Menu("Menu");
    menuBar.getMenus().add(menu);

    MenuItem saveItem = new MenuItem("Save");
    menu.getItems().add(saveItem);
    //saveItem.setOnAction(new SaveHandler());

    MenuItem exitItem = new MenuItem("Exit");
    menu.getItems().add(exitItem);
    //exitItem.setOnAction(new ExitHandler());

    MenuItem openItem = new MenuItem("Open");
    menu.getItems().add(openItem);
    //openItem.setOnAction(new OpenHandler());

    MenuItem addPItem = new MenuItem("Add Person");
    menu.getItems().add(addPItem);
    //addPItem.setOnAction(new AddPItemHandler()?????);


    //HÄR TESTAR VI KOD:
    load();
    int i = 0;
    for (Person p: allPersons){
      int x = positions [i][0];
      int y = positions [i][1];
      gc.drawImage(p.getImage(), x, y, 100, 100);
      i++;
      
    }
    
    Scene scene = new Scene (root, 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  public void load(){
    Person nellie = new Person("Nellie Åkerström", 1998, "Kvinna");
    Person erika = new Person ("Erika Lundblad", 1995, "Kvinna");
    Person nils = new Person("Nils Denward", 1995, "Man");
    allPersons.add(nellie);
    allPersons.add(erika);
    allPersons.add(nils);
    allPersons.connect(nellie, erika, "fiender", 10);
    allPersons.connect(nellie, nils, "bästisar", 0);

  }

  public static void main(String[] args) {
    launch(args);
  }
}
