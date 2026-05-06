package se.su.inlupp;

public class EdgeClass<T> implements Edge<T> {
    private T destination;
    private String name;
    private int weight;

    public EdgeClass(T destination, String name, int weight){
        this.destination = destination;
        this.name = name;
        this.weight = weight;
    }

    public T getDestination() {
        return destination;
    }
    // getDestination – returnerar den nod som kanten pekar till.

    public int getWeight() {
        return weight;
    }
    // getWeight – returnerar kantens vikt.

    public void setWeight(int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight can't be negative.");
        } else {
            this.weight = weight;
        }
    }
    // setWeight – s¨atter kantens vikt. Om vikten ¨ar negativ skall undantaget
    // IllegalArgumentException genereras.

    public String getName() {
        return name;
    }
    // • getName – returnerar kantens namn.
    @Override
    public String toString(){
        return destination + " (" + name + ": " + weight + ")";
    }
}
