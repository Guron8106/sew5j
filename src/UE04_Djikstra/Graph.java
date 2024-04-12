package UE04_Djikstra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Graph Klasse, berechnet liest CSV-Dateien ein und läuft den Dijkstra Algorithmus über die Daten
 * @author Karanbir Guron
 */
public class Graph implements IOfferDistance {

    /**
     * Priority Queue mit den zu besuchenden Nodes
     */
    private final PriorityQueue<Node> pq = new PriorityQueue<>();

    /**
     * ArrayList mit den ganzen Nodes
     */
    private final ArrayList<Node> nodes = new ArrayList<>();

    /**
     * Liest eine CSV Datei ein und speist die Daten in 'nodes' ein
     *
     * @param input Pfad zur File
     */
    public void readGraphFromAdjacencyMatrixFile(String input) {
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            int counter = 0;

            while ((line = br.readLine()) != null) {
                if (line.startsWith(";")) {
                    nodes.addAll(Arrays.stream(line.split(";"))
                            .filter(n -> !n.isEmpty())
                            .map(Node::new)
                            .collect(Collectors.toList()));
                } else {
                    String[] daten = line.split(";", -1);

                    if (daten.length != nodes.size() + 1) {
                        throw new IllegalArgumentException("Zeile hat nicht die richtige Anzahl von Argumenten");
                    }

                    Node node = nodes.stream()
                            .filter(n -> n.getId().equals(daten[0]))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Node Namen passen nicht"));

                    if (!nodes.get(counter).equals(node)) {
                        throw new IllegalArgumentException("Node Reihenfolge passt nicht");
                    }

                    for (int i = 0; i < daten.length - 1; i++) {
                        String distString = daten[i + 1];
                        int distance = 0;
                        if (!distString.isEmpty()) {
                            try {
                                distance = Integer.parseInt(distString);
                            } catch (NumberFormatException e) {
                                throw new IllegalArgumentException("Distanz ist kein Integer");
                            }
                        }
                        nodes.get(i).addEdge(node, distance);
                    }
                    counter++;
                }
            }
            if (counter != nodes.size()) {
                throw new IllegalArgumentException("Matrix hat nicht die gleiche Anzahl von Zeilen und Spalten");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Fehler beim Lesen der Matrix");
        }
    }


    /**
     * Gibt alle Pfade aus
     *
     * @return alle Pfade als String
     */
    public String getAllPaths() {
        StringBuilder result = new StringBuilder();
        for (Node node : nodes) {
            result.append(node.getPath());
            result.append("\n");
        }
        return result.substring(0, result.length() - 1);
    }

    /**
     * Von dieser Node aus wird der Dijkstra Algorithmus ausgeführt werden
     * @param startNodeId die Node von der, der Algorithmus ausgeführt werden soll
     */
    public void calcWithDijkstra(String startNodeId) {
        this.init();
        setStartNode(getNode(startNodeId));
        while (!pq.isEmpty()) {
            pq.poll().visit(this);
        }
    }

    /**
     * setzt 'startNode' als Startpunkt für den Dijkstra Algorithmus
     * @param startNode die Node von der, der Algorithmus ausgeführt werden soll
     */
    public void setStartNode(Node startNode){
        startNode.setStartNode();
        pq.add(startNode);
    }

    /**
     * to String Methode
     * @return toString Darestellung des Graphs
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Node node : nodes) {
            result.append(node);
            result.append("\n");
        }
        return result.substring(0, result.length() - 1);
    }

    /**
     * Interface um bei Nodes den besten Nachbarn auszuwählen
     * @param node Node die geändert werden soll
     * @param newParent möglich besserer Nachbar
     * @param newDistance möglich bessere Distanz
     */
    @Override
    public void offerDistance(Node node, Node newParent, int newDistance) {
        if (node.change(newParent, newDistance)){
            pq.remove(node);
            pq.add(node);
        }
    }

    /**
     * Alle Nodes werden neuinitialisiert (bei calcWithDijkstra benötigt)
     */
    public void init() {
        nodes.forEach(Node::init);
    }

    /**
     * Get-Node (nur zum Testen benutzt)
     * @param ID Node-ID
     *
     * @return node
     */
    public Node getNode(String ID) {
        Node node;
        try {
            node = nodes.stream()
                    .filter(n -> n.getId().equals(ID))
                    .findFirst()
                    .orElseThrow();
        } catch (Exception e) {
            throw new IllegalArgumentException("Die Node wurde nicht gefunden");
        }
        return node;
    }

    /**
     * Get-Nodes (nur zum Testen benutzt)
     *
     * @return nodes
     */
    public ArrayList<Node> getNodes(){
        return nodes;
    }
}

