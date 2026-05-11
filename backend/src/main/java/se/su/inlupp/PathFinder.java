package se.su.inlupp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface PathFinder<T> {

  Path<T> findPath(Graph<T> graph, T from, T to);


  //Detta är en metod för DFS!!!!!!!!!!!! BÖR NOG FLYTTAS
  default public void connect(T from, T to, Map<T, T> connections){
    connections.put(from, to);
    ListGraph<T> listGraph = new ListGraph<T>();
    Map<T, Set<Edge<T>>> graphMap = new HashMap<>(listGraph.getGraphMap());
    for (Edge<T> edge: graphMap.get(from)){
      T destination = edge.getDestination();
      if (!connections.containsKey(destination)){
        connect(destination, from, connections);
      }
    }
  }
}

