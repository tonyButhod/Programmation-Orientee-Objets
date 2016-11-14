import gui.GUISimulator;
import java.awt.Color;
import io.LecteurDonnees;
import objects.*;
import animation.*;
import strategie.*;
import exception.*;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestChefRobot {
	

	public static void main(String args[]) {
		try{
			
			GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
			DonneesSimulation DS = null;
			if (args.length == 0) {
				DS = LecteurDonnees.lire("cartes/carteSujet.map");
			}
			else {
				DS = LecteurDonnees.lire(args[0]);
			}

			Simulateur simu = new Simulateur(gui, DS);
			ChefRobot chef = new ChefRobotSimple(DS, simu);

			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (DataFormatException e) {
			System.out.println(e);
		}
	}

}
