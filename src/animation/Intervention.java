package animation;

import objects.Robot;
import objects.Incendie;
import objects.DonneesSimulation;

public class Intervention extends Evenement {
	private Incendie incendie;
	private Simulateur simu;

	public Intervention(long date, Robot robot, Incendie incendie, Simulateur simu) {
		super(date, robot);
		this.incendie = incendie;
		this.simu = simu;
	}
	
	
	
	@Override
	public void execute() {
		if (! this.estSurIncendie()) {
			throw new IllegalArgumentException("Robot pas sur un incendie");
		}
		if (! this.getRobot().deverserEau(this.incendie)) {
			Evenement prochainDever = new Intervention(this.getDate()+1, this.getRobot(), this.incendie, this.simu);
			simu.ajouteEvenement(prochainDever);
			this.getRobot().setDateOccupe(this.getDate()+1);
		}
		System.out.println("Date : " + this.getDate() + "Intensite : " + this.incendie.getLitresEau());
	}

	private boolean estSurIncendie() {
		return (this.incendie.getPosition() == this.getRobot().getPosition());
	}
}
