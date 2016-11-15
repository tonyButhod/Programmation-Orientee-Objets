package animation;

import objects.Robot;
import objects.Case;
import objects.Carte;
import objects.Direction;

import exception.*;

/** Classe fournissant l'implémentation des Deplacements
 *  Cet évènement déplace le robot à la date donnée dans la direction
 *  indiquée, enregistrée dans l'attribut privé direction.
 *
 *  Remarque : cette classe extend la classe Evenement.
 */
public class Deplacement extends Evenement {
	private Direction direction;

	/**
	 * Enregistre les paramètres passés en argument liés à un déplacement.
	 *
	 * @param date
	 * 		date d'exécution de l'évènement intervention.
	 * @param robot
	 * 		robot associé à l'intervention.
	 * @param direction
	 * 		direction dans laquelle déplacer le robot.
	 */
	public Deplacement(long date, Robot robot, Direction direction) {
		super(date, robot);
		this.direction = direction;
	}

	/**
	 * Redéfinition de la fonction execute.
	 * Déplace le robot associé dans la direction souhaitée, tout en vérifiant que cette
	 * direction est valide (tient compte des caractéristiques du robot et de la nature
	 * de la case).
	 * Si le déplacement n'est pas possible, cette fonction fait remonter une exception.
	 *
	 * @throws ExecutionEvenementException
	 */
	@Override
	public void execute() throws ExecutionEvenementException {
		Carte map = this.getRobot().getCarte();
		Robot r = this.getRobot();
		Case pos = r.getPosition();
		if (map.voisinExiste(pos, direction)) {
			Case newpos = map.getVoisin(pos, direction);
			if (r.getVitesse(newpos.getNature()) > 0) { //On teste la possibilité d'aller sur la nouvelle case qui existe
				r.setPosition(map.getVoisin(pos, direction));
			} else {
				throw new ExecutionEvenementException("Deplacement", "Deplacement impossible sur la case");
			}
		} else {
			throw new ExecutionEvenementException("Deplacement", "Deplacement impossible sur la case (en dehors de la carte)");
		}
	}
}
