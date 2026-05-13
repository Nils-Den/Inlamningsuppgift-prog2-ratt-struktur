//PROG2 VT2026, Inlämningsuppgift
//Grupp 58
//Nils Denward nide8018
//Erika Lundblad erlu6715
//Nellie Åkerström neak7375
package se.su.inlupp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DFSPathFinder<T> implements PathFinder<T> {

  @Override
  public Path<T> findPath(Graph<T> graph, T from, T to) {
    Map<T, T> connections = new HashMap<>();
    connect(graph, from, null, connections);
    LinkedList<Edge<T>> path = new LinkedList<>();
      
    T current = to;

    while (current != null && !current.equals(from)){
      T next = connections.get(current);
      if(current == null || next == null){
        return null;
      }
      Edge<T> edge = graph.getEdgeBetween(next, current);
      path.addFirst(edge);
      current = next;

    }
    List<Edge<T>> pathSet = new ArrayList<Edge<T>>(path);
    Path<T> returnPath = new PathClass<T>(from, to, pathSet);
    return returnPath;
  }


  public void connect(Graph<T> graph, T from, T to, Map<T, T> connections){
    connections.put(from, to);
    for (Edge<T> edge: graph.getEdgesFrom(from)){
      T destination = edge.getDestination();
      if (!connections.containsKey(destination)){
        connect(graph, destination, from, connections);
      }
    }
  }

}