package objects;

public class Roue extends RobotEau {

	public Roue(Case position, Carte carte, double vitesse) {

		super(position, carte, 5000, 10); //10*60
		
		if (vitesse < 0) {
			System.out.println("Vitesse invalide -> vitesse par défault");
			this.vitesse = 80;
		} else {
			this.vitesse = vitesse;
		}
		this.vitDever = 20.0;
	}

	public double getVitesse(NatureTerrain NT) {
		if (NT == NatureTerrain.TERRAIN_LIBRE || NT == NatureTerrain.HABITAT) {
			return super.vitesse;
		}
		else {
			return -1;
		}
	}
	
	public boolean peutSeRemplir(){
		return this.eauCote();
	}

	@Override
	public String toString() {
		return ("Robot à roues " + super.toString() + " \n temps remplissage : " + tempsRemp + " \n volume dispo :"
				+ volEau + " \n sur " + volEauMax + "\n");
	}
}
