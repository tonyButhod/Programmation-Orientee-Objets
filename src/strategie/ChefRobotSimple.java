package strategie;

import objects.*;
import animation.*;

import java.util.ListIterator;

/**
 * Classe extend ChefRobot.
 * Permet de définir une certaine stratégie pour éteindre tous les
 * feux, et définit l'action à exécuter lorsqu'un robot a terminé.
 */
public class ChefRobotSimple extends ChefRobot {

	/**
	 * Enregistre les paramètres de base de chefRobot.
	 *
	 * @param DS
	 * 		données de la simulation
	 * 	@param simu
	 * 		simulateur
	 */
	public ChefRobotSimple(DonneesSimulation DS, Simulateur simu){
		super(DS, simu);
	}

	/**
	 * Définition de la stratégie liée à ce chef robot.
	 * Stratégie : tant qu'il y a un feu, elle calcule le
	 * robot le plus proche de ce feu et lui dit d'intervenir sur ce feu.
	 * S'il n'y a pas de robot libre, elle ne fait rien.
	 */
	public void executeStrategie() {
		if (super.getRobotsLibres().size() == 0) {
			// Garantit qu'il y ai au moins un robot de libre
			return;
		}
		ListIterator<Incendie> li = super.getIncendies().listIterator();
		ListIterator<Robot> lr=null;
		while (li.hasNext() && super.getRobotsLibres().size() != 0) {
			Incendie fire = li.next();
			Case posFire = fire.getPosition();
			lr = super.getRobotsLibres().listIterator();
			Robot robotMin = lr.next(); // Garanti par la première condition
			long tpsMin = Dijkstra.tempsMin(robotMin, posFire);
			while (lr.hasNext()) {
				Robot r = lr.next();
				long tps = Dijkstra.tempsMin(r, posFire);
				if (tps < tpsMin) {
					robotMin = r;
					tpsMin = tps;
				}
			}
			// Ici, on possède le robot le plus proche de l'incendie et on
			// lui dit d'y aller
			getRobotsLibres().remove(robotMin);
			robotMin.intervient(getSimu(), fire);
		}
	}

	/**
	 * Ajoute le robot r à la liste de robots libres.
	 *
	 * @param r
	 * 		robot libre.
	 */
	public void leaveAMessage(Robot r) {
		this.setRobotLibre(r);
	}
}
