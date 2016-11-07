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
            
            //Le robot utilisé est un robot à roue, qui peut se remplir car étant un RobotEau
            objects.Robot robot = DS.getRobots().get(1);
            
            Evenement dN = new Deplacement(10, robot, Direction.NORD);

            aff.ajouteEvenement(dN);
            
            //On remplit le robot artificiellement, pour tester seulement l'extinction du feu
            robot.setVolEau(robot.getVolEauMax());
            
			Case feu = null;
			Incendie incendie = null;
			feu = map.getCase(robot.getPosition().getLigne() - 1,
					robot.getPosition().getColonne());
			for (Incendie incendieCarte : DS.getIncendies()) {
				if (feu.equals(incendieCarte.getPosition())) {
					incendie = incendieCarte;
				}
			}
			
            //Pour les besoins du test on réduit aussi l'intensité de l'incendie
            //Afin de pouvoir l'éteindre avce juste le robot à roue
			incendie.setLitresEau(6000.0);
			
			System.out.println("Le robot " + robot.toString() + " va intervenir sur l'incendie " + incendie.toString());
            
            Evenement interFeu = new Intervention(40, robot, incendie, aff);
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
