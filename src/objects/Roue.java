package objects;
/**
 * Classe fournissant l'implémentation du type de robot à roues.
 */
public class Roue extends Robot {

	/**
	 * Construit un robot à roues à partir des paramètres d'un autre robot à
	 * roues.
	 * 
	 * @param roue
	 *            robot dont les paramètres sont à utiliser
	 */
	public Roue(Roue roue) {
		super(roue);
	}

	/**
	 * Construit un robot à roues.
	 * 
	 * @param position
	 * 			case où se situe le robot
	 * @param carte
	 * 			carte sur laquelle le robot se déplace
	 * @param vitesse
	 * 			vitesse du robot (Doit être > 0, sinon vitesse par défaut (=80 m/s)
	 */
	public Roue(Case position, Carte carte, double vitesse) {
		super(position, carte, 10*60, 5000, 5000, 20.0, "R2D2.png");
		
		if (vitesse < 0) {
			System.out.println("Vitesse invalide -> vitesse par défault");
			super.vitesse = 80;
		} else {
			super.vitesse = vitesse;
		}
	}

	/**
	 * Implémentation de la méthode {@link Robot#getVitesse(NatureTerrain)} pour
	 * le robot à roues
	 */
	public double getVitesse(NatureTerrain NT) {
		if (NT == NatureTerrain.TERRAIN_LIBRE || NT == NatureTerrain.HABITAT) {
			return super.vitesse;
		}
		else {
			// Le robot à roues ne peut pas se déplacer sur de la forêt ou du rocher.
			return -1;
		}
	}

	/**
	 * Implémentation de la méthode {@link Robot#copierRobot()} pour
	 * le robot à roues
	 */
	public Robot copierRobot(){
		Roue roue = new Roue(this);
		return roue;
	}

	@Override
	public String toString() {
		return ("Robot à roues " + super.toString() +
				" \n temps remplissage : " + this.getTempsRemp() + 
				" \n volume dispo :" + this.getVolEau() + 
				" \n sur " + this.getVolEauMax() + "\n");
	}
}
