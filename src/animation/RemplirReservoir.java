package animation;

import objects.*;
import exception.*;

/** Classe permettant de remplir le réservoir d'un robot.
 *  Cet évènement commence le remplissage du robot.
 *
 *  Remarque : cette classe extend la classe Evenement.
 */
public class RemplirReservoir extends Evenement {
	private Simulateur simu;

	/**
	 * Enregistre les paramètres passés en argument liés au remplissage.
	 *
	 * @param date
	 * 		date d'exécution de l'évènement intervention.
	 * @param robot
	 * 		robot associé à l'intervention.
	 * @param simu
	 * 		simulateur qui exécute le remplissage.
	 */
	public RemplirReservoir(long date, Robot robot, Simulateur simu) {
		super(date, robot);
		this.simu = simu;
	}

	/**
	 * Redéfinition de la fonction execute.
	 * Vérifie que le robot peut se remplir.
	 * Si c'est le cas, elle indique que le robot est occupé pendant tout le temps du remplissage
	 * et ajoute un évènement de fin de remplissage.
	 * Dans le cas contraire, elle renvoie une exception.
	 *
	 * @throws ExecutionEvenementException
	 */
	@Override
	public void execute()throws ExecutionEvenementException {
		Robot robot = this.getRobot();
		Case position = robot.getPosition();
		long temps = robot.getTempsRemp(); // calcul temps de remplissage.
		if (robot.peutSeRemplir(position)) {
			//robot disponible après avoir complètement rempli son reservoir
			Evenement rempli = new ReservoirRempli(this.simu.getDateSimu() + temps,this.getRobot());
			robot.setDateOccupe(this.simu.getDateSimu() + temps);
			this.simu.ajouteEvenement(rempli);
		}else{
			throw new ExecutionEvenementException("Remplissage", "Remplissage impossible (position non compatible)");
		}
	}
}