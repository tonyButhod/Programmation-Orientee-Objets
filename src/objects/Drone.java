package objects;

public class Drone extends RobotEau {
	
	public Drone(Case position, Carte carte, double vitesse) {
		
		super(position, carte, 10000);
		
		if (vitesse > 150 || vitesse <0){
			System.out.println("Vitesse trop élevée -> vitesse par défault");
			this.vitesse = 100;
		}else{
			this.vitesse= vitesse;
		}
		this.vitDever = (double) 1/3 *1000;

		this.tempsRemp = 30*60; //+1 pour être juste après le fin du remplissage
	}

	public double getVitesse(NatureTerrain NT) {
			return super.vitesse;
	}

	public boolean peutSeRemplir(){
		return (this.getPosition().getNature() == NatureTerrain.EAU);
	}

	
	@Override	
	public String toString(){
		return ("Drone " + super.toString() + 
				" \n temps remplissage : " + tempsRemp + 
				" \n volume dispo :" + volEau +
    			" \n sur " + volEauMax + "\n");
	}

}
