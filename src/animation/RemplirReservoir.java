package animation;

import objects.*;

public class RemplirReservoir extends Evenement {
	private Robot robot;
	private Simulateur simu;

	public RemplirReservoir(long date, RobotEau robot, Simulateur simu) {
		super(date);
		this.robot = robot;
		this.simu = simu
	}

	@Override
	public void execute() {

		Case postion = this.robot.getPosition();
		NatureTerrain nat = this.robot.position.getNature();
		int temps = robot.getVolEauMax() / robot.getVitRemp(); // calcul temps
																// de
																// remplissage.

		if (robot.getclass() == Drone.class && nat == NatureTerrain.EAU) {

			Evenement rempli = new ReservoirRempli(this.date + temps, RobotEau robot);
		} else if (robot.getclass() == Chenille.class || robot.getclass() == Patte.class && eauCote(this.robot)) {
			Evenement rempli = new ReservoirRempli(this.date + temps, RobotEau robot);
		} else {
			throw new IllegalArgumentException("Le robot ne peut pas se remplir !");
		}
		this.simu.ajouteEvenement(ReservoirRempli);
	}

	private boolean eauCote(Robot robot) {
		int ligne = robot.position.getLigne();
		int colonne = robot.position.getColonne();
		for (int i = ligne-1; i<=ligne+1; i++){
			for (int j= colonne-1; j<=colonne+1; j++){
				if (robot.carte.getCase(i,j).getNature() == NatureTerrain.EAU){
					return true;
				}
			}
		}
		return false;
	}
}