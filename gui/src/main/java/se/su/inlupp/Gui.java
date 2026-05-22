package se.su.inlupp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Gui extends Application {
  private ListGraph<Person> allPersons = new ListGraph<>();

  private boolean changed = false;

  private Stage stage;

  private FileChooser fileChooser = new FileChooser();

  private int[][] positions = { { 100, 130 }, { 250, 200 }, { 350, 50 }, { 100, 270 }, { 30, 400 }, { 50, 250 } };

  private PersonImage[] loadedData = new PersonImage[6];

  private PersonImage personToAdd;
  private PersonImage hasFocus;
  // private Canvas canvas = new Canvas(640, 480);

  private Pane pane = new Pane();

  Label placePerson;

  private HashMap<Person, PersonImage> personMap = new HashMap<>();

  private HashMap<Person, List<Line>> edgeLines = new HashMap<>();

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
    saveItem.setOnAction(new SaveHandler());

    MenuItem exitItem = new MenuItem("Exit");
    menu.getItems().add(exitItem);
    // exitItem.setOnAction(new ExitHandler());

    MenuItem openItem = new MenuItem("Open");
    menu.getItems().add(openItem);
    openItem.setOnAction(new LoadHandler());

    MenuItem addPItem = new MenuItem("Add Person");
    menu.getItems().add(addPItem);
    addPItem.setOnAction(new AddPersonHandler());

    // HÄR TESTAR VI KOD:
    loadData();
    int i = 0;

    for (Person p : allPersons) {
      int x = positions[i][0];
      int y = positions[i][1];
      PersonImage pi = new PersonImage(null, p);
      drawPerson(pi, x, y);
      loadedData[i] = pi;
      i++;

    }
    for (int n = 0; n < loadedData.length; n++) {
      // Collection <Edge<Person>> edges=
      // allPersons.getEdgesFrom(loadedData[n].getPerson());
      for (int x = 0; x < loadedData.length; x++) {
        allPersons.getEdgeBetween(loadedData[n].getPerson(), loadedData[x].getPerson());
        if (allPersons.getEdgeBetween(loadedData[n].getPerson(), loadedData[x].getPerson()) != null) {
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
    personMap.put(pi.getPerson(), pi);

  }

  public void drawEdge(PersonImage pi1, PersonImage pi2) {
    if (allPersons.getEdgeBetween(pi1.getPerson(), pi2.getPerson()) != null) {
      // double [] pos1 = pi1.getCoordinates();
      // double [] pos2 = pi2.getCoordinates();
      Line line = new Line();
      line.startXProperty().bind(pi1.layoutXProperty());
      line.startYProperty().bind(pi1.layoutYProperty());
      line.endXProperty().bind(pi2.layoutXProperty());
      line.endYProperty().bind(pi2.layoutYProperty());

      pane.getChildren().add(line);

      edgeLines.computeIfAbsent(pi1.getPerson(), k -> new ArrayList<>()).add(line);
      edgeLines.computeIfAbsent(pi2.getPerson(), k -> new ArrayList<>()).add(line);
    }

  }

  public void save(String fileName) {
    try {
      FileWriter fileWriter = new FileWriter(fileName);
      PrintWriter printWriter = new PrintWriter(fileWriter);
      for (Person p : allPersons) {
        PersonImage pi = personMap.get(p);
        printWriter.println(p.getName() + ";" + p.getYearOfBirth() + ";" + p.getGender() + ";" + pi.getLayoutX() + ";"
            + pi.getLayoutY() + ";" + pi.getImagePath());

      }
      for (Person p : allPersons) {
        for (Edge<Person> e : allPersons.getEdgesFrom(p)) {
          printWriter.println(
              "EDGE;" + p.getName() + ";" + e.getDestination().getName() + ";" + e.getName() + ";" + e.getWeight());
        }
      }
      printWriter.close();
      fileWriter.close();
    } catch (FileNotFoundException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "Can't open file");
      alert.showAndWait();
    } catch (IOException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR, "IO Error " + e.getMessage());
      alert.showAndWait();
    }

  }

  public void open(String fileName){
    try {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader reader = new BufferedReader(fileReader);
      Map<PersonImage, Edge<Person>> personImages = new HashMap<>();
      String line;
      while ((line = reader.readLine()) != null){
        String[] split = parseLine(line);
        if (!split[0].equals("EDGE")){
        //for (int i = 0; i < split.length; i++){
          String name = split[0];
          int year = Integer.parseInt(split[1]);
          String gender = split[2];
          double layoutX = Double.parseDouble(split[3]);
          double layoutY = Double.parseDouble(split[4]);
          //Image image = split[5];
          Person newPerson = new Person(name, year, gender);
          PersonImage newPersonImage = new PersonImage(newPerson, image);
          personMap.put(newPerson, newPersonImage);
          allPersons.add(newPerson);
          drawPerson(newPersonImage, layoutX, layoutY);
        }else {
          allPersons.connect(allPersons.getPerson(split[1]), allPersons.getPerson(split[2]), split[3], Integer.parseInt(split[4]));
          Edge<Person> newEdge = allPersons.getEdgeBetween(allPersons.getPerson(split[1]), allPersons.getPerson(split[2]));
          edgeLines.put(allPersons.getPerson(split[1]), newEdge);


          //connect(T node1, T node2, String name, int weight)
          //EDGE;Holy Spirit;Father;bästisar;0
        }

          //Holy Spirit;1995;Man;534.0;180.0;null
        //}
        
        //personImages.add(parseLine(line));

      }
      System.out.println(personImages);
    }
  }

  private String[] parseLine(String line){
    String[] split = line.split(";");
    return split;

  }


  public void loadData() {
    Person father = new Person("Father", 1998, "Kvinna");
    Person son = new Person("Son", 1995, "Kvinna");
    Person hs = new Person("Holy Spirit", 1995, "Man");
    Person devil = new Person("The Devil", 1995, "Man");
    Person jesus = new Person("Jesus", 1995, "Man");
    Person allah = new Person("Allah", 1995, "Man");
    allPersons.add(father);
    allPersons.add(son);
    allPersons.add(hs);
    allPersons.add(devil);
    allPersons.add(jesus);
    allPersons.add(allah);
    allPersons.connect(father, son, "fiender", 10);
    allPersons.connect(father, hs, "bästisar", 0);
    allPersons.connect(son, devil, "bästisar", 0);
    allPersons.connect(devil, jesus, "bästisar", 0);

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
      List<Line> lines = edgeLines.getOrDefault(hasFocus.getPerson(), new ArrayList<>());
      pane.getChildren().removeAll(lines);
      edgeLines.remove(hasFocus.getPerson());

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

  class FindPathHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      PathGui pathGui = new PathGui(allPersons);
      Optional<Path<Person>> result = pathGui.showAndWait();
      result.ifPresent(p -> {
        PathResultGui pathResult = new PathResultGui(result.get());
        pathResult.showAndWait();

      });

    }
  }

  class ConnectHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      ConnectGui connect = new ConnectGui(hasFocus, allPersons);
      Optional<Person> result = connect.showAndWait();
      result.ifPresent(p -> {
        PersonImage otherPerson = personMap.get(p);
        drawEdge(hasFocus, otherPerson);
      });
    }
  }

  class SaveHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      File file = fileChooser.showSaveDialog(stage);
      if (file != null) {
        save(file.getAbsolutePath());
        changed = false;
      }
    }
  }

  class LoadHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      File file = fileChooser.showOpenDialog(stage);
      if (file != null){
        open(file.getAbsolutePath());

      }
    }
  }

}
