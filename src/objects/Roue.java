package objects;

public class Roue extends RobotEau {

	public Roue(Case position, Carte carte, double vitesse) {

		super(position, carte, 5000);
		
		if (vitesse < 0) {
			System.out.println("Vitesse invalide -> vitesse par défault");
			this.vitesse = 80;
		} else {
			this.vitesse = vitesse;
		}
		this.vitDever = 20.0;
		this.vitRemp = (double) 1 / 12;
	}

	public double getVitesse(NatureTerrain NT) {
		return super.vitesse;
	}

	@Override
	public String toString() {
		return ("Robot à roues " + super.toString() + " \n vitesse remplissage : " + vitRemp + " \n volume dispo :"
				+ volEau + " \n sur " + volEauMax + "\n");
	}
}
