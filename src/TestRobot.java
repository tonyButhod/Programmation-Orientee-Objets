import objects.*;
 
public class TestRobot {
	public static void main(String args[]) {
		
		Case position = new Case (30, 60, NatureTerrain.FORET);
		
		Drone drone1 = new Drone(110, position);
		System.out.println(drone1);
		
		Drone drone2 = new Drone(100, position);
		System.out.println(drone2);
		
		Patte patte1 = new Patte(position);
		System.out.println(patte1);
		
		Chenille chenille1 = new Chenille(110, position);
		System.out.println(chenille1);
		
		Chenille chenille2 = new Chenille(50, position);
		System.out.println(chenille2);
		
		Roue roue1 = new Roue(110, position);
		System.out.println(roue1);
		
		Roue roue2 = new Roue(50, position);
		System.out.println(roue2);
		//Robot robot1 = new Robot(position);  Ne marche pas (classe abstraite)
		//System.out.println(robot1);
	}
}
