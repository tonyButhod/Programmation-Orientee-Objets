package strategie;

import objects.*;
import animation.*;

import java.util.*;
import java.util.ListIterator;

public abstract class ChefRobot {
    private List<Incendie> incendies = null;
    private List<Robot> robotsLibres = null;
    
    public ChefRobot(DonneesSimulation DS){
    	robotsLibres = new ArrayList<Robot>(DS.getRobots());
		this.incendies = DS.getIncendies();
	}

	public List<Incendie> getIncendies() {
		return incendies;
	}

	public List<Robot> getRobotsLibres() {
		return robotsLibres;
	}

	public void setRobotLibre(Robot robot) {
		this.robotsLibres.add(robot);
	}

	public void removeRobotLibre(Robot robot) {
		this.robotsLibres.remove(robot);
	}

	public abstract void executeStrategie();

	public void leaveAMessage(Robot r) {
		// Hi it's me, I just finished my work, kiss !

	}
}