package animation;

import objects.Robot;
import objects.Case;

public class Deplacement extends Evenement {
	private Robot robot;
	private String direction;

	public Deplacement(long date, Robot robot, String direction) {
		super(date);
		this.robot = robot;
		this.direction = direction;
	}

	@Override
	public void execute() {
		Case nouvellePosition;
		int lig = this.robot.getPosition().getLigne();
		int col = this.robot.getPosition().getColonne();
		switch (this.direction) {
		case "N":
			nouvellePosition = getCase(lig - 1, col);
			break;
		case "S":
			nouvellePosition = getCase(lig + 1, col);
			break;
		case "E":
			nouvellePosition = getCase(lig, col + 1);
			break;
		case "O":
			nouvellePosition = getCase(lig, col - 1);
			break;
		}
		this.robot.setPosition(nouvellePosition);
	}
}
