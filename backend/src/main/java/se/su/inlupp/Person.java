//PROG2 VT2026, Inlämningsuppgift
//Grupp 58
//Nils Denward nide8018
//Erika Lundblad erlu6715
//Nellie Åkerström neak7375
package se.su.inlupp;

import java.time.Year;

import javafx.scene.image.Image;

public class Person {
    private String name;
    private int yearOfBirth;
    private String gender;
    private Image image;

    public Person(String name, int yearOfBirth, String gender, Image image) {
        this(name, yearOfBirth, gender);
        this.image = image;
    }
    
    public Person(String name, int yearOfBirth, String gender) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.gender = gender;
        if (image == null){
            this.image = new Image (Person.class.getResourceAsStream("idea.png"));
        }
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        int year = Year.now().getValue();
        return year - yearOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public Image getImage(){
        return image;
    }
}
