package strategie;

import objects.*;
import animation.*;

import java.util.*;
import java.util.ListIterator;

public abstract class ChefRobot {
    private List<Incendie> incendies = null;
    private List<Robot> robotsLibres = null;
	private Simulateur simu;
    
    public ChefRobot(DonneesSimulation DS, Simulateur simu){
    	robotsLibres = new ArrayList<Robot>(DS.getRobots());
		this.incendies = DS.getIncendies();
		this.simu = simu;
		//Il faut aussi dire aux robots qui est leur chef
		ListIterator<Robot> lr = robotsLibres.listIterator();
		while (lr.hasNext()) {
			lr.next().setChefRobot(this);
		}
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

	public Simulateur getSimu() {
		return this.simu;
	}

	public void removeRobotLibre(Robot robot) {
		this.robotsLibres.remove(robot);
	}

	public abstract void executeStrategie();

	public abstract void leaveAMessage(Robot r);
}