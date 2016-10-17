package objects;

public class Chenille extends RobotEau {

	public Chenille(double vitesse, Case position) {
		
		super(position, 2000);
		
		if (vitesse > 80 || vitesse < 0) {
			System.out.println("Vitesse trop élevée -> vitesse par défault");
			this.vitesse = 60;
		} else {
			this.vitesse = vitesse;
		}
		this.vitDever = 12.5;
		this.vitRemp = (double) 2 / 3 * 10;

	}

	public double getVitesse(NatureTerrain NT) {
		if (NT == NatureTerrain.FORET) {
			return 0.5 * super.vitesse;
		}
		return super.vitesse;
	}

	public String toString(){
    		return ("Robot à chenilles " + super.toString() + 
				" \n vitesse remplissage : " + vitRemp + 
				" \n volume dispo :" + volEau + 
				" \n sur " + volEauMax + "\n");
	}
}
