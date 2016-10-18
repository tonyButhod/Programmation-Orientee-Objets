package animation;

import objects.Robot;

public class RemplirReservoir extends Evenement {
	private Robot robot;
	
	public RemplirReservoir(long date, Robot robot){
		super(date);
		this.robot = robot;
	}

	@Override
	public void execute(){
		//robot.remplirReservoir();
	}
}
