package objects;

public class Patte extends Robot {

	public Patte(Case position, Carte carte) {

		super(position, carte,0);
		this.vitesse = 30;
		this.vitDever = 10.0;
		this.image += "pattes.png";
		super.volEau = super.vitDever; //Il restera inchangé car il ne se vide pas
		super.volEauMax = -1; //Permet de reconnaitre un robot à patte
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

	//Par sécurité, mais est normalement inutile
	@Override
	public boolean peutSeRemplir() {
		return false;
	}

	@Override
	public String toString() {
		return ("Robot à pattes " + super.toString() + "\n");
	}
}