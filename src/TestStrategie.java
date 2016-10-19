
import animation.*;
import gui.GUISimulator;
import io.LecteurDonnees;
import objects.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestStrategie {
    public static void main(String args[]) {
        try {
            GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
            DonneesSimulation DS = LecteurDonnees.lire("cartes/spiralOfMadness-50x50.map");
            System.out.println("Taille des cases : " + DS.getCarte().getTailleCases());
            objects.Robot robot = DS.getRobots().get(0);
            System.out.println("Position robot : " + robot.getPosition());
            Case end1 = DS.getCarte().getCase(0,9);
            Case end2 = DS.getCarte().getCase(4,12);
            long temps1 = Strategie.tempsMin(robot, end1);
            long temps2 = Strategie.tempsMin(robot, end2);
            System.out.println("Il atteint (0,9) en : " + temps1);
            System.out.println("Il atteint (4,12) en : " + temps2);
            Simulateur simu = new Simulateur(gui, DS);
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (DataFormatException e) {
            System.out.println(e);
        }
    }
}