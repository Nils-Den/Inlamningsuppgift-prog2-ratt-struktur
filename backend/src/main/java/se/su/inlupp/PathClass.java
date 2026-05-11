package se.su.inlupp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PathClass<T> implements Path<T> {
    private T startNode;
    private T endNode;
    private final Set<Edge<T>> edges;

    public PathClass(T startNode, T endNode, Set<Edge<T>> edges) {
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
        for (Edge<T> e : edges) {
            value += e.getWeight();
        }
        return value;

    }

    public List<Edge<T>> getEdges() {
        // DENNA METOD ÄR EJ KLAR. BEHÖVER RETURNERA EDGES I ORDNING FRÅN START TILL
        // SLUT.
        List<Edge<T>> returnEdges = new ArrayList<Edge<T>>();
        returnEdges.addAll(edges);
        return returnEdges;
    }

    public List<T> getNodes() {
        // Kolla över denna igen!!!!
        List<T> returnNodes = new ArrayList<T>();
        for (Edge<T> e : edges) {
            returnNodes.add(e.getDestination());
        }
        return returnNodes;
    }

    @Override
    public Iterator<Edge<T>> iterator() {
        Iterator<Edge<T>> iter = getEdges().iterator();
        return iter;
    }

    // UTVECKLA!!!
    @Override
    public String toString() {
        String returnString = "";
        return returnString;
    }
}
