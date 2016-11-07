package strategie;

import objects.*;
import animation.*;

import java.util.*;
import java.util.ListIterator;

public abstract class ChefRobot {
    protected List<Incendie> incendies = null;
    protected List<Robot> robotsLibres = null;
    
    public ChefRobot(DonneesSimulation DS){
    	
    	ListIterator<Robot> le = DS.getRobots().listIterator();
    	for (Robot r:this.robotsLibres){
			this.robotsLibres.add(r);
		}
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

}