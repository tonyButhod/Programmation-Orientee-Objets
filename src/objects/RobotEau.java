package objects;

public class RobotEau extends Robot {
	
	protected int volEau;
	protected int volEauMax;
	protected double vitRemp;  //double car vitesse non entières

	public RobotEau (Case position, Carte carte, int volEauMax){
		
		super(position, carte);
		this.volEau = 0; //Par défault une robot est vide.
		this.volEauMax = volEauMax;
	}

	public void setVolEau(int volEau){
		
		if (volEau > this.volEauMax || volEau < 0) {
			throw new IllegalArgumentException("Volume d'eau incorrect !");
		} else {
			this.volEau = volEau;
		}
	}

	public int getVolEau() {
		return this.volEau;
	}
	public double getvitRemp(){
		return this.vitRemp;
	}
}
