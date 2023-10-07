package UE01_RIP.RipObjects;

import UE01_RIP.RipObjects.Packets.Route;
import UE01_RIP.RipObjects.Subnet.IPAddress;
import UE01_RIP.RipObjects.Subnet.Subnet;

import java.util.HashSet;

public class Router {

    /**
     * Name des Routers als Text
     */
    private String name;

    /**
     * RoutingTable des Router Objekts
     */
    private final RoutingTable table = new RoutingTable();

    /**
     * Konstruktor des Router Objekts
     * @param name Name des Routers
     */
    public Router(String name){
        setName(name);
    }

    /**
     * Konstruktor des Router Objekts
     * @param name neuer Name des Routers
     */
    public Router(String name, Subnet... subnets){
        setName(name);
        for(Subnet subnet : subnets){
            addSubnet(subnet);
        }
    }

    /**
     * Setzt den Namen des Router-Objekts
     * @param name neuer Name des Routers
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Fügt ein neues, lokal verbundenes, Subnet zu 'table' hinzu
     * @param subnet lokal verbundenes Subnet
     */
    public void addSubnet(Subnet subnet){
        table.addLocal(subnet);
    }

    /**
     * toString Methode
     * @return Router ['name']
     */
    @Override
    public String toString(){
        return "Router [" + name + "]";
    }

    /**
     * Getter-Methode, welche das 'table' Objekt zurückliefert
     * @return (RoutingTable) table
     */
    public RoutingTable getTable(){
        return table;
    }


    /**
     * "Verpackt" die Routen die er dem Nachbarn 'router' geben würde
     * @param router Nachbar-Router
     * @return alle Routen die an den Nachbarn router versendet werden können
     */
    public HashSet<Route> receivePacket(Router router){
        return table.getPacketFromTable(router);
    }

    /**
     * Verarbeitet das Routen-Packet, welches er vom Nachbarn 'source' bekommen hat
     * @param routePackage HashSet von DistributedRoute Objekten
     * @param source Nachbar von dem die Routen kommen
     */
    public void receivePacket(HashSet<Route> routePackage, Router source){
        table.convertToRoutingTable(routePackage, source);
    }

    /**
     * löscht ein lokal verbundenes Subnet
     * @param subnet lokal verbundenes Subnet
     */
    public void deleteSubnet(Subnet subnet){
        table.poisonLocal(subnet);
    }

    /**
     * gibt den Namen zurück
     * @return (String) 'name'
     */
    public String getName() {
        return name;
    }

    /**
     * löscht alle Routen eines Nachbars
     * @param router Nachbar Router
     */
    public void deleteNeighbour(Router router){
        table.deleteAllRoutesOfNeighbor(router);
    }

    public void sendPacket(IPAddress destination){
        Router neighbor = table.findPathToIPAddress(destination);
        if(neighbor!=null){
            neighbor.sendPacket(destination);
            System.out.println("Packet sent to \"" + neighbor.getName() + "\"");
        }
    }
}
