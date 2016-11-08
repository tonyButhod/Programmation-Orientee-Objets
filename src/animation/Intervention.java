package animation;

import objects.Robot;
import objects.Incendie;
import strategie.Dijkstra;

import exception.*;

public class Intervention extends Evenement {
	private Incendie incendie;
	private Simulateur simu;

	public Intervention(long date, Robot robot, Incendie incendie, Simulateur simu) {
		super(date, robot);
		this.incendie = incendie;
		this.simu = simu;
	}
	
	
	
	@Override
	public void execute() throws ExecutionEvenementException{
		if (! this.estSurIncendie()) {
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
		else if (this.incendie.getLitresEau() == 0){
			Robot r = this.getRobot();
			r.getChefRobot().leaveAMessage(r);
		}
		System.out.println("Date : " + this.getDate() + "Intensite : " + this.incendie.getLitresEau());
	}

	private boolean estSurIncendie() {
		return (this.incendie.getPosition() == this.getRobot().getPosition());
	}
}
