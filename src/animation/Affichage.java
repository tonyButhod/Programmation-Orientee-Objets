package animation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;

import gui.ImageElement;
import  java.awt.image.ImageObserver;

import objects.*;

public class Affichage implements Simulable {
	/** L'interface graphique associée */
	private GUISimulator gui;
	private DonneesSimulation donnees;
	private int lenCase;

	public Affichage(DonneesSimulation donnees, int lenCase) {
		this.donnees = donnees;
		this.lenCase = lenCase;
		Carte carte = this.donnees.getCarte();
		int tailleCases = carte.getTailleCases();
		int nbCol = carte.getNbColonnes();
		int nbLin = carte.getNbLignes();
		GUISimulator gui = new GUISimulator(nbCol * lenCase, nbLin * lenCase, Color.BLACK);
		this.gui = gui;
		gui.setSimulable(this);

		draw();
		drawRobots();
		drawIncendies();
	}

	@Override
	public void restart() {
		draw();
	}

	@Override
	public void next() {
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
				gui.addGraphicalElement(new Rectangle(j * lenCase, i * lenCase, couleur, couleur, lenCase));
			}
		}
	}

	public void drawRobots() {
		
		List<objects.Robot> robots = donnees.getRobots();
		Color couleur = Color.decode("#8f8f8f");
		Case position;
		ImageObserver io = null;
		
		for (int i = 0; i < robots.size(); i++) {
			position = robots.get(i).getPosition();
			//gui.addGraphicalElement(new ImageElement(position.getLigne() * lenCase, position.getColonne() * lenCase,"robot.png", lenCase, lenCase, io));
			gui.addGraphicalElement(new Oval(position.getLigne() * lenCase, position.getColonne() * lenCase, couleur, couleur, lenCase/2));
		}
	}

	public void drawIncendies() {
		
		List<Incendie> incendies = donnees.getIncendies();
		Color couleur = Color.decode("#DFFF00");
		Case position;
		
		for (int i = 0; i < incendies.size(); i++) {
			position = incendies.get(i).getPosition();
			gui.addGraphicalElement(new Rectangle(position.getLigne() * lenCase, position.getColonne() * lenCase,
					couleur, couleur, lenCase));

		}
	}
}
