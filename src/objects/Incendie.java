package objects;

/**
 * Classe fournissant l'implémentation des incendies. L'attribut litresEau
 * correspond à l'intensité de l'incendie, c'est-à-dire le nombre de litres
 * d'eau nécessaires à son extinction. Invariant de classe : litresEau >= 0
 * -private une position
 */

public class Incendie {
	private Case position;
	private double litresEau;

	public double getLitresEau() {
		return this.litresEau;
	}

	public void setLitresEau(double ltr) {
		if (ltr < 0.0) {
			throw new IllegalArgumentException(
					"Invariant sur les litres d'eau nécessaires pour un incendie non respecte (> 0)");
		}
		this.litresEau = ltr;
	}

	public Case getPosition() {
		return this.position;
	}

	public void setPosition(Case pos) {
		this.position = pos;
	}

	/**
	 * Crée un incendie.
	 * 
	 * @param pos
	 *            case où se situe l'incendie
	 * @param ltr
	 *            litres d'eau nécessaires à son extinction
	 */
	public Incendie(Case pos, double ltr) {
		this.position = pos;
		setLitresEau(ltr);
	}

	/**
	 * Copie l'incendie sur lequel la méthode est instanciée.
	 * 
	 * @return incenie un incendie de même position et intensité que celui
	 *         d'instanciation
	 */
	public Incendie copierIncendie() {
		Incendie incendie = new Incendie(this.position, this.getLitresEau());
		return incendie;
	}

	@Override
	public String toString() {
		return new String(
				"Feu à la case " + this.position.toString() + ", necessite " + this.getLitresEau() + "L d'eau");
	}
}
