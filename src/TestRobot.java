import objects.*;
 
public class TestRobot {
	public static void main(String args[]) {
		
		Case position = new Case (30, 60, NatureTerrain.FORET);
		Carte map = new Carte (10,10,30);
		
		Drone drone1 = new Drone(position, map, 110);
		System.out.println(drone1);
		
		Drone drone2 = new Drone(position, map, 100);
		System.out.println(drone2);
		
		Patte patte1 = new Patte(position, map);
		System.out.println(patte1);
		
		Chenille chenille1 = new Chenille(position, map, 110);
		System.out.println(chenille1);
		
		Chenille chenille2 = new Chenille(position, map, 50);
		System.out.println(chenille2);
		
		Roue roue1 = new Roue(position, map, 100);
		System.out.println(roue1);
		
		Roue roue2 = new Roue(position, map, 50);
		System.out.println(roue2);
		double vitesse = drone1.getVitesse(NatureTerrain.FORET);
		System.out.println(vitesse);
		//Robot robot1 = new Robot(position);  Ne marche pas (classe abstraite)
		//System.out.println(robot1);
	}
}
