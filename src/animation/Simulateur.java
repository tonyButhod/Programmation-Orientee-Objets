package animation;

import java.awt.Color;
import java.util.*;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Oval;
import gui.Simulable;

import objects.*;

public class Simulateur implements Simulable {
	/** L'interface graphique associée */
	private GUISimulator gui;
	private DonneesSimulation donnees;
	private int lenCase;
	private long dateSimulation;
	public LinkedList<Evenement> evenements;
	public LinkedList<Evenement> evenementsAAjouter;

	public Simulateur(GUISimulator gui, DonneesSimulation donnees) {
		this.gui = gui;
		gui.setSimulable(this); // association a la gui!
		this.donnees = donnees;
		this.lenCase = 15;
		this.dateSimulation = 0;
		this.evenements = new LinkedList<Evenement>();
		this.evenementsAAjouter = new LinkedList<Evenement>();

		draw();
	}
	public long getDateSimu(){
		return this.dateSimulation;
	}

	public void ajouteEvenement(Evenement e, LinkedList<Evenement> list) {
		ListIterator<Evenement> le = list.listIterator();
		while (le.hasNext()) {
			if (le.next().getDate() > e.getDate()) {
				le.previous();
				break;
			}
		}
		le.add(e);
	}

	public void incrementeDate() {
		dateSimulation++;
	}

	public boolean simulationTerminee() {
		// La complexité de size est en O(1) car attribut.
		if (evenements.size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void restart() {
		// Rappeler la fonction qui calcule tous les déplacements initiaux
		draw();
	}

	@Override
	public void next() {
		if (simulationTerminee()) {
			return;
		}
		ListIterator<Evenement> le = evenements.listIterator();
		Evenement e = le.hasNext() ? le.next() : null;
		while (e != null && e.getDate() <= dateSimulation) {
			if (dateSimulation >= e.getRobot().getDateOccupe()) {
				e.execute();
			}
				e = le.hasNext() ? le.next() : null;
			evenements.remove();
		}
		if (evenementsAAjouter != null) {
			for (Evenement evt : evenementsAAjouter) {
				ajouteEvenement(evt, evenements);
				evenementsAAjouter.remove();
			}
		}
		verifIncendies();
		incrementeDate();
		draw();
	}

	private void draw() {
		gui.reset();
		Carte carte = this.donnees.getCarte();
		for (int i = 0; i < carte.getNbLignes(); i++) {
			for (int j = 0; j < carte.getNbColonnes(); j++) {
				NatureTerrain nature = carte.getCase(i, j).getNature();
				Color couleur = Color.BLACK;
				if (nature == NatureTerrain.EAU) {
					couleur = Color.decode("#0000ef");
				} else if (nature == NatureTerrain.ROCHE) {
					couleur = Color.decode("#934d00");
				} else if (nature == NatureTerrain.FORET) {
					couleur = Color.decode("#006b00");
				} else if (nature == NatureTerrain.TERRAIN_LIBRE) {
					couleur = Color.WHITE;
				} else if (nature == NatureTerrain.HABITAT) {
					couleur = Color.GRAY;
				}
				gui.addGraphicalElement(new Rectangle((int) ((j+0.5)*lenCase), (int) ((i+0.5)*lenCase), couleur, couleur, lenCase));
			}
		}
		drawIncendies();
		drawRobots();
	}

	@Override
	public String toString() {
		String out = "Affichage de la liste triée : ";
		ListIterator<Evenement> le = evenements.listIterator();
		while (le.hasNext()) {
			out += le.next().getDate() + " ";
		}
		return out;
	}

	public void drawRobots() {

		List<objects.Robot> robots = donnees.getRobots();
		Color couleur = Color.decode("#8f8f8f");
		Case position;
		//ImageObserver io = null;

		for (int i = 0; i < robots.size(); i++) {
			position = robots.get(i).getPosition();
			// gui.addGraphicalElement(new ImageElement(position.getLigne() *
			// lenCase, position.getColonne() * lenCase,"robot.png", lenCase,
			// lenCase, io));
			gui.addGraphicalElement(new Oval((int) ((position.getColonne()+0.5)*lenCase),
					(int) ((position.getLigne()+0.5)*lenCase), couleur, couleur, lenCase / 3));
		}
	}

	public void drawIncendies() {

		List<Incendie> incendies = donnees.getIncendies();
		Color couleur = Color.decode("#DFAF00");
		Case position;

		for (int i = 0; i < incendies.size(); i++) {
			// On ne dessine l'incendie que si ce dernier n'est pas d'intensité nulle
			if (incendies.get(i).getLitresEau() > 0) {
				position = incendies.get(i).getPosition();
				gui.addGraphicalElement(new Oval((int) ((position.getColonne() + 0.5) * lenCase),
						(int) ((position.getLigne() + 0.5) * lenCase), couleur, couleur, lenCase / 2));
			}
		}
	}
	
	private void verifIncendies() {
		List<Incendie> incendies = donnees.getIncendies();
		for (int i = 0; i < incendies.size(); i++) {
			if (incendies.get(i).getLitresEau() == 0.0) {
				incendies.remove(i);
			}
		}
	}
}
