package objects;

/**
 * Classe fournissant l'implémentation du type de robot à chenilles.
 */
public class Chenille extends Robot {
	
	/**
	 * Construit un robot à chenilles à partir des paramètres d'un autre robot à
	 * chenilles.
	 * 
	 * @param chenille
	 *            robot dont les paramètres sont à utiliser
	 */
	public Chenille(Chenille chenille){
		super(chenille);
	}

	/**
	 * Construit un robot à chenilles.
	 * 
	 * @param position
	 * 			case où se situe le robot
	 * @param carte
	 * 			carte sur laquelle le robot se déplace
	 * @param vitesse
	 * 			vitesse du robot (Doit être > 0 et < 80, sinon vitesse par défaut (=60 m/s)
	 */
	public Chenille(Case position, Carte carte, double vitesse) {
		super(position, carte, 5*60, 2000, 2000, 12.5, "WallE.png");
		
		if (vitesse > 80 || vitesse < 0) {
			System.out.println("Vitesse trop élevée -> vitesse par défault");
			super.vitesse = 60;
		} else {
			super.vitesse = vitesse;
		}
	}

	/**
	 * Implémentation de la méthode {@link Robot#getVitesse(NatureTerrain)} pour
	 * le robot à chenilles
	 */
	public double getVitesse(NatureTerrain NT) {
		if (NT == NatureTerrain.FORET) {
			return 0.5 * super.vitesse;
		} else if (NT == NatureTerrain.EAU || NT == NatureTerrain.ROCHE) {
			return -1;
		}
		return super.vitesse;
	}

	/**
	 * Implémentation de la méthode {@link Robot#copierRobot()} pour
	 * le robot à chenilles
	 */
	public Robot copierRobot(){
		Chenille chenille = new Chenille(this);
		return chenille;
	}

	@Override
	public String toString(){
    		return ("Robot à chenilles " + super.toString() + 
				" \n temps remplissage : " + this.getTempsRemp() + 
				" \n volume dispo :" + this.getVolEau() + 
				" \n sur " + this.getVolEauMax() + "\n");
	}
}
