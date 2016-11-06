package objects;

public class Roue extends Robot {

	public Roue(Case position, Carte carte, double vitesse) {

		super(position, carte, 10); //10*60
		
		if (vitesse < 0) {
			System.out.println("Vitesse invalide -> vitesse par défault");
			super.vitesse = 80;
		} else {
			super.vitesse = vitesse;
		}
		super.vitDever = 20.0;
		super.volEauMax = 5000;
	}

	public double getVitesse(NatureTerrain NT) {
		if (NT == NatureTerrain.TERRAIN_LIBRE || NT == NatureTerrain.HABITAT) {
			return super.vitesse;
		}
		else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return ("Robot à roues " + super.toString() + " \n temps remplissage : " + tempsRemp + " \n volume dispo :"
				+ volEau + " \n sur " + volEauMax + "\n");
	}
}
