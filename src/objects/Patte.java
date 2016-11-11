package objects;

/**
 * Classe fournissant l'implémentation du type de robot à pattes.
 */
public class Patte extends Robot {
	
	/**
	 * Construit un robot à pattes à partir des paramètres d'un autre robot à
	 * pattes.
	 * 
	 * @param patte
	 *            robot dont les paramètres sont à utiliser
	 */
	public Patte(Patte patte) {
		super(patte);
	}

	/**
	 * Construit un robot à pattes. Sa vitesse ne peut être choisie par
	 * l'utilisateur et est automatiquement de 30m/s
	 * 
	 * @param position
	 *            case où se situe le robot
	 * @param carte
	 *            carte sur laquelle le robot se déplace
	 */
	public Patte(Case position, Carte carte) {
		super(position, carte, 0, 10.0, -1, 10.0, "pattes.png");
		this.vitesse = 30;
		//volEau = vitDever, restera inchangé car il ne se vide pas
		//volEau à -1 pour reconnaitre un robot à pattes
	}

	/**
	 * Implémentation de la méthode {@link Robot#getVitesse(NatureTerrain)} pour
	 * le robot à pattes
	 */
	public double getVitesse(NatureTerrain NT) {
		if (NT == NatureTerrain.ROCHE) {
			return 10.0;
		}
		else if (NT == NatureTerrain.EAU) {
			return -1;
		}
		return super.vitesse;
	}
	
	/**
	 * Implémentation de la méthode {@link Robot#copierRobot()} pour
	 * le robot à pattes
	 */
	public Robot copierRobot() {
		Patte patte = new Patte(this);
		return patte;
	}

	/**
	 * La méthode de test de la possibilité pour un robot de se remplir est
	 * redéfinie pour le robot à pattes. Il n'est pas censé l'utiliser, par
	 * sécurité celle-ci renvoie toujours faux.
	 */
	@Override
	public boolean peutSeRemplir(Case pos) {
		return false;
	}

	@Override
	public String toString() {
		return ("Robot à pattes " + super.toString() + "\n");
	}
}