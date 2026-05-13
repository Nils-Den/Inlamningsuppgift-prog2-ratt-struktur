//PROG2 VT2026, Inlämningsuppgift
//Grupp 58
//Nils Denward nide8018
//Erika Lundblad erlu6715
//Nellie Åkerström neak7375
package se.su.inlupp;
public interface PathFinder<T> {

  Path<T> findPath(Graph<T> graph, T from, T to);
}

