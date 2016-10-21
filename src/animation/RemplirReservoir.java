package animation;

import objects.*;

public class RemplirReservoir extends Evenement {
	private Simulateur simu;

	public RemplirReservoir(long date, Robot robot, Simulateur simu) {
		super(date, robot);
		this.simu = simu;
	}

	@Override
	public void execute() {

		Case postion = this.robot.getPosition();
		NatureTerrain nat = this.robot.getPosition().getNature();
		long temps = this.robot.getTempsRemp(); // calcul temps
																// de
																// remplissage.
		if (robot.peutSeRemplir()) {
			Evenement rempli = new ReservoirRempli(this.getDate() + tmps,robot); //robot disponible après avoir complètement rempli son reservoir
			this.simu.ajouteEvenement(rempli);
		}
	}

	
}