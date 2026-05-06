public class PathClass implements Path {
    private T startNode;
    private T endNode;
    private final Set<Edge<T>> edges;

    public Path(T startNode, T endNode, Set<Edge<T>> edges){
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
        for (Edge e : edges) {
            value += e.getWeight();
        }
        return value;

    }

    public List<Edge<T>> getEdges() {
        // DENNA METOD ÄR EJ KLAR. BEHÖVER RETURNERA EDGES I ORDNING FRÅN START TILL
        // SLUT.
        List<Edge<T>> returnEdges = new ArrayList<Edge<T>>();
        returnEdges.addAll(edges);
        return returnEdges;
    }

    public List<T> getNodes() {
        //Kolla över denna igen!!!!
        List<T> returnNodes = new ArrayList<T>();
        for (Edge e : edges) {
            returnNodes.add(e.getDestination);
        }
        return returnNodes;
    }
}
