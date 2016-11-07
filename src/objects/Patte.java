package objects;

public class Patte extends Robot {
	
	public Patte(Patte patte){
		super(patte);
	}

	public Patte(Case position, Carte carte) {
		super(position, carte, 0, 10.0, -1, 10.0, "pattes.png");
		this.vitesse = 30;
		//volEau = vitDever, restera inchangé car il ne se vide pas
		//volEau à -1 pour reconnaitre un robot à patte
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
	
	public Robot copierRobot(){
		Patte patte = new Patte(this);
		return patte;
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