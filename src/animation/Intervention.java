package animation;

import objects.Robot;
import objects.DonneesSimulation;

public class Intervention extends Evenement {
	Robot robot;

	public Intervention(long date, Robot robot) {
		super(date);
		this.robot = robot;
	}
	
	@Override
	public void execute(){
		//robot.derverserEau();
	}
}
