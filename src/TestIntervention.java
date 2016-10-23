import gui.GUISimulator;

import io.LecteurDonnees;
import objects.*;
import animation.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestIntervention {
	public static void main(String args[]) {
		try {
            GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
            DonneesSimulation DS = LecteurDonnees.lire("cartes/carteSujet.map");
            Carte map = DS.getCarte();
            
            Simulateur aff = new Simulateur(gui, DS);
            Evenement dN = new Deplacement(10, DS.getRobots().get(1), Direction.NORD);

            aff.ajouteEvenement(dN);
            
//            Incendie incendie = null;
//            for(Incendie incendieCarte:DS.getIncendies()) {
//            	if (DS.getRobots().get(1).getPosition().equals(incendieCarte.getPosition())) {
//            		incendie = incendieCarte;
//            	}
//            }
//            
//            if (incendie == null) {
//            	throw new IllegalArgumentException("Aucun incendie sous le robot");
			// }
			Case feu = null;
			Incendie incendie = null;
			feu = map.getCase(DS.getRobots().get(1).getPosition().getLigne() - 1,
					DS.getRobots().get(1).getPosition().getColonne());
			for (Incendie incendieCarte : DS.getIncendies()) {
				if (feu.equals(incendieCarte.getPosition())) {
					incendie = incendieCarte;
				}
			}
            
            Evenement interFeu = new Intervention(40, DS.getRobots().get(1), incendie, aff);
            aff.ajouteEvenement(interFeu);
        } 
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
        catch (DataFormatException e) {
            System.out.println(e);
        }
	}
}
