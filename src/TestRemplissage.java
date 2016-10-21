import gui.GUISimulator;
import io.LecteurDonnees;
import objects.*;
import animation.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;


public class TestRemplissage {
	public static void main(String args[]) {
		try {
            GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
            DonneesSimulation DS = LecteurDonnees.lire("cartes/carteSujet.map");
            Carte map = DS.getCarte();
            
            Simulateur aff = new Simulateur(gui, DS);
            Evenement dN = new Deplacement(10, DS.getRobots().get(1), Direction.NORD);
            Evenement dO1 = new Deplacement(20, DS.getRobots().get(1), Direction.OUEST);
            Evenement dO2 = new Deplacement(30, DS.getRobots().get(1), Direction.OUEST);
            
            if (DS.getRobots().get(1).peutSeRemplir()){
            
            System.out.println(DS.getRobots().get(1));
            Evenement remp = new RemplirReservoir(35, DS.getRobots().get(1),aff);
            
            System.out.println(DS.getRobots().get(1));
            Evenement dE1 = new Deplacement(35 + ((RobotEau)(DS.getRobots().get(1))).getTempsRemp(), DS.getRobots().get(1), Direction.EST);
            
            System.out.println(DS.getRobots().get(1));
            Evenement dE2 = new Deplacement(50, DS.getRobots().get(1), Direction.EST);

            aff.ajouteEvenement(dO2);
            
            aff.ajouteEvenement(dN);
            aff.ajouteEvenement(dO1);
            aff.ajouteEvenement(dE1);
            aff.ajouteEvenement(dE2);
            aff.ajouteEvenement(remp);
        } 
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (DataFormatException e) {
            System.out.println(e);
        }
	}
}
