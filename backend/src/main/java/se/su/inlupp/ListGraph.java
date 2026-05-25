//PROG2 VT2026, Inlämningsuppgift
//Grupp 58
//Nils Denward nide8018
//Erika Lundblad erlu6715
//Nellie Åkerström neak7375
package se.su.inlupp;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;

public class ListGraph<T> implements Graph<T> {

  private final Map<T, Set<Edge<T>>> graphMap = new HashMap<>();

  @Override
  public void add(T node) {
    graphMap.putIfAbsent(node, new HashSet<Edge<T>>());
  }

  @Override
  public void remove(T node) {
    if (!graphMap.containsKey(node)) {
      throw new NoSuchElementException();
    }
    for (Set<Edge<T>> e : graphMap.values()) {
      e.removeIf(edges -> edges.getDestination().equals(node));

    }
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
    if (getEdgeBetween(node1, node2) != null)

      throw new IllegalStateException();
    try {
      this.add(node1);
      this.add(node2);
    } catch (NoSuchElementException e) {
      e.printStackTrace();
    }
    Set<Edge<T>> aEdges = graphMap.get(node1);
    Set<Edge<T>> bEdges = graphMap.get(node2);

    aEdges.add(new EdgeClass<T>(node2, name, weight));
    bEdges.add(new EdgeClass<T>(node1, name, weight));
  }

  @Override
  public void disconnect(T node1, T node2) {
    if (!graphMap.containsKey(node1) || !graphMap.containsKey(node2)) {
      throw new NoSuchElementException();
    }

    Edge<T> e1 = getEdgeBetween(node1, node2);
    Edge<T> e2 = getEdgeBetween(node2, node1);

    if (e1 == null || e2 == null) {
      throw new IllegalStateException();
    }
    graphMap.get(node1).remove(e1);
    graphMap.get(node2).remove(e2);
  }

  @Override
  public void setConnectionWeight(T node1, T node2, int weight) {
    if (weight < 0) {
      throw new IllegalArgumentException();
    }
    if ((!graphMap.containsKey(node1)) || (!graphMap.containsKey(node2))) {
      throw new NoSuchElementException();
    }
    Edge<T> returnEdge1 = getEdgeBetween(node1, node2);
    if (returnEdge1 == null) {
      throw new NoSuchElementException();
    }
    Edge<T> returnEdge2 = getEdgeBetween(node2, node1);
    if (returnEdge2 == null) {
      throw new NoSuchElementException();
    }
    returnEdge1.setWeight(weight);
    returnEdge2.setWeight(weight);
  }

  @Override
  public Set<T> getNodes() {
    Set<T> returnSet = new HashSet<T>(graphMap.keySet());
    return returnSet;
  }

  @Override
  public Collection<Edge<T>> getEdgesFrom(T node) {
    if (!graphMap.containsKey(node)) {
      throw new NoSuchElementException();
    }
    Collection<Edge<T>> returnCollection = new HashSet<>(graphMap.get(node));
    return returnCollection;
  }

  @Override
  public Edge<T> getEdgeBetween(T node1, T node2) {
    if (!graphMap.containsKey(node1) || !graphMap.containsKey(node2)) {
      throw new NoSuchElementException();
    }
    Set<Edge<T>> edgesFrom1 = graphMap.get(node1);
    for (Edge<T> e : edgesFrom1) {
      if (e.getDestination().equals(node2)) {
        return e;
      }
    }
    return null;
  }

    public T getPerson(String name){
      Set<T> returnSet = getNodes();
      for (T t: returnSet){
        if (((Person)t).getName().equals(name)){
          return (T)t;
        }
      }

      return null;
    }

  @Override
  public Iterator<T> iterator() {
    Iterator<T> iter = getNodes().iterator();
    return iter;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<T, Set<Edge<T>>> kv : graphMap.entrySet()) {
      sb.append(kv.getKey()).append(": ").append(kv.getValue());
      sb.append("\n");
    }
    return sb.toString();
  }
}
