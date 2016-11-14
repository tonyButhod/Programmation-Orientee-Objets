package animation;

import objects.Robot;
import objects.Incendie;
import strategie.Dijkstra;

import exception.*;

/** Classe fournissant l'implémentation des Interventions
 *  A la date correspondante, fait intervenir le robot en versant l'eau qu'il contient,
 *  et si nécessaire lui ordonne d'aller se remplir.
 *
 *  Remarque : cette classe extend la classe Evenement.
 */
public class Intervention extends Evenement {
	private Incendie incendie;
	private Simulateur simu;

	/**
	 * Enregistre les paramètres passés en argument liés à une intervention.
	 *
	 * @param date
	 * 		date d'exécution de l'évènement intervention.
	 * @param robot
	 * 		robot associé à l'intervention.
	 * @param incendie
	 * 		incendie sur lequel intervenir.
	 * @param simu
	 * 		simulateur qui exécute l'intervention.
	 */
	public Intervention(long date, Robot robot, Incendie incendie, Simulateur simu) {
		super(date, robot);
		this.incendie = incendie;
		this.simu = simu;
	}


	/**
	 * Redéfinition de la fonction execute.
	 * Vérifie si le robot est bien sur l'incendie désiré (renvoie une exception sinon).
	 * Si c'est la cas, elle verse l'eau du robot sur l'incendie.
	 * Si le robot est vide, elle lui ordonne de retourner se remplir (et
	 * s'il ne peut pas se remplir, le robot est inutile et n'est pas ajouté aux robots libres).
	 * Sinon, si le feu n'est toujours pas éteint, l'intervention continue
	 * (avec la création d'une nouvelle intervention).
	 * Sinon, le feu est éteint, le robot indique alors au chef robot qu'il a fini.
	 *
	 * @throws ExecutionEvenementException
	 */
	@Override
	public void execute() throws ExecutionEvenementException{
		if (!this.estSurIncendie()) {
			throw new ExecutionEvenementException("Intervention", "Robot pas sur l'incendie");
		}
		this.getRobot().deverserEau(this.incendie);
		if (this.getRobot().getVolEau() == 0) {
			Dijkstra.fairePlein(simu, this.getRobot(), this.getDate());
		}
		else if (this.incendie.getLitresEau() > 0){
			Evenement prochainDever = new Intervention(this.getDate()+1, this.getRobot(), this.incendie, this.simu);
			simu.ajouteEvenement(prochainDever);
			this.getRobot().setDateOccupe(this.getDate()+1);
		}
		else {
			Robot r = this.getRobot();
			r.getChefRobot().leaveAMessage(r);
		}
		//System.out.println("Date : " + this.getDate() + "Intensite : " + this.incendie.getLitresEau());
	}

	private boolean estSurIncendie() {
		return (this.incendie.getPosition() == this.getRobot().getPosition());
	}
}
