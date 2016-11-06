package objects;

public class Drone extends Robot {
	
	public Drone(Case position, Carte carte, double vitesse) {
		
		super(position, carte, 30*60);
		
		if (vitesse > 150 || vitesse <0){
			System.out.println("Vitesse trop élevée -> vitesse par défault");
			super.vitesse = 100;
		}else{
			super.vitesse= vitesse;
		}
		super.vitDever = (double) 1/3 *1000;
		super.volEauMax = 10000;
	}

	public double getVitesse(NatureTerrain NT) {
			return super.vitesse;
	}

	@Override
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
