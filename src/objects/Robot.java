package objects;
/**Robot est une classe en partie abstraite*/
public abstract class Robot {
        //Attributs Robot à définir
        private Case position;
        protected double vitesse; //Necessaire pour changer valeur par défault
        protected double vitDever;  //vitesses en litres pas seconde
        
        public Robot (Case position){
        	this.position = position;
        }

        public Case getPosition(){
        	return this.position;
        }

        public void setPosition(Case c){
        	this.position = c;
        }
        public double getVitDever(){
        	return this.vitDever;
        }

        //public abstract double getVitesse(NatureTerrain NT);

        //public abstract void deverserEau(int vol);
        	
        //public abstract void remplirReservoir();

        //Rajouter des nouvelles méthodes si nécessaires
        public String toString(){
        	return ("sur la " + this.position + " \n vitesse: " 
        + vitesse + " \n vitesse d'extinction : " + vitDever);
        }
}
