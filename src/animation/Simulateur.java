package animation;

import java.util.*;

public class Simulateur {
    long dateSimulation;
    LinkedList<Evenement> evenements;

    public Simulateur() {
        this.dateSimulation = 0;
    }

    public void ajouteEvenement(Evenement e) {
        ListIterator<Evenement> le = evenements.listIterator();
        while (le.hasNext() && le.next().getDate()<e.getDate());
        if (le.hasPrevious()) {
            le.previous();
        }
        le.add(e);
    }

    public void incrementeDate() {
        dateSimulation++;
    }

    public boolean simulationTerminee() {
        if (evenements.size() == 0) {
            return true;
        }
        return false;
    }
}
