package animation;

import java.util.*;

public class Simulateur {
    long dateSimulation;
    LinkedList<Evenement> evenements;

    public Simulateur() {
        this.dateSimulation = 0;
        this.evenements = new LinkedList<Evenement>();
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

    @Override
    public String toString() {
        String out = "Affichage de la liste triée : ";
        ListIterator<Evenement> le = evenements.listIterator();
        while (le.hasNext()) {
            out += le.next().getDate()+" ";
        }
        return out;
    }
}
