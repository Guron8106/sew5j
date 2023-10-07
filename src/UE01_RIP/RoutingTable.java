package UE01_RIP.RipObjects;

import UE01_RIP.RipObjects.Packets.Route;
import UE01_RIP.RipObjects.Subnet.IPAddress;
import UE01_RIP.RipObjects.Subnet.Subnet;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class RoutingTable {

    // RIP MAXIMAL HOP COUNT IS 15
    private static final int POISONED_NUMBER = 15;

    /**
     * Eine TreeMap mit dem Key Subnet und dem Value RoutingEntry
     * Routingeinträge!
     */
    TreeMap<Subnet, RoutingEntry> entries = new TreeMap<>();

    /**
     * fügt dem RoutingTable eine lokale Route zu
     * @param subnet SUbnet
     */
    public void addLocal(Subnet subnet) {
        entries.put(subnet,new RoutingEntry( 0, null));
    }

    /**
     * fügt dem RoutingTable eine Remote Route hinzu
     * @param subnet SUbnet
     * @param hopCount hopCount der Route
     * @param source Nachbar Router
     */
    public void addRemote(Subnet subnet, int hopCount, Router source) {
        entries.put(subnet,new RoutingEntry( hopCount, source));
    }

    /**
     * löscht einen Entry aus dem RoutingTable
     * @param routingEntry Entry
     */
    public void removeEntry(RoutingEntry routingEntry) {
        entries.values().removeIf(routingEntry::equals);
    }

    /**
     * Löscht alle Routen eines Nachbarns aus dem RoutingTable
     * @param router Nachbar Router
     */
    public void deleteAllRoutesOfNeighbor(Router router) {
        entries.values().removeIf(entry -> entry != null && entry.getNeighbour() != null && entry.getNeighbour().equals(router));
        ;
    }

    /**
     * Findet eine Route im RoutingTable
     * @param query Subnet nach dem gesucht wird
     * @return die Route
     */
    public RoutingEntry findInTable(Subnet query) {
        for (Entry<Subnet,RoutingEntry> entry : entries.entrySet()) {
            if (entry.getKey().equals(query)) return entry.getValue();
        }
        return null;
    }

    /**
     * kommt eine Route im RoutingTable vor
     * @param query Subnet nach dem gesucht wird
     * @return kommt vor oder nicht in boolean
     */
    public boolean containsSubnet(Subnet query) {
        return entries.keySet().stream().anyMatch(subnet -> subnet.equals(query));
    }

    /**
     * Das Paket von Routen, das vom Nachbar gesendet wurde, wird verarbeitet
     * @param routes ein HashSet mit Routen
     * @param source Nachbar-Router
     */
    public void convertToRoutingTable(HashSet<Route> routes, Router source){
        for(Route route : routes){
            RoutingEntry inTable = findInTable(route.getSubnet());

            /*
             Wenn der Hop-Count größer wird dann entferne das Entry und füge
             eine neue Route ein
             */
            if(route.getHopCount()>=POISONED_NUMBER){
                if(inTable!=null && inTable.getNeighbour().equals(source)) {
                    removeEntry(inTable);
                    addRemote(route.getSubnet(),route.getHopCount()+1,source);
                }
                continue;
            }
            // Wenn keine Route da, dann hinzufügen
            if(inTable == null) {
                addRemote(route.getSubnet(), route.getHopCount()+1, source);
                continue;
            }
            /*
             Die Route ersetzen
             */
            if(inTable.getHopCount() > route.getHopCount()){
                removeEntry(inTable);
                addRemote(route.getSubnet(),route.getHopCount()+1,source);
            }
        }
    }

    /**
     * Die Routen die für den Nachbarn bestimmt sind, werden in ein HashSet gepackt
     * @param router Nachbar-Router
     * @return HashSet mit den Routen
     */
    public HashSet<Route> getPacketFromTable(Router router) {
        return entries.entrySet().stream()
                .filter(entry -> entry.getValue().getNeighbour() == null || !entry.getValue().getNeighbour().equals(router))
                .map(entry -> new Route(entry.getKey(), entry.getValue().getHopCount()))
                .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Poisoned eine lokale Route
     * @param subnet Subnet
     */
    public void poisonLocal(Subnet subnet) {
        RoutingEntry entry = findInTable(subnet);

        if (entry != null && entry.getHopCount() == 0) entry.setHopCount(15);
        else throw new IllegalArgumentException("No such Subnet is locally connected.");
    }


    /**
     * returned alle Routen
     * @return TreeMap
     */
    public TreeMap<Subnet, RoutingEntry> getEntries() {
        return entries;
    }

    /**
     * toString Methode
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder erg = new StringBuilder("Routing Table: \n");
        for (Entry<Subnet,RoutingEntry> entry : entries.entrySet()) {
            erg.append(entry.getKey());
            erg.append(", ");
            erg.append(entry.getValue());
            erg.append('\n');
        }
        return erg.toString();
    }

    public Router findPathToIPAddress(IPAddress address) {
        for(Entry<Subnet, RoutingEntry> entry : entries.entrySet()){
            if(entry.getKey().contains(address)) return entry.getValue().getNeighbour();
        }
        return null;
    }

}
