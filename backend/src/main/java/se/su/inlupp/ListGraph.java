package se.su.inlupp;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;

public class ListGraph<T> implements Graph<T> {

  private final Map<T, Set<EdgeClass<T>>> graphMap = new HashMap<>();

  @Override
  public void add(T node) {
    graphMap.putIfAbsent(node, new HashSet<EdgeClass<T>>());
    // throw new UnsupportedOperationException("Unimplemented method 'add'");
  }

  @Override
  public void remove(T node) {
    if (!graphMap.containsKey(node)) {
      throw new NoSuchElementException();
    }
    //Kanterna måste tas bort, även kanten som pekar mot T node måste bort.
    graphMap.remove(node);
  }

  @Override
  public boolean hasNode(T node) {
    return graphMap.containsKey(node);
  }

  @Override
  public void connect(T node1, T node2, String name, int weight) {
    if (weight < 0) {
      throw new IllegalArgumentException();
    }
    if (graphMap.get(node1).contains(node2) || graphMap.get(node2).contains(node1))
      throw new IllegalStateException();
    try {
      this.add(node1);
      this.add(node2);
    } catch (NoSuchElementException e) {
      e.printStackTrace();
    }
    Set<EdgeClass<T>> aEdges = graphMap.get(node1);
    Set<EdgeClass<T>> bEdges = graphMap.get(node2);

    aEdges.add(new EdgeClass<T>(node2, name, weight));
    bEdges.add(new EdgeClass<T>(node1, name, weight));
  }

  @Override
  public void disconnect(T node1, T node2) {
    if (!graphMap.containsKey(node1) || !graphMap.containsKey(node2)) {
      throw new NoSuchElementException();
    }

    if (!graphMap.get(node1).contains(node2) || !graphMap.get(node2).contains(node1)) {
      throw new IllegalStateException();
    }

    graphMap.get(node1).remove(node2);
    graphMap.get(node2).remove(node1);

    // throw new UnsupportedOperationException("Unimplemented method 'disconnect'");
  }

  @Override
  public void setConnectionWeight(T node1, T node2, int weight) {
    if (weight < 0) {
      throw new IllegalArgumentException();
    }
    if (!graphMap.get(node1).contains(node2) || !graphMap.get(node2).contains(node1) || (!graphMap.containsKey(node1)) || (!graphMap.containsKey(node2))) {
      throw new NoSuchElementException();
    }
    EdgeClass<T> returnEdge = getEdgeBetween(node1, node2);
    returnEdge.setWeight(weight);
    // throw new UnsupportedOperationException("Unimplemented method
    // 'setConnectionWeight'");
  }

  @Override
  public Set<T> getNodes() {
    Set<T> returnSet = new HashSet<T>(graphMap.keySet());
    return returnSet;
    //throw new UnsupportedOperationException("Unimplemented method 'getNodes'");
  }

  @Override
  public Collection<EdgeClass<T>> getEdgesFrom(T node) {
    if (!graphMap.containsKey(node)){
      throw new NoSuchElementException();
    }
    Collection <EdgeClass<T>> returnCollection = graphMap.get(node);
    return returnCollection;
    // throw new UnsupportedOperationException("Unimplemented method
    // 'getEdgesFrom'");
  }

  @Override
  public EdgeClass<T> getEdgeBetween(T node1, T node2) {
    if (!graphMap.containsKey(node1) || !graphMap.containsKey(node2)) {
      throw new NoSuchElementException();
    }
    Set<EdgeClass<T>> edgesFrom1 = graphMap.get(node1);
    for (EdgeClass<T> e : edgesFrom1) {
      if (e.getDestination().equals(node2)) {
        return e;
      }
    }
    return null;
  }

  @Override
  public Iterator<T> iterator() {
    throw new UnsupportedOperationException("Unimplemented method 'iterator'");
  }
}
