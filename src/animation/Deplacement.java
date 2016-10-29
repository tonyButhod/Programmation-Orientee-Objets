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
			lig -= 1;
			break;
		case SUD:
			lig += 1;
			break;
		case EST:
			col += 1;
			break;
		case OUEST:
			col -= 1;
			break;
		default:
			throw new IllegalArgumentException("Direction de déplacement non valide");
		}
		if (lig < 0 || lig > map.getNbLignes() - 1 || col < 0 || col > map.getNbColonnes() - 1) {
			throw new IllegalArgumentException("Deplacement impossible du robot sur cette case (Hors de la carte)");
		}
		nouvellePosition = map.getCase(lig, col);
		if (this.getRobot().getVitesse(nouvellePosition.getNature()) < 0) {
			throw new IllegalArgumentException("Deplacement impossible du robot sur cette case (Terrain non compatible)");
		}
		this.getRobot().setPosition(nouvellePosition);
	}
}
