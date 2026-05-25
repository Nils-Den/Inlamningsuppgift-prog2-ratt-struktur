//PROG2 VT2026, Inlämningsuppgift
//Grupp 58
//Nils Denward nide8018
//Erika Lundblad erlu6715
//Nellie Åkerström neak7375
package se.su.inlupp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PathClass<T> implements Path<T> {
    private T startNode;
    private T endNode;
    private final List<Edge<T>> edges;

    public PathClass(T startNode, T endNode, List<Edge<T>> edges) {
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
        return edges;
    }

    public List<T> getNodes() {
        List<T> returnNodes = new ArrayList<T>();
        returnNodes.add(startNode);
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

    @Override
    public String toString() {
        String returnString = "Friendship connection: ";
        StringBuilder sb = new StringBuilder(returnString);
        int i = 0;
        for (T e : getNodes()) {
            sb.append("[").append(e).append("] ");
            if (e != endNode) {
                sb.append(" -> "+ edges.get(i).getName() +", "+ edges.get(i).getWeight()+ " -> ");
            i++;
            }
        }
        sb.append("\nTotal score: " + getTotalWeight());
        return sb.toString();
    }
}
