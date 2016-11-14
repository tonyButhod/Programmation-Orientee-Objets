package animation;

import java.awt.Color;
import java.util.*;

import gui.*;
import objects.*;
import strategie.*;

import exception.*;

/**
 * Classe Simulateur qui permet la simulation du problème.
 * Elle extend Simulable, et définit les fonctions next() et restart()
 * qui sont utilisées par Simulable pour afficher la simulation à l'écran.
 */
public class Simulateur implements Simulable {
	/** L'interface graphique associée */
	private GUISimulator gui;
	private DonneesSimulation donnees;
	private int lenCaseHeight;
	private int lenCaseWidth;
	private DonneesSimulation donneesBase;

	private long dateSimulation;
	private LinkedList<Evenement> evenements;
	private LinkedList<Evenement> evenementsAAjouter;
	private ChefRobot chef;

	/**
	 * Enregistre les paramètres de base du simulateur.
	 * En plus d'enregistrer les paramètres donnés en arguments, elle définit
	 * la taille de la fenêtre, fait un copie des données de bases pour restart(),
	 * définit la date simulation à 0 et initialise les listes et le chef robot à nulle.
	 * Pour finir elle dessine à l'écran.
	 *
	 * @param gui
	 * 		interface qui gère l'affichage
	 *
	 * 	@param donnees
	 * 		données de la simulation
	 */
	public Simulateur(GUISimulator gui, DonneesSimulation donnees) {
		this.gui = gui;
		gui.setSimulable(this); // association a la gui!
		this.donnees = donnees;
		this.lenCaseHeight = (gui.getPanelHeight() - 50) / donnees.getCarte().getNbLignes(); 
		this.lenCaseWidth = (gui.getPanelWidth() - 50) / donnees.getCarte().getNbColonnes();
		this.donneesBase = donnees.copierDonnees();

		this.dateSimulation = 0;
		this.evenements = new LinkedList<Evenement>();
		this.evenementsAAjouter = new LinkedList<Evenement>();
		this.chef = null;

		draw();
	}

	public long getDateSimu() {
		return this.dateSimulation;
	}

	public void setChef(ChefRobot chef) {
		this.chef = chef;
	}

	/**
	 * Ajoute un évènement e à la liste des évènements à ajouter.
	 * Il va être inséré plus tard dans la liste des évènements dans la fonction
	 * updateEvents().
	 *
	 * @param e
	 * 		Evènement à ajouter à la liste des évènements.
	 */
	public void ajouteEvenement(Evenement e) {
		ListIterator<Evenement> le = evenementsAAjouter.listIterator();
		while (le.hasNext()) {
			if (le.next().getDate() > e.getDate()) {
				le.previous();
				break;
			}
		}
		le.add(e);
	}

	/**
	 * Procède à la fusion de 2 listes triées pour mettre à jour les
	 * évènements à exécuter. La nouvelle liste des évènements formée est triée.
	 */
	private void updateEvents() {
		if (evenementsAAjouter.size() == 0) {
			return;
		}
		if (evenements.size() == 0) {
			LinkedList<Evenement> temporaire = evenements;
			evenements = evenementsAAjouter;
			evenementsAAjouter = temporaire;
			return;
		}
		LinkedList<Evenement> newl = new LinkedList<Evenement>();
		ListIterator<Evenement> le = evenements.listIterator();
		ListIterator<Evenement> leAdd = evenementsAAjouter.listIterator();
		boolean continuer = true;
		while (continuer) {
			if (le.hasNext() && leAdd.hasNext()) {
				Evenement e = le.next();
				Evenement eAdd = leAdd.next();
				if (e.getDate() <= eAdd.getDate()) {
					newl.add(e);
					leAdd.previous();
				} else {
					newl.add(eAdd);
					le.previous();
				}
			} else if (le.hasNext()) {
				newl.add(le.next());
			} else if (leAdd.hasNext()) {
				newl.add(leAdd.next());
			} else {
				continuer = false;
			}
		}
		evenementsAAjouter.clear();
		evenements.clear();
		evenements = newl;
	}

	private void incrementeDate() {
		dateSimulation++;
	}

	private boolean simulationTerminee() {
		// La complexité de size est en O(1) car attribut.
		if (evenements.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Permet de recharger les données de début enregistrées dans
	 * l'attribut donneesBase, et réinitialise les listes et dateSimulation.
	 */
	@Override
	public void restart() {
		donnees = donneesBase.copierDonnees();
		evenements.clear();
		evenementsAAjouter.clear();
		dateSimulation = 0;
		if (this.chef != null) {
			// On réinitialise le chef robot
			this.chef.init(donnees.getIncendies(), donnees.getRobots());
		}
		draw();
	}

	/**
	 * Si la simulation possède un chef robot, elle exécute la stratégie associée.
	 * Ensuite elle met à jour les évènements, et exécute les évènements si leur date
	 * est inférieur ou égale à la date simulation.
	 * Ensuite, elle met à jour les incendies, incrémente la date et dessine.
	 *
	 * Remarque : Si une exception est levée, elle est rattrapée et affichée sur la console, et
	 * l'évènement associé est supprimé avant de passer aux évènements suivants.
	 */
	@Override
	public void next() {
		try {
			if (this.chef != null) {
				this.chef.executeStrategie();
			}
			updateEvents();
			if (simulationTerminee()) {
				return;
			}
			ListIterator<Evenement> le = evenements.listIterator();
			Evenement e = le.hasNext() ? le.next() : null;
			while (e != null && e.getDate() <= dateSimulation) {
				if (dateSimulation >= e.getRobot().getDateOccupe()) {
					e.execute();
				}
				le.remove();
				e = le.hasNext() ? le.next() : null;
			}
			verifIncendies();
			incrementeDate();
			draw();

		} catch (ExecutionEvenementException e) {
			System.out.println(e);
			evenements.remove();
		}
	}

	/**
	 * Dessine la carte avec les incendies et les robots à l'écran.
	 */
	private void draw() {
		gui.reset();
		Carte carte = this.donnees.getCarte();
		for (int i = 0; i < carte.getNbLignes(); i++) {
			for (int j = 0; j < carte.getNbColonnes(); j++) {
				NatureTerrain nature = carte.getCase(i, j).getNature();
				Color couleur = Color.BLACK;
				String image = "images/";
				if (nature == NatureTerrain.EAU) {
					image += "eau.jpg";
				} else if (nature == NatureTerrain.ROCHE) {
					image += "roche.png";
				} else if (nature == NatureTerrain.FORET) {
					image += "foret.png";
				} else if (nature == NatureTerrain.TERRAIN_LIBRE) {
					image += "herbe.png";
				} else if (nature == NatureTerrain.HABITAT) {
					image += "maison.png";
				}
				gui.addGraphicalElement(new ImageElement((int) ((j + 0.5) * lenCaseWidth),
						(int) ((i + 0.5) * lenCaseHeight), image, lenCaseWidth, lenCaseHeight, null));
			}
		}
		drawIncendies();
		drawRobots();
	}

	/**
	 * Dessine les robots à l'écran.
	 */
	public void drawRobots() {

		List<objects.Robot> robots = donnees.getRobots();
		Color couleur = Color.decode("#8f8f8f");
		Case position;
		// ImageObserver io = null;

		for (int i = 0; i < robots.size(); i++) {
			position = robots.get(i).getPosition();
			gui.addGraphicalElement(new ImageElement((int) ((position.getColonne() + 0.5) * lenCaseWidth),
					(int) ((position.getLigne() + 0.5) * lenCaseHeight), robots.get(i).getImage(), lenCaseWidth,
					lenCaseHeight, null));
		}
	}

	/**
	 * Dessine les incendies à l'écran.
	 */
	public void drawIncendies() {

		List<Incendie> incendies = donnees.getIncendies();
		Color couleur = Color.decode("#DFAF00");
		Case position;

		for (int i = 0; i < incendies.size(); i++) {

			// L'incendie ne doit pas etre dessinne si son intensite est nulle
			// mais
			// la fonction verifIncendies est appellée avant draw(), les
			// incendies nuls sont donc
			// supprimés de la liste
			position = incendies.get(i).getPosition();
			gui.addGraphicalElement(new ImageElement((int) ((position.getColonne() + 0.5) * lenCaseWidth),
					(int) ((position.getLigne() + 0.5) * lenCaseHeight), "images/Fire.gif", lenCaseWidth, lenCaseHeight,
					null));
		}
	}

	/**
	 * Parcourt les incendies, et dès qu'un incendie est éteint (litresEau = 0),
	 * on le supprime de la liste des incendies.
	 */
	private void verifIncendies() {
		List<Incendie> incendies = donnees.getIncendies();
		for (int i = 0; i < incendies.size(); i++) {
			if (incendies.get(i).getLitresEau() == 0.0) {
				incendies.remove(i);
			}
		}
	}
}
