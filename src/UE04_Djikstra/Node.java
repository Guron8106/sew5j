package u06_dijkstra.src;

import java.util.TreeSet;

/**
 * Node-Klasse für den Graphen
 *
 * @author Lorenz Bauer
 */
public class Node implements Comparable<Node> {

    /**
     * ID der Node
     */
    private final String id;

    /**
     * Tree-Set mit den Edges, die zu den Nachbar-Nodes zeigen
     */
    private final TreeSet<Edge> edges = new TreeSet<>();

    /**
     * Distanz zur Start-Node
     */
    private int distance = Integer.MAX_VALUE;

    /**
     * Parent-Node am Weg zur Start-Node
     */
    private Node parent;

    /**
     * Ob die Node schon angeschaut wurde
     */
    private boolean isVisited;

    /**
     * Konstruktor
     *
     * @param id ID der Node
     */
    public Node(String id) {
        this.id = id;
    }

    /**
     * Changes previous node and distance, if distance is smaller
     *
     * @param newParent neue Parent-Node
     * @param newDistance neuer Wert für die Distanz zur start-node
     * @return wurde etwas verändert
     */
    public boolean change(Node newParent, int newDistance) {
        if (newDistance > this.distance) return false;
        this.parent = newParent;
        this.distance = newDistance;
        return true;
    }

    /**
     * Getter für die ID der Node
     *
     * @return id
     */
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        if (this.distance == 0) {
            result.append(this.id).append(" ----> is the start node");
        } else {
            result.append(this.id).append(" [totalDistance: ");
            if (distance < Integer.MAX_VALUE) {
                result.append(distance);
            } else {
                result.append("?");
            }
            result.append("] ");
        }

        for (Edge edge : edges) {
            result.append(edge).append(", ");
        }

        if (result.length() > 2 && result.charAt(result.length() - 2) == ',') {
            result.setLength(result.length() - 2);
        }

        return result.toString();
    }


    /**
     * Der Pfad vom Start-Node bis zu diesem Node-Objekt
     *
     * @return Path as String
     */
    public String getPath() {
        if (parent != null) {
            return parent.getPath().replace(": is start node", "") + " --(" + this.distance + ")-> " + this.getId();
        } else if (distance == 0) {
            return this.getId() + ": is start node";
        }

        return "no path available for " + this;
    }

    /**
     * Ein neuer Edge wurde dem Node hinzugefügt
     *
     * @param node     Nachbar-Node
     * @param distance Distanz zum Nachbar
     */
    public void addEdge(Node node, int distance) {
        if (distance > 0) edges.add(new Edge(node, distance));
    }

    /**
     * Resettet den Node
     */
    public void init() {
        this.distance = Integer.MAX_VALUE;
        this.parent = null;
        this.isVisited = false;
    }

    /**
     * Setzt diesen Node auf besucht und fügt die Neighbors der PQ zu
     *
     * @param graph graph
     */
    public void visit(Graph graph) {
        edges.stream().filter(edge -> !edge.getNeighbor().isVisited).forEach(edge -> graph.offerDistance(edge.getNeighbor(), this, this.distance + edge.getDistance()));
        this.isVisited = true;
    }

    /**
     * Comparator Methode, vergleicht zwei Node Objekte
     * @param other the object to be compared.
     * @return -1, gewonnen, 0 gleich, 1 verloren
     */
    @Override
    public int compareTo(Node other) {
        int comp = Integer.compareUnsigned(this.distance, other.distance);

        if (comp != 0) {return comp;}
        return this.id.compareTo(other.getId());
    }

    /**
     * Sind zwei Node Objekte gleich
     * @param other the object to be tested.
     * @return true= gleich, false= ungleich
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Node node = (Node) other;
        return id.equals(node.id) && distance==node.distance;
    }

    /**
     * Setzt diese Node als Start-Node
     */
    public void setStartNode() {
        this.distance = 0;
        this.parent = null;
    }

    /**
     * Get-Distance (nur zum Testen benutzt)
     * @return distance
     */
    public int getDistance(){
        return distance;
    }

    /**
     * Get-Edges (nur zum Testen benutzt)
     * @return edges
     */
    public TreeSet<Edge> getEdges(){
        return edges;
    }

    public boolean getIsVisited(){
        return isVisited;
    }
}

