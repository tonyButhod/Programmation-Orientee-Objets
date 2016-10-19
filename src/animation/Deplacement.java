package animation;

import objects.Robot;
import objects.Case;
import objects.Carte;
import objects.Direction;

/** Classe fournissant l'implémentation des Deplacements
 *  La variable direction est du type enumere Direction
	*/
public class Deplacement extends Evenement {
	private Robot robot;
	private Direction direction;

	public Deplacement(long date, Robot robot, Direction direction) {
		super(date);
		this.robot = robot;
		this.direction = direction;
	}

	@Override
	public void execute() {
		Case nouvellePosition;
		int lig = this.robot.getPosition().getLigne();
		int col = this.robot.getPosition().getColonne();
		Carte map = this.robot.getCarte();
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
		this.robot.setPosition(nouvellePosition);
	}
}
