package objects;

public class Drone extends Robot {
	
	public Drone(Drone drone){
		super(drone);
	}
	
	public Drone(Case position, Carte carte, double vitesse) {
		super(position, carte, 30*60, 10000, 10000, 334, "eve.png");
		
		if (vitesse > 150 || vitesse <0){
			System.out.println("Vitesse trop élevée -> vitesse par défault");
			super.vitesse = 100;
		}else{
			super.vitesse= vitesse;
		}
	}

	public double getVitesse(NatureTerrain NT) {
			return super.vitesse;
	}

	public Robot copierRobot(){
		Drone drone = new Drone(this);
		return drone;
	}
	
	@Override
	public boolean peutSeRemplir(Case pos){
		return (pos.getNature() == NatureTerrain.EAU);
	}

	
	@Override	
	public String toString(){
		return ("Drone " + super.toString() + 
				" \n temps remplissage : " + tempsRemp + 
				" \n volume dispo :" + volEau +
    			" \n sur " + volEauMax + "\n");
	}

}
