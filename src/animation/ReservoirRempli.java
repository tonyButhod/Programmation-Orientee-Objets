package animation;

import exception.ExecutionEvenementException;
import objects.*;

/** Classe mettant fin au remplissage d'un robot.
 *
 *  Remarque : cette classe extend la classe Evenement.
 */
public class ReservoirRempli extends Evenement {

	/**
	 * Enregistre les paramètres passés en argument liés à un déplacement.
	 *
	 * @param date
	 * 		date d'exécution de l'évènement intervention.
	 * @param robot
	 * 		robot associé à l'intervention.
	 */
	public ReservoirRempli(long date, Robot robot) {
		super(date, robot);
	}

	/**
	 * Redéfinition de la fonction execute.
	 * Met fin au remplissage d'un robot en mettant à jour son attribut volEau (=volEauMax),
	 * et avertit le chefRobot qu'il a fini et donc qu'il est libre.
	 *
	 * @throws ExecutionEvenementException
	 */
	@Override
	public void execute()throws ExecutionEvenementException {
		Robot robot = this.getRobot();
		if (robot.getVolEauMax() != -1) {
			robot.setVolEau(robot.getVolEauMax());
		}
		else {
			throw new ExecutionEvenementException("Remplissage", "Remplissage impossible (robot à patte)");
		}
		//System.out.println(robot);
		//Le robot avertit le chef qu'il a fini son travail
		if (robot.getChefRobot() != null){
			robot.getChefRobot().leaveAMessage(robot);
		}
	}
}
