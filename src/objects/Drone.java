package objects;

/**
 * Classe fournissant l'implémentation du type de drone.
 */
public class Drone extends Robot {
	
	/**
	 * Construit un drone à partir des paramètres d'un autre drone.
	 * 
	 * @param drone
	 *            robot dont les paramètres sont à utiliser
	 */
	public Drone(Drone drone){
		super(drone);
	}
	/**
	 * Construit un drone.
	 * 
	 * @param position
	 * 			case où se situe le robot
	 * @param carte
	 * 			carte sur laquelle le robot se déplace
	 * @param vitesse
	 * 			vitesse du robot (Doit être > 0 et < 150, sinon vitesse par défaut (=100 m/s)
	 */
	public Drone(Case position, Carte carte, double vitesse) {
		super(position, carte, 30*60, 10000, 10000, 334, "eve.png");
		
		if (vitesse > 150 || vitesse <0){
			System.out.println("Vitesse trop élevée -> vitesse par défault");
			super.vitesse = 100;
		}else{
			super.vitesse= vitesse;
		}
	}

	/**
	 * Implémentation de la méthode {@link Robot#getVitesse(NatureTerrain)} pour
	 * le drone
	 */
	public double getVitesse(NatureTerrain NT) {
			return super.vitesse;
	}

	/**
	 * Implémentation de la méthode {@link Robot#copierRobot()} pour
	 * le drone
	 */
	public Robot copierRobot(){
		Drone drone = new Drone(this);
		return drone;
	}

	/**
	 * La méthode de test de la possibilité pour un robot de se remplir est
	 * redéfinie pour le drone. Il faut dans ce cas que le robot soit sur une
	 * case d'eau.
	 * @param pos
	 * 			case à tester
	 * @return vrai si le robot est sur une case d'eau et faux sinon
	 */
	@Override
	public boolean peutSeRemplir(Case pos) {
		return (pos.getNature() == NatureTerrain.EAU);
	}

	
	@Override	
	public String toString(){
		return ("Drone " + super.toString() +
				" \n temps remplissage : " + this.getTempsRemp() + 
				" \n volume dispo :" + this.getVolEau() + 
				" \n sur " + this.getVolEauMax() + "\n");
	}

}
