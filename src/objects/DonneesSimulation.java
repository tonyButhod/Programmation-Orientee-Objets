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

}
