package se.su.inlupp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Application {

  public void start(Stage stage) {
    
    //ISAKS KOD:
    //Graph<String> graph = new ListGraph<String>();
    //String javaVersion = System.getProperty("java.version");
    //String javafxVersion = System.getProperty("javafx.version");
    //Label label =
    //    new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");


    BorderPane root = new BorderPane();

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

    
    
    Scene scene = new Scene (root, 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
