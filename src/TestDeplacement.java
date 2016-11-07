import gui.GUISimulator;

import io.LecteurDonnees;
import objects.*;
import animation.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;


public class TestDeplacement {
	public static void main(String args[]) {
		try {
            GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
            DonneesSimulation DS = LecteurDonnees.lire("cartes/carteSujet.map");
            Carte map = DS.getCarte();
            
            Simulateur aff = new Simulateur(gui, DS);
            Evenement dN = new Deplacement(10, DS.getRobots().get(1), Direction.NORD);
            Evenement dO1 = new Deplacement(20, DS.getRobots().get(1), Direction.OUEST);
            Evenement dO2 = new Deplacement(30, DS.getRobots().get(1), Direction.OUEST);
            Evenement dE1 = new Deplacement(40, DS.getRobots().get(1), Direction.EST);
            Evenement dE2 = new Deplacement(50, DS.getRobots().get(1), Direction.EST);
            Evenement dE3 = new Deplacement(60, DS.getRobots().get(1), Direction.EST);
            Evenement dE4 = new Deplacement(70, DS.getRobots().get(1), Direction.EST);
            Evenement dE5 = new Deplacement(80, DS.getRobots().get(1), Direction.EST);
            Evenement dS = new Deplacement(90, DS.getRobots().get(1), Direction.SUD);

            aff.ajouteEvenement(dO2);
            
            aff.ajouteEvenement(dN);
            aff.ajouteEvenement(dO1);
            aff.ajouteEvenement(dE1);
            aff.ajouteEvenement(dE2);
            aff.ajouteEvenement(dE3);
            aff.ajouteEvenement(dE4);
            aff.ajouteEvenement(dE5);
            aff.ajouteEvenement(dS);
        } 
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (DataFormatException e) {
            System.out.println(e);
        }
	}
}
