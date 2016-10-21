package animation;

import objects.*;

public class ReservoirRempli extends Evenement {
	private RobotEau robot;

	public ReservoirRempli(long date, RobotEau robot) {
		super(date, robot);
	}

	@Override
	public void execute() {
		this.robot.setVolEau(this.robot.getVolEauMax());
		
	}
}
