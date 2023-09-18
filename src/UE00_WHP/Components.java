package UE00_WHP;

import java.util.HashMap;
import java.util.Map;

public abstract class Components {


    boolean[] interfaces;

    /**
     * Inputs and Outputs
     */
    protected final Map<String, Cable> values = new HashMap<String, Cable>();

    /**
     * Constructor
     * @param interfaces is the amount of interfaces
     */
    public Components(int interfaces) {
        this.interfaces = new boolean[interfaces];
    }

    public abstract void pull();

    /**
     * Inputs verarbeiten und Ausgänge setzen
     */
    public abstract void calc();

    /**
     * Getter für Input Zustände
     * @param input um den index anzugeben vom input
     * @return bool
     */
    public boolean getInputs(int input) {
        return interfaces[input];
    }

    /**
     * toString method
     * @return String
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" + values + '}';
    }
}
