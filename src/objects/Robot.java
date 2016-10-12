package objects;
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

        public void deverserEau(int vol);

        public void remplirReservoir();

        //Rajouter des nouvelles méthodes si nécessaires
}
