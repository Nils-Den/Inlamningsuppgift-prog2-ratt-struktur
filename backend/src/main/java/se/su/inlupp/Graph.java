//PROG2 VT2026, Inlämningsuppgift
//Grupp 58
//Nils Denward nide8018
//Erika Lundblad erlu6715
//Nellie Åkerström neak7375
package se.su.inlupp;

import java.util.Collection;
import java.util.Set;

public interface Graph<T> extends Iterable<T> {

  void add(T node);

  void remove(T node);

  boolean hasNode(T node);

  void connect(T node1, T node2, String name, int weight);

  void disconnect(T node1, T node2);

  void setConnectionWeight(T node1, T node2, int weight);

  Set<T> getNodes();

  Collection<Edge<T>> getEdgesFrom(T node);

  Edge<T> getEdgeBetween(T node1, T node2);
}

