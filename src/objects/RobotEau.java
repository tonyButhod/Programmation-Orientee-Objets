package objects;

public abstract class RobotEau extends Robot {
	
	protected double volEau;
	protected double volEauMax;

	public RobotEau (Case position, Carte carte, int volEauMax){
		
		super(position, carte);
		setVolEau(0); //Par défault une robot est vide.
		this.volEauMax = volEauMax; //pas besoin de constructeur, non modifiable.

	}

	public void setVolEau(double volEau){
		
		if (volEau > this.volEauMax || volEau < 0.0) {
			throw new IllegalArgumentException("Volume d'eau incorrect !");
		} else {
			this.volEau = volEau;
		}
	}

    public abstract double getVitesse(NatureTerrain NT);

	public double getVolEau() {
		return this.volEau;
	}
	
	public double getVolEauMax(){
		return this.volEauMax;
	}
	
	
	protected boolean eauCote() { // verfifie aussi la case où se trouve le robot
		int ligne = this.getPosition().getLigne();
		int colonne = this.getPosition().getColonne();
		for (int i = ligne-1; i<=ligne+1; i++){
			for (int j= colonne-1; j<=colonne+1; j++){
				if (this.getCarte().getCase(i,j).getNature() == NatureTerrain.EAU){
					return true;
				}
			}
		}
		return false;
	}
	
}
