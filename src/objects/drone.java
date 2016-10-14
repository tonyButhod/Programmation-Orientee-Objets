package objects;
class Drone extends Robot {
        
	
		public Drone (double vitesse, int volEau, int VolEauMax, Case position){
			super(position);
			this.volEau = volEau;
		}

        public double getVitesse(NatureTerrain NT) {
                return super.vitesse;
        }

}
