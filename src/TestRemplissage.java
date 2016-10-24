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
			long temps = DS.getRobots().get(1).getTempsRemp();

			Simulateur aff = new Simulateur(gui, DS);
			
			Evenement dN = new Deplacement(10, DS.getRobots().get(1), Direction.NORD);
			aff.ajouteEvenement(dN);
			
			Evenement dO1 = new Deplacement(20, DS.getRobots().get(1), Direction.OUEST);
			aff.ajouteEvenement(dO1);
			
			Evenement dO2 = new Deplacement(30, DS.getRobots().get(1), Direction.OUEST);
			aff.ajouteEvenement(dO2);
	
			
			Evenement remp = new RemplirReservoir(35, DS.getRobots().get(1), aff);
			aff.ajouteEvenement(remp);
		
			
			Evenement dE1 = new Deplacement(35 + temps +1, DS.getRobots().get(1), Direction.EST);
			aff.ajouteEvenement(dE1);
	

			//Evenement dE2 = new Deplacement(DS.getRobots().get(1).getDateOccupe() + 5, DS.getRobots().get(1), Direction.EST);
			//aff.ajouteEvenement(dE2);

	
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (DataFormatException e) {
			System.out.println(e);
		}
	}
}
