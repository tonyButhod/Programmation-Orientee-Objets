package objects;

public class Patte extends Robot {

	public Patte(Case position, Carte carte) {

		super(position, carte,0);
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
	
	public boolean peutSeRemplir(){
		return false;
	}
	
	@Override
	public boolean deverserEau(Incendie incendie) {
		double intensiteApresDever = incendie.getLitresEau() - this.getVitDever();
		if (intensiteApresDever <= 0.0) {
			incendie.setLitresEau(0.0);
			return true;
		}
		else {
			incendie.setLitresEau(intensiteApresDever);
			return false;
		}
	}
	
	@Override
	public String toString() {
		return ("Robot à pattes " + super.toString() + "\n");
	}
}