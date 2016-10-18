
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
            Evenement e1 = new Intervention(4, null);
            Evenement e2 = new Intervention(2, null);
            Evenement e3 = new Intervention(3, null);
            Evenement e4 = new Intervention(5, null);
            Evenement e5 = new Intervention(1, null);
            simu.ajouteEvenement(e1);
            simu.ajouteEvenement(e5);
            simu.ajouteEvenement(e2);
            simu.ajouteEvenement(e3);
            simu.ajouteEvenement(e4);
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
