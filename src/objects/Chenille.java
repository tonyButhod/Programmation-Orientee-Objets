package objects;

public class Chenille extends Robot {
	
	public Chenille(Chenille chenille){
		super(chenille);
	}

	public Chenille(Case position, Carte carte, double vitesse) {
		super(position, carte, 5*60, 0, 2000, 12.5, "chenille.png");
		
		if (vitesse > 80 || vitesse < 0) {
			System.out.println("Vitesse trop élevée -> vitesse par défault");
			super.vitesse = 60;
		} else {
			super.vitesse = vitesse;
		}
	}

	public double getVitesse(NatureTerrain NT) {
		if (NT == NatureTerrain.FORET) {
			return 0.5 * super.vitesse;
		}
		else if (NT == NatureTerrain.EAU || NT == NatureTerrain.ROCHE) {
			return -1;
		}
		return super.vitesse;
	}
	
	public Robot copierRobot(){
		Chenille chenille = new Chenille(this);
		return chenille;
	}

	@Override
	public String toString(){
    		return ("Robot à chenilles " + super.toString() + 
				" \n temps remplissage : " + tempsRemp + 
				" \n volume dispo :" + volEau + 
				" \n sur " + volEauMax + "\n");
	}
}
