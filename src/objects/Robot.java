package objects;
/**Robot est une classe en partie abstraite*/
public abstract class Robot {
        //Attributs Robot à définir
        private Case position;
        protected double vitesse; //Necessaire pour changer valeur par défault
        protected int volEau;
        protected int volEauMax;
        
        
        public Robot (Case position){
        	this.position = position;
        }

        public Case getPosition(){
        	return this.position;
        }

        public void setPosition(Case c){
        	this.position = c;
        }

        public abstract double getVitesse(NatureTerrain NT);

        public abstract void deverserEau(int vol);
        	
        public abstract void remplirReservoir();

        //Rajouter des nouvelles méthodes si nécessaires
}
