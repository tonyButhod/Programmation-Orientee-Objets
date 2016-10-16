import io.LecteurDonnees;
import objects.*;

import java.util.ArrayList;
import java.util.List;

import com.sun.glass.ui.Robot;

public class TestDonnees {
	public static void main(String[] args) {
        try {
            DonneesSimulation donnees = LecteurDonnees.lire("cartes/carteSujet.map");
            Carte map = donnees.getCarte();
            List<Incendie> incendies = donnees.getIncendies();
            List<objects.Robot> robots = donnees.getRobots();
            System.out.println("Map : "+map.getNbLignes()+"x"+map.getNbColonnes()+", tailleCases : "+map.getTailleCases());
            System.out.println(map.getCase(7,3));
            System.out.println("Incendies : " + incendies.size() + " incendies, 3eme à la case " + incendies.get(2).getPosition() + " d'intensite " + incendies.get(2).getLitresEau());
            System.out.println("Nombre de robots: " +robots.size() + "\n");
            for (int i=0; i<robots.size();i++){
            	System.out.println(robots.get(i));     
            }
        } finally {
            return;
        }
    }
}
