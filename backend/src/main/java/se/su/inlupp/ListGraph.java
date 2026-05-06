package se.su.inlupp;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;

public class ListGraph<T> implements Graph<T> {

  private final Map <T, Set<EdgeClass<T>>> graphMap = new HashMap <>();
 
  @Override
  public void add(T node) {
    graphMap.putIfAbsent(node, new HashSet<EdgeClass<T>>());
    //throw new UnsupportedOperationException("Unimplemented method 'add'");
  }

  @Override
  public void remove(T node) { 
    try {
      if(graphMap.containsKey(node)){
      graphMap.remove(node);
    }
    } catch (NoSuchElementException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean hasNode(T node) {
    return graphMap.containsKey(node);
    //throw new UnsupportedOperationException("Unimplemented method 'hasNode'");
  }

  @Override
  public void connect(T node1, T node2, String name, int weight) {
    
    throw new UnsupportedOperationException("Unimplemented method 'connect'");
  }

  @Override
  public void disconnect(T node1, T node2) {
    throw new UnsupportedOperationException("Unimplemented method 'disconnect'");
  }

  @Override
  public void setConnectionWeight(T node1, T node2, int weight) {
    throw new UnsupportedOperationException("Unimplemented method 'setConnectionWeight'");
  }

  @Override
  public Set<T> getNodes() {
    throw new UnsupportedOperationException("Unimplemented method 'getNodes'");
  }

  @Override
  public Collection<Edge<T>> getEdgesFrom(T node) {
    throw new UnsupportedOperationException("Unimplemented method 'getEdgesFrom'");
  }

  @Override
  public Edge<T> getEdgeBetween(T node1, T node2) {
    throw new UnsupportedOperationException("Unimplemented method 'getEdgeBetween'");
  }

  @Override
  public Iterator<T> iterator() {
    throw new UnsupportedOperationException("Unimplemented method 'iterator'");
  }
}

