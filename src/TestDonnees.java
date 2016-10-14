import io.LecteurDonnees;
import objects.*;

import java.util.ArrayList;
import java.util.List;

public class TestDonnees {
	public static void main(String[] args) {
        try {
            DonneesSimulation donnees = LecteurDonnees.lire("cartes/carteSujet.map");
            Carte map = donnees.getCarte();
            List<Incendie> incendies = donnees.getIncendies();
            
            System.out.println("Map : "+map.getNbLignes()+"x"+map.getNbColonnes()+", tailleCases : "+map.getTailleCases());
            System.out.println(map.getCase(7,3));
            System.out.println("Incendies : " + incendies.size() + " incendies, 3eme à la case " + incendies.get(2).getPosition() + " d'intensite " + incendies.get(2).getLitresEau());
        } finally {
            return;
        }
    }
}
