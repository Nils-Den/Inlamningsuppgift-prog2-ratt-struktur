package se.su.inlupp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class DFSPathFinder<T> implements PathFinder<T> {

  @Override
  public Path<T> findPath(Graph<T> graph, T from, T to) {
    Map<T, T> connections = new HashMap<>();
    connect(from, null, connections);
    LinkedList<Edge<T>> path = new LinkedList<>();
    
    
    T current = to;
    //ListGraph<T> listGraph = new ListGraph<>();
    while (current != null && !current.equals(from)){
      T next = connections.get(current);
      Edge<T> edge = graph.getEdgeBetween(next, current);
      path.addFirst(edge);
      current = next;

    }
    Set<Edge<T>> pathSet = new HashSet<Edge<T>>(path);
    Path<T> returnPath = new PathClass<T>(from, to, pathSet);
    return returnPath;
  }
}

/* 
//ISAKS KOD NEDAN!!!!!!
 @Override
  public List<Edge> getPathDFS(City from, City to) {
    Map<City, City> connections = new HashMap<>();
    connect(from, null, connections);
    LinkedList<Edge> path = new LinkedList<>();

    City current = to;
    while (current != null && !current.equals(from)) {
      City next = connections.get(current);
      // During the lecture I flip the next, current here:
      // We want the "back"-link, from next to current
      // and not the forward link
      Edge edge = getEdgeBetween(next, current);
      path.addFirst(edge);
      current = next;
    }

    return path;
  }
*/