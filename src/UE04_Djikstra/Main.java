package UE04_Djikstra;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {

           testBigCsv();
    }

    public static void testGraph() {
        //Abbildung 1 Normal Einlesen
        Graph graph = new Graph();
        graph.readGraphFromAdjacencyMatrixFile("src/UE04_Djikstra/res/Graph_A-H.csv");
        System.out.println(graph);

        // Mit A als Startpunkt
        graph.calcWithDijkstra("A");
        System.out.println("---------------------");
        System.out.println(graph);

        // Mit D als Startpunkt
        graph.calcWithDijkstra("D");
        System.out.println("---------------------");
        System.out.println(graph);


        //get All Path
        System.out.println("---------------------");
        Graph graph1 = new Graph();
        graph1.readGraphFromAdjacencyMatrixFile("src/UE04_Djikstra/res/Graph_A-H.csv");
        System.out.println(graph1.getAllPaths());

        System.out.println("---------------------");
        graph1.calcWithDijkstra("A");
        System.out.println(graph1.getAllPaths());


        System.out.println("---------------------");
        graph1.calcWithDijkstra("D");
        System.out.println(graph1.getAllPaths());
        //graph1.setStartNode(graph1.getNode("A"));
        //System.out.println(graph1.getAllPaths());
    }

    public static void testBigCsv() throws Exception {
        Graph graph = new Graph();
        graph.readGraphFromAdjacencyMatrixFile("src/UE04_Djikstra/res/big.csv");
        graph.calcWithDijkstra("n0");
        System.out.println(graph.getAllPaths());
    }

    public static void testBrokenGraphs() {
        Graph graphEmpty = new Graph();
        graphEmpty.readGraphFromAdjacencyMatrixFile(("res/dijkstra/empty.csv"));

        Graph graphA = new Graph();
        graphA.readGraphFromAdjacencyMatrixFile("src/UE04_Djikstra/res/kaputt_Graph_A-H_a.csv");

        Graph graphB = new Graph();
        graphB.readGraphFromAdjacencyMatrixFile("src/UE04_Djikstra/res/kaputt_Graph_A-H_b.csv");
    }
}
