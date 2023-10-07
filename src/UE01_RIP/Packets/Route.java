package UE01_RIP.Packets;

import UE01_RIP.Subnet.Subnet;

public class Route {

    /**
     * Subnet der Route
     */
    public Subnet subnet;

    /**
     * hopCount der Route
     */
    public int hopCount;

    /**
     * Konstruktor der Klasse DistributedRoute
     * @param subnet Subnet
     * @param hopCount hopCount
     */
    public Route(Subnet subnet, int hopCount){
        this.subnet = subnet;
        this.hopCount = hopCount;
    }

    /**
     * Getter-Methode für 'hopCount'
     * @return 'hopCount'
     */
    public int getHopCount() {
        return hopCount;
    }

    /**
     * Getter-Methode für 'subnet'
     * @return 'subnet'
     */
    public Subnet getSubnet() {
        return subnet;
    }

    /**
     * Equals Methode der DistributedRoute Klasse
     * @param o anderes DistributedRoute Objekt
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (hopCount != route.hopCount) return false;
        return subnet.equals(route.subnet);
    }

    /**
     * HashCode Methode der DistributedRoute Klasse
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return 0;
    }
}
