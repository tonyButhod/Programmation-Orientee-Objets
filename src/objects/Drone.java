package objects;

public class Drone extends RobotEau {
	
	public Drone(double vitesse, Case position) {
		
		super(position, 10000);
		
		if (vitesse > 150 || vitesse <0){
			System.out.println("Vitesse trop élevée -> vitesse par défault");
			this.vitesse = 100;
		}else{
			this.vitesse= vitesse;
		}
		this.vitDever = (double) 1/3 *1000;
		this.vitRemp = 0.5555;
		
	}

	public double getVitesse(NatureTerrain NT) {
			return super.vitesse;
	}
	@Override	
	public String toString(){
		return ("Drone " + super.toString() + 
				" \n vitesse remplissage : " + vitRemp + 
				" \n volume dispo :" + volEau +
    			" \n sur " + volEauMax + "\n");
	}

}
