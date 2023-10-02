package UE00_WHP;

/**
 * Cable
 *
 * @author Karanbir Guron
 */

public class Cable {

    /**
     * Überprüft ein Signal
     */
    private boolean isOn = false;

    /**
     * Getter für isON
     *
     * @return isOn
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Setter für isOn
     *
     * @param on turning it on
     */
    public void setOn(boolean on) {
        isOn = on;
    }

    /**
     * toString
     *
     * @return String
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" + "isOn=" + isOn + '}';
    }

}
