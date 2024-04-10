package u06_dijkstra.src;

/**
 * Edge class die ein Node zu einem benachbarten Node verbindet
 *
 * @author Lorenz Bauer
 */
public class Edge implements Comparable<Edge> {

    /**
     * Die Distanz zum Nachbarn
     */
    private final int distance;

    /**
     * Der Nachbar-Node
     */
    private final Node neighbor;

    /**
     * Konstruktor
     *
     * @param neighbor der Nachbar-Node
     * @param distance die Distanz zum Nachbarn
     */
    public Edge(Node neighbor, int distance) {
        this.distance = distance;
        this.neighbor = neighbor;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.neighbor.getId().compareTo(edge.getNeighbor().getId());
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Edge edge = (Edge) other;
        return this.getNeighbor().equals(edge.getNeighbor()) && this.getDistance()==edge.getDistance();
    }

    @Override
    public String toString() {
        return neighbor.getId() + ":" + distance;
    }

    /**
     * Getter-Methode für den Nachbar-Node
     *
     * @return neighbor
     */
    public Node getNeighbor() {
        return this.neighbor;
    }

    /**
     * Getter-Methode für die Distanz
     *
     * @return distance
     */
    public int getDistance() {
        return this.distance;
    }
}
