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

    /**
     * Run
     */
    public void run() {
        System.out.println(components);
        Taster t1 = (Taster) components.get(0);
        Taster t2 = (Taster) components.get(1);
        t1.press();
        System.out.println("T1 clicked");
        t2.press();
        System.out.println("T2 clicked");

        t1.press();
        System.out.println("T1 clicked");

        System.out.println("T2 clicked");
        t2.press();

    }





}
