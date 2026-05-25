//PROG2 VT2026, Inlämningsuppgift
//Grupp 58
//Nils Denward nide8018
//Erika Lundblad erlu6715
//Nellie Åkerström neak7375
package se.su.inlupp;

public class EdgeClass<T> implements Edge<T> {
    private T destination;
    private String name;
    private int weight;

    public EdgeClass(T destination, String name, int weight){
        this.destination = destination;
        this.name = name;
        if(weight > 100){
            this.weight = 100;
        }
        else if(weight < 0){
            this.weight = 0;
        }
        else{
        this.weight = weight;
    }
    }

    public T getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight can't be negative.");
        } else {
            this.weight = weight;
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return "till " + destination + " med " + name + " tar " + weight;
    }
}
