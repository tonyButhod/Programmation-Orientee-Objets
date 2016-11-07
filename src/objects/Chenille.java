package objects;

public class Chenille extends Robot {

	public Chenille(Case position, Carte carte, double vitesse) {
		
		super(position, carte, 5*60);
		
		if (vitesse > 80 || vitesse < 0) {
			System.out.println("Vitesse trop élevée -> vitesse par défault");
			super.vitesse = 60;
		} else {
			super.vitesse = vitesse;
		}
		this.image += "chenille.png";
		super.vitDever = 12.5;
		super.volEauMax = 2000;
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

	@Override
	public String toString(){
    		return ("Robot à chenilles " + super.toString() + 
				" \n temps remplissage : " + tempsRemp + 
				" \n volume dispo :" + volEau + 
				" \n sur " + volEauMax + "\n");
	}
}
