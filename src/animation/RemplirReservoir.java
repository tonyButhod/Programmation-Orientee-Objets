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
			Evenement rempli = new ReservoirRempli(this.simu.getDateSimu() + temps,this.getRobot()); //robot disponible après avoir complètement rempli son reservoir
			this.robot.setDateOccupe(this.simu.getDateSimu() + temps); // blocage ajout évènement lors du remplissage (+1 pour etre sur que le robot est bien rempli)
			this.simu.ajouteEvenement(rempli, simu.evenementsAAjouter);
			System.out.println(this.getRobot());
		}else{
			System.out.println("Le robot ne peut pas se remplir ! ");
		}
	}

	
}