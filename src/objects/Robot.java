package objects;
/**Robot est une classe en partie abstraite*/
abstract class Robot {
        //Attributs Robot à définir
        private Case position;
        protected double vitesse; //Necessaire pour changer valeur par défault
        protected int volEau;
        protected int volEauMax;

        public Case getPosition(){
        	return this.position;
        }

        public void setPosition(Case c){
        	this.position = c;
        }

        public double getVitesse(NatureTerrain NT);

        public void deverserEau(int vol){
        	if (vol > this.volEau){
        		throw new IllegalArgumentException("Volume d'eau insuffisant!");
        	}else{
        		this.volEau -= vol;
        	}
        }

        public void remplirReservoir();
        	this.volEau = this.volEauMax;

        //Rajouter des nouvelles méthodes si nécessaires
}
