
import gui.GUISimulator;
import io.LecteurDonnees;
import objects.*;
import animation.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestSimulateur {
    public static void main(String args[]) {
        try {
            GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
            DonneesSimulation DS = LecteurDonnees.lire("cartes/carteSujet.map");
            Simulateur simu = new Simulateur(gui, DS);
            Evenement e1 = new Intervention(4, null, null, simu);
            Evenement e2 = new Intervention(2, null, null, simu);
            Evenement e3 = new Intervention(3, null, null, simu);
            Evenement e4 = new Intervention(5, null, null, simu);
            Evenement e5 = new Intervention(1, null, null, simu);
            simu.ajouteEvenement(e1, simu.evenements);
            simu.ajouteEvenement(e5, simu.evenements);
            simu.ajouteEvenement(e2, simu.evenements);
            simu.ajouteEvenement(e3, simu.evenements);
            simu.ajouteEvenement(e4, simu.evenements);
            System.out.println(simu);
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (DataFormatException e) {
            System.out.println(e);
        }
    }
}
