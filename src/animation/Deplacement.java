package animation;

import objects.Robot;
import objects.Case;
import objects.Carte;
import objects.Direction;

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
	public void execute() {
		Case nouvellePosition;
		int lig = this.getRobot().getPosition().getLigne();
		int col = this.getRobot().getPosition().getColonne();
		Carte map = this.getRobot().getCarte();
		switch (this.direction) {
		case NORD:
			nouvellePosition = map.getCase(lig - 1, col);
			break;
		case SUD:
			nouvellePosition = map.getCase(lig + 1, col);
			break;
		case EST:
			nouvellePosition = map.getCase(lig, col + 1);
			break;
		case OUEST:
			nouvellePosition = map.getCase(lig, col - 1);
			break;
		default:
			throw new IllegalArgumentException("Direction de déplacement non valide");
		}
		this.getRobot().setPosition(nouvellePosition);
	}
}
