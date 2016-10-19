package animation;

import objects.*;

public class ReservoirRempli extends Evenement {
	private Robot robot;

	public ReservoirRempli(long date, RobotEau robot) {
		super(date);
		this.robot = robot;
	}

	@Override
	public void execute() {
		this.setVolEau(this.getVolEauMax());
		
	}
