package objects;

public class Chenille extends RobotEau {

	public Chenille(Case position, Carte carte, double vitesse) {
		
		super(position, carte, 2000, 5*60);
		
		if (vitesse > 80 || vitesse < 0) {
			System.out.println("Vitesse trop élevée -> vitesse par défault");
			this.vitesse = 60;
		} else {
			this.vitesse = vitesse;
		}
		this.vitDever = 12.5;
		this.image += "chenille.png";
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
	
	public boolean peutSeRemplir(){
		return this.eauCote();
	}

	@Override
	public String toString(){
    		return ("Robot à chenilles " + super.toString() + 
				" \n temps remplissage : " + tempsRemp + 
				" \n volume dispo :" + volEau + 
				" \n sur " + volEauMax + "\n");
	}
}
