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
        String returnString = "Start node: " + startNode + " End node: " + endNode + "\n";
        StringBuilder sb = new StringBuilder(returnString);
        for(T e : getNodes()){
            if(e != startNode || e != endNode){
               sb.append(e).append(", ");  
            }
        }
        sb.append("\n" + "Total weight: " + getTotalWeight());
        return sb.toString();
    }
}
