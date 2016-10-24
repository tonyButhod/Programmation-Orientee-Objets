package objects;

public abstract class RobotEau extends Robot {

	protected double volEau;
	protected double volEauMax;

	public RobotEau(Case position, Carte carte, int volEauMax, long tempsRemp) {

		super(position, carte, tempsRemp);
		setVolEau(0); // Par défault une robot est vide.
		this.volEauMax = volEauMax; // pas besoin de constructeur, non
		// modifiable.

	}

	public void setVolEau(double volEau) {

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

	public double getVolEauMax() {
		return this.volEauMax;
	}

	// Deverse le maximum d'eau que le rebot peut sur l'incendie
	// Sauf si ce dernier a une intensité moindre
	// Retourne vrai si le feu est eteint, faux sinon
	@Override
	public boolean deverserEau(Incendie incendie) {
		// On diminue l'intensité de l'incindie du maximum que le robot peut
		// deverser en une unité de
		// temps (vitesse de déversement ou réservoir vidé)
		double intensiteApresDever = incendie.getLitresEau() - java.lang.Math.min(this.getVitDever(), this.getVolEau());
		// Si l'incendie est éteint il est possible qu'on ait mit "trop" d'eau
		// On passe donc son intensité à 0 pour ne pas avoir une intensite
		// negative
		if (intensiteApresDever <= 0.0) {
			this.volEau -= incendie.getLitresEau();
			incendie.setLitresEau(0.0);
			return true;
		}
		else {
			this.volEau -= java.lang.Math.min(this.getVitDever(), this.getVolEau());
			incendie.setLitresEau(intensiteApresDever);
			//System.out.println("J'ai déversé " + java.lang.Math.min(this.getVitDever(), this.getVolEau()) + " litres d'eau");
			return false;
		}
	}

	protected boolean eauCote() { // verfifie aussi la case où se trouve le
									// robot
		int ligne = this.getPosition().getLigne();
		int colonne = this.getPosition().getColonne();
		for (int i = ligne - 1; i <= ligne + 1; i++) {
			for (int j = colonne - 1; j <= colonne + 1; j++) {
				System.out.println("ok");
				if (this.getCarte().getCase(i, j).getNature() == NatureTerrain.EAU) {
					System.out.println("vrai");
					return true;
				}
			}
		}
		System.out.println("faux");
		return false;
	}

}
