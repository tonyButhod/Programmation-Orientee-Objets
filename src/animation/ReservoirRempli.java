package animation;

import objects.*;

public class ReservoirRempli extends Evenement {

	public ReservoirRempli(long date, Robot robot) {
		super(date, robot);
	}

	@Override
	public void execute() {
		Robot robot = this.getRobot();
		if (robot.getVolEauMax() != -1) {
			robot.setVolEau(robot.getVolEauMax());
		}
		else {
			System.out.println("Oh oh, on essaye de remplir un robot à patte.");
		}
		System.out.println(robot);
	}
}
