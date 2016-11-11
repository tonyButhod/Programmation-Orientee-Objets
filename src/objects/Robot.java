package objects;

import strategie.*;
import animation.*;

/**
 * Classe en partie abstraite fournissant l'implémentation des robots. Contient
 * les attributs communs aux différentes robots. Comprend aussi des références
 * vers la carte sur laquelle le robot se déplace ainsi que son chef robot pour
 * l'envoi de messages de libération.
 */
public abstract class Robot {
	private Case position;
	protected double vitesse; // Necessaire pour changer valeur par défault
	private double vitDever; // vitesse en litres pas seconde
	private long tempsRemp;
	private double volEau;
	private double volEauMax;
	private long dateOccupe;
	private String image = "images/"; // Pour le dessin des robots

	private Carte carte;
	private ChefRobot chef;

	/**
	 * Construit un robot à partir des paramètres d'un autre robot.
	 * 
	 * @param robot
	 *            robot dont les paramètres sont à utiliser
	 */
	public Robot(Robot robot) {
		this.position = robot.position;
		this.vitesse = robot.vitesse;
		this.vitDever = robot.vitDever;
		this.tempsRemp = robot.tempsRemp;
		this.volEau = robot.volEau;
		this.volEauMax = robot.volEauMax;
		this.carte = robot.carte;
		this.chef = robot.chef;
		this.image = robot.image;
		this.dateOccupe = robot.dateOccupe;
	}

	/**
	 * Construit un robot.
	 * 
	 * @param position
	 *            case où se situe le robot
	 * @param carte
	 *            carte sur laquelle le robot se déplace
	 * @param tempsRemp
	 *            temps nécessaire au remplissage en eau complet du robot (en s)
	 * @param volEau
	 *            volume d'eau contenu dans le robot
	 * @param volEauMax
	 *            volume d'eau maximale que le robot peut contenir
	 * @param vitDever
	 *            vitesse de déversement de l'eau du robot (en L/s)
	 * @param nomImage
	 *            nom de l'image à utiliser pour afficher le robot (placée dans
	 *            le dossier images)
	 */
	public Robot(Case position, Carte carte, long tempsRemp, double volEau, double volEauMax, double vitDever,
			String nomImage) {
		this.position = position;
		this.carte = carte;
		this.tempsRemp = tempsRemp;
		this.volEau = volEau;
		this.volEauMax = volEauMax;
		this.vitDever = vitDever;
		this.image += nomImage;
		setDateOccupe(-1); // le robot est libre dès le début de la simulation
	}

	public Case getPosition() {
		return this.position;
	}

	public void setPosition(Case c) {
		this.position = c;
	}

	public double getVitDever() {
		return this.vitDever;
	}

	public Carte getCarte() {
		return this.carte;
	}

	public ChefRobot getChefRobot() {
		return this.chef;
	}

	public void setChefRobot(ChefRobot chef) {
		this.chef = chef;
	}

	public long getTempsRemp() {
		return this.tempsRemp;
	}

	public double getVolEau() {
		return this.volEau;
	}

	public void setVolEau(double volEau) {
		if (this.volEauMax != -1 && (volEau > this.volEauMax || volEau < 0.0)) {
			throw new IllegalArgumentException("Volume d'eau incorrect !");
		} else {
			this.volEau = volEau;
		}
	}

	public double getVolEauMax() {
		return this.volEauMax;
	}

	public void setVolEauMax(double volEauMax) {
		this.volEauMax = volEauMax;
	}

	public long getDateOccupe() {
		return this.dateOccupe;
	}

	public void setDateOccupe(long date) {
		this.dateOccupe = date;
	}

	public String getImage() {
		return image;
	}

	/**
	 * Methode abstraite la vitesse du robot en fonction du terrain
	 * 
	 * @param NT
	 * 			nature du terrain sur lequel on souhaite connaître la vitesse du robot
	 */
	public abstract double getVitesse(NatureTerrain NT);

	/**
	 * Methode testant si un robot peut se remplir à la position donnée en
	 * paramètre. Cette fonction est à redéfinir pour le drone qui se remplir au
	 * dessus et non à côté d'une case d'eau. Le robot à patte quant à lui n'est
	 * pas censé y faire appel.
	 * 
	 * @param pos
	 *            case à tester
	 * @return vrai si le robot est à côté d'une case d'eau et faux sinon
	 */
	public boolean peutSeRemplir(Case pos) {
		for (Direction dir : Direction.values()) {
			if (carte.voisinExiste(pos, dir)) {
				Case voisin = carte.getVoisin(pos, dir);
				if (voisin.getNature() == NatureTerrain.EAU) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Methode mettant à jour l'intensité de l'incendie passé en paramètre pour
	 * l'intervention du robot sur lequel la méthode est instanciée
	 * 
	 * @param incendie
	 *            incendie sur lequel le robot intervient
	 */
	public void deverserEau(Incendie incendie) {
		// On diminue l'intensité de l'incindie du maximum que le robot peut
		// deverser en une unité de temps (vitesse de déversement ou réservoir
		// vidé)
		double maxDever = java.lang.Math.min(this.getVitDever(), this.getVolEau());
		double intensiteApresDever = incendie.getLitresEau() - maxDever;
		// Si intensiteApresDever est négative alors le robot n'a pas besoin de
		// déverser autant d'eau, on met donc l'intensité de l'incendie à 0 et
		// on soustrait
		if (intensiteApresDever <= 0.0) {
			if (this.volEauMax > 0) {
				this.volEau -= incendie.getLitresEau();
			}
			incendie.setLitresEau(0.0);
		} else {
			if (this.volEauMax > 0) {
				this.volEau -= maxDever;
			}
			incendie.setLitresEau(intensiteApresDever);
		}
	}

	/**
	 * Methode abstraite pour la copie du robot sur lequel elle est instanciée.
	 * Renvoie un Robot de type dynamique d'une de ses classes filles.
	 * 
	 * @return Un nouveau robot copié à parti de celui sur lequel la méthode est
	 *         instanciée
	 */
	public abstract Robot copierRobot();

	/**
	 * Methode permettant d'ajouter un évènement d'intervention au robot sur
	 * lequel elle est instanciée. Si l'incendie est atteignable par le robot
	 * cet évènement est ajouté après le temps nécessaire à son déplacement.
	 * 
	 * @param simu
	 * 			simulateur auquel on va ajouter l'évènement d'intervention
	 * @param fire
	 * 			incendie sur lequel le robot doit intervenir
	 */
	public void intervient(Simulateur simu, Incendie fire) {
		long tps = Dijkstra.deplaceRobot(simu, this, fire.getPosition(), simu.getDateSimu());
		if (tps == -1) {
			System.out.println("Le chef demande au robot d'éteindre un feu inatteignable");
			return;
		}
		Evenement eteint = new Intervention(simu.getDateSimu()+tps, this, fire, simu);
		simu.ajouteEvenement(eteint);
	}
	
	public String toString() {
		return ("sur la " + this.position + " \n vitesse: " + vitesse + " \n vitesse d'extinction : " + vitDever
				+ "\n occupé jusqu'à la date :" + dateOccupe);
	}
}
