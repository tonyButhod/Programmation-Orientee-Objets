package strategie;

import objects.*;
import animation.*;
import exception.*;

import java.util.*;
import java.util.ListIterator;

/**
 * Classe abstraite implémentant un chef robot.
 * Elle possède la liste des incendies et des robots libres,
 * ainsi que le simulateur associé pour exécuter la stratégie.
 * Elle possède 2 principales fonctions abstraites :
 * executeStrategie et leaveAMessage.
 */
public abstract class ChefRobot {
    private List<Incendie> incendies = null;
    private List<Robot> robotsLibres = null;
	private Simulateur simu;

	/**
	 * Enregistre les paramètres nécessaires au chef robot,
	 * et met à jour le chef robot dans les attributs des robots
	 * et de la simulation.
	 *
	 * @param DS
	 * 		données de simulation.
	 * @param simu
	 * 		simulateur associé au chef robot.
	 */
    public ChefRobot(DonneesSimulation DS, Simulateur simu){
		this.simu = simu;
		this.simu.setChef(this);
		init(DS.getIncendies(), DS.getRobots());
	}

	public List<Incendie> getIncendies() {
		return incendies;
	}

	public List<Robot> getRobotsLibres() {
		return robotsLibres;
	}

	public void setRobotLibre(Robot robot) {
		this.robotsLibres.add(robot);
	}

	public Simulateur getSimu() {
		return this.simu;
	}

	/**
	 *	Initialise les paramètres du chef robot en fonction des données de base de la simulation.
	 *
	 * @param incendies
	 * 		liste des incendies initiaux
	 * @param robotsLibres
	 * 		liste des robots initiaux
	 */
	public void init(List<Incendie> incendies, List<Robot> robotsLibres) {
		this.incendies = incendies;
		//Il faut aussi dire aux robots qui est leur chef
		ListIterator<Robot> lr = robotsLibres.listIterator();
		while (lr.hasNext()) {
			lr.next().setChefRobot(this);
		}
		this.robotsLibres = new ArrayList<Robot>(robotsLibres);
	}

	/**
	 * Exécute la stratégie du chef robot.
	 */
	public abstract void executeStrategie();

	/**
	 * Avertit le chef robot que le robot r passé
	 * en argument a fini ses tâches.
	 *
	 * @param r
	 * 		robot de nouveau libre.
	 */
	public abstract void leaveAMessage(Robot r);
}