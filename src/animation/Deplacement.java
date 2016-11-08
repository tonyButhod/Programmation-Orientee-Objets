package animation;

import objects.Robot;
import objects.Case;
import objects.Carte;
import objects.Direction;

import exception.*;

/** Classe fournissant l'implémentation des Deplacements
 *  La variable direction est du type enumere Direction
	*/
public class Deplacement extends Evenement {
	private Direction direction;

	public Deplacement(long date, Robot robot, Direction direction) {
		super(date, robot);
		this.direction = direction;
	}

	@Override
	public void execute() throws ExecutionEvenementException {
		Carte map = this.getRobot().getCarte();
		Robot r = this.getRobot();
		Case pos = r.getPosition();
		if (map.voisinExiste(pos, direction) && r.getVitesse(pos.getNature())>0) {
			r.setPosition(map.getVoisin(pos, direction));
		}
		else {
			throw new ExecutionEvenementException("Deplacement", "Deplacement impossible sur la case");
		}
	}
}
