package animation;
import objects.Robot;
import exception.*;

/**
 * Classe abstraite permettant l'implémentation des évènements.
 * Chaque évènement possède une date d'exécution et est associé à un robot.
 * Le simulateur se charge d'exécuter l'évènement si la date courante correspond
 * à la date de l'évènement.
 */
public abstract class Evenement {
    private long date;
    private Robot robot;

    /**
     * Initialise les attributs généraux d'un évènement en prenant en paramètre la date
     * d'exécution et le robot associé à l'évènement.
     *
     * @param date
     * 		Date d'exécution de l'évènement
     * @param robot
     * 		Robot associé à l'évènement
     */
    public Evenement(long date, Robot robot) {
        this.date = date;
        this.robot = robot;
    }

    public long getDate() {
        return date;
    }
    public Robot getRobot(){
    	return this.robot;
    }
    public void setRobot(Robot robot){
    	this.robot = robot;
    }

    /**
     * Exécute l'action à réaliser de l'évènement.
     * Cette fonction est appelée par le simulateur lorsque la date courante
     * correspond à la date de l'évènement.
     * Remarque : l'action étant spécifique à chaque evènement,
     * cette fonction est définie par ses classes filles.
     *
     * @throws ExecutionEvenementException
     */
	public abstract void execute() throws ExecutionEvenementException;
}
