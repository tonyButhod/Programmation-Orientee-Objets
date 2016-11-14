package objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe fournissant l'implémentation de la structure conenant les données de la simulation.
 */
public class DonneesSimulation {
    private Carte carte;
    private List<Incendie> incendies;
    private List<Robot> robots;
    
	/**
	 * Construit la structue de données
	 * 
	 * @param carte
	 *            carte sur laquelle on execute la simulation
	 * @param incendies
	 *            liste des incendies présents dans la simulation
	 * @param robots
	 *            liste des robots pésents dans la simulation
	 */
	public DonneesSimulation(Carte carte, List<Incendie> incendies, List<Robot> robots) {
        this.carte = carte;
        this.incendies = incendies;
        this.robots = robots;
    }

    public Carte getCarte() {
        return this.carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public List<Incendie> getIncendies() {
        return this.incendies;
    }

    public void setIncendies(List<Incendie> incendies) {
        this.incendies = incendies;
    }

    public List<Robot> getRobots() {
        return this.robots;
	}

	public void setRobots(List<Robot> robots) {
		this.robots = robots;
	}

	/**
	 * Methode de copie de la structue de données sur laquelle elle est
	 * instanciée. Les données sont copiées profondément et peuvent donc être
	 * modifiées indépendemment. Cette methode fait appel aux methodes de copie
	 * des sous-structures.
	 * 
	 * @return une nouvelle instance de DonneesSimulation copiée à partir de la
	 *         structue sur laquelle la méthode est instanciée
	 */
	public DonneesSimulation copierDonnees() {
		List<Incendie> incendiesCopies = new ArrayList<Incendie>(this.incendies);
		for (int i = 0; i < incendies.size(); i++) {
			Incendie feu = incendies.get(i);
			incendiesCopies.set(i, feu.copierIncendie());
		}
		List<Robot> robotsCopies = new ArrayList<Robot> (this.robots);
    	for(int i = 0; i < robots.size(); i++){
    		Robot robot = robots.get(i);
    		robotsCopies.set(i, robot.copierRobot());
    	}
    	DonneesSimulation donneesCopiees = new DonneesSimulation(this.carte, incendiesCopies, robotsCopies);
    	return donneesCopiees;
    }
}
