package animation;

import objects.*;
import exception.*;

public class RemplirReservoir extends Evenement {
	private Simulateur simu;

	public RemplirReservoir(long date, Robot robot, Simulateur simu) {
		super(date, robot);
		this.simu = simu;
	}

	@Override
	public void execute()throws ExecutionEvenementException {

		Case postion = this.robot.getPosition();
		NatureTerrain nat = this.robot.getPosition().getNature();
		long temps = this.robot.getTempsRemp(); // calcul temps
																// de
																// remplissage.
		if (robot.peutSeRemplir(robot.getPosition())) {
			Evenement rempli = new ReservoirRempli(this.simu.getDateSimu() + temps,this.getRobot()); //robot disponible après avoir complètement rempli son reservoir
			this.robot.setDateOccupe(this.simu.getDateSimu() + temps); // blocage ajout évènement lors du remplissage (+1 pour etre sur que le robot est bien rempli)
			this.simu.ajouteEvenement(rempli);
		}else{
			throw new ExecutionEvenementException("Remplissage", "Remplissage impossible (position non compatible)");
		}
	}

	
}