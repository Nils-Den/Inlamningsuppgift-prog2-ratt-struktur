package se.su.inlupp;

public class BFSPathFinder<T> implements PathFinder<T> {

  @Override
  public Path<T> findPath(Graph<T> graph, T from, T to) {
    throw new UnsupportedOperationException("Unimplemented method 'findPath'");
  }
}

    //ISAKS KOD NEDAN!!!!
 @Override
  public List<Edge> getPathBFS(City from, City to) {
    Map<City, City> connections = new HashMap<>();
    connections.put(from, null);
    LinkedList<City> queue = new LinkedList<>();

    queue.add(from);

    while (!queue.isEmpty()) {
      City current = queue.poll();
      for (Edge edge : cities.get(current)) {
        City next = edge.getDestination();
        if (!connections.containsKey(next)) {
          connections.put(next, current);
          queue.add(next);
        }
      }
    }

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



