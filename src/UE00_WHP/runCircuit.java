package UE00_WHP;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class runCircuit {
    protected final List<Components> components = new LinkedList<>();

    /**
     * Konstruktor
     * @param components Komponenten
     */
    public runCircuit(Components... components) {
        this.components.addAll(Arrays.asList(components));
    }




}
