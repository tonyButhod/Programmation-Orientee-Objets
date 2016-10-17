package objects;

public class Patte extends Robot {

	public Patte(Case position) {

		super(position);
		this.vitesse = 30;
		this.vitDever = 10.0;
	}

	public double getVitesse(NatureTerrain NT) {
		if (NT == NatureTerrain.ROCHE) {
			return 10.0;
		}
		return super.vitesse;
	}
	
	public String toString() {
		return ("Robot à pattes " + super.toString() + "\n");
	}
}