//PROG2 VT2026, Inlämningsuppgift
//Grupp 58
//Nils Denward nide8018
//Erika Lundblad erlu6715
//Nellie Åkerström neak7375
package se.su.inlupp;

import java.time.Year;

public class Person {
    String name;
    int yearOfBirth;
    String gender;

    public Person(String name, int yearOfBirth, String gender) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.gender = gender;

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
}
