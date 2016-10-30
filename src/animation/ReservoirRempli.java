package animation;

import objects.*;

public class ReservoirRempli extends Evenement {

	public ReservoirRempli(long date, Robot robot) {
		super(date, robot);
	}

	@Override
	public void execute() {
		RobotEau robot1 = (RobotEau) this.getRobot();
		robot1.setVolEau(robot1.getVolEauMax());
		System.out.println(robot1);
		System.out.println("remplissage");
	}
}
