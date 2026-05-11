package se.su.inlupp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PathClass<T> implements Path {
    private T startNode;
    private T endNode;
    private final Set<EdgeClass<T>> edges;

    public PathClass(T startNode, T endNode, Set<EdgeClass<T>> edges) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.edges = edges;
    }

    public T getStart() {
        return startNode;
    }

    public T getEnd() {
        return endNode;
    }

    public int getTotalWeight() {
        int value = 0;
        for (EdgeClass<T> e : edges) {
            value += e.getWeight();
        }
        return value;

    }

    public List<EdgeClass<T>> getEdges() {
        // DENNA METOD ÄR EJ KLAR. BEHÖVER RETURNERA EDGES I ORDNING FRÅN START TILL
        // SLUT.
        List<EdgeClass<T>> returnEdges = new ArrayList<EdgeClass<T>>();
        returnEdges.addAll(edges);
        return returnEdges;
    }

    public List<T> getNodes() {
        // Kolla över denna igen!!!!
        List<T> returnNodes = new ArrayList<T>();
        for (EdgeClass<T> e : edges) {
            returnNodes.add(e.getDestination());
        }
        return returnNodes;
    }

    @Override
    public Iterator iterator() {
        Iterator<EdgeClass<T>> iter = getEdges().iterator();
        return iter;
    }

    // UTVECKLA!!!
    @Override
    public String toString() {
        String returnString = "";
        return returnString;
    }
}
