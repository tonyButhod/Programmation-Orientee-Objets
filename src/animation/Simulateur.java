package animation;

import java.awt.Color;
import java.util.*;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import objects.*;

public class Simulateur implements Simulable {
    /** L'interface graphique associée */
    private GUISimulator gui;
    private DonneesSimulation donnees;
    private int lenCase;
    long dateSimulation;
    LinkedList<Evenement> evenements;

    public Simulateur(GUISimulator gui, DonneesSimulation donnees) {
        this.gui = gui;
        gui.setSimulable(this);				// association a la gui!
        this.donnees = donnees;
        this.lenCase = 30;
        this.dateSimulation = 0;
        this.evenements = new LinkedList<Evenement>();

        draw();
    }

    public void ajouteEvenement(Evenement e) {
        ListIterator<Evenement> le = evenements.listIterator();
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
        //La complexité de size est en O(1) car attribut.
        if (evenements.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void restart() {
        //Rappeler la fonction qui calcule tous les déplacements initiaux
        draw();
    }

    @Override
    public void next() {
        if (simulationTerminee()) {
            return;
        }
        ListIterator<Evenement> le = evenements.listIterator();
        Evenement e = le.hasNext()?le.next():null;
        while (e != null && e.getDate() <= dateSimulation) {
            e.execute();
            e = le.hasNext()?le.next():null;
        }
        incrementeDate();
        draw();
    }

    private void draw() {
        gui.reset();
        Carte carte = this.donnees.getCarte();
        for (int i = 0; i < carte.getNbLignes(); i++) {
            for (int j = 0; j < carte.getNbColonnes(); j++) {
                NatureTerrain nature = carte.getCase(i,j).getNature();
                Color couleur = Color.BLACK;
                if (nature == NatureTerrain.EAU) {
                    couleur = Color.decode("#0000ef");
                }
                else if (nature == NatureTerrain.ROCHE) {
                    couleur = Color.decode("#934d00");
                }
                else if (nature == NatureTerrain.FORET) {
                    couleur = Color.decode("#006b00");
                }
                else if (nature == NatureTerrain.TERRAIN_LIBRE) {
                    couleur = Color.WHITE;
                }
                else if (nature == NatureTerrain.HABITAT) {
                    couleur = Color.GRAY;
                }
                gui.addGraphicalElement(new Rectangle(j*lenCase, i*lenCase, couleur, couleur, lenCase));
            }
        }
    }

    @Override
    public String toString() {
        String out = "Affichage de la liste triée : ";
        ListIterator<Evenement> le = evenements.listIterator();
        while (le.hasNext()) {
            out += le.next().getDate()+" ";
        }
        return out;
    }
}
