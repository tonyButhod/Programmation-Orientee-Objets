package objects;

public class Patte extends Robot {

	public Patte(Case position, Carte carte) {

		super(position, carte);
		this.vitesse = 30;
		this.vitDever = 10.0;
	}

	public double getVitesse(NatureTerrain NT) {
		if (NT == NatureTerrain.ROCHE) {
			return 10.0;
		}
		else if (NT == NatureTerrain.EAU) {
			return -1;
		}
		return super.vitesse;
	}
	
	@Override
	public String toString() {
		return ("Robot à pattes " + super.toString() + "\n");
	}
}