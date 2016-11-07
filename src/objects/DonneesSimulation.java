package objects;

import java.util.ArrayList;
import java.util.List;

public class DonneesSimulation {
    private Carte carte;
    private List<Incendie> incendies;
    private List<Robot> robots;
    
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

    public DonneesSimulation copierDonnees() {
    	List <Incendie> incendiesBase = new ArrayList<Incendie> (this.incendies);
    	for(int i = 0; i < incendies.size(); i++){
    		Incendie feu = incendies.get(i);
    		incendiesBase.set(i, feu.copierIncendie());
    	}
    	List <Robot> robotsBase = new ArrayList<Robot> (this.robots);
    	//TO DO : copie de chaque Robot de la liste
    	for(int i = 0; i < robots.size(); i++){
    		Robot robot = robots.get(i);
    		robotsBase.set(i, robot.copierRobot());
    	}
    	DonneesSimulation donneesCopiees = new DonneesSimulation(this.carte, incendiesBase, robotsBase);
    	return donneesCopiees;
    }
}
