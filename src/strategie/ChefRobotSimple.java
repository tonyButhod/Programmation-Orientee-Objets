package strategie;

import objects.*;
import animation.*;

import java.util.ListIterator;

public class ChefRobotSimple extends ChefRobot {
	
	public ChefRobotSimple(DonneesSimulation DS, Simulateur simu){
		super(DS, simu);
	}

	public void executeStrategie(){
		if (super.getRobotsLibres().size() == 0) {
			//Garantit qu'il y ai au moins un robot de libre
			return;
		}
		ListIterator<Incendie> li = super.getIncendies().listIterator();
		while(li.hasNext()) {
			Incendie fire = li.next();
			Case posFire = fire.getPosition();
			ListIterator<Robot> lr = super.getRobotsLibres().listIterator();
			Robot robotMin = lr.next(); //Garanti par la première condition
			long tpsMin = Dijkstra.tempsMin(robotMin, posFire);
			while (lr.hasNext()) {
				Robot r = lr.next();
				long tps = Dijkstra.tempsMin(r, posFire);
				if (tps < tpsMin) {
					robotMin = r;
					tpsMin = tps;
				}
			}
			//Ici, on possède le robot le plus proche de l'incendie et on lui dit d'y aller
			getRobotsLibres().remove(robotMin);
			robotMin.intervient(getSimu(), fire);
		}
	}

	public void leaveAMessage(Robot r) {
		//Fonction appelée lorsque le robot a fini sont travail
		//getRobotsLibres().add(r);
	}
}
