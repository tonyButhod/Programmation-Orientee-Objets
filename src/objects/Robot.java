package objects;

/**Robot est une classe en partie abstraite*/
public abstract class Robot {
        //Attributs Robot à définir
        private Case position;
        protected double vitesse; //Necessaire pour changer valeur par défault
        protected double vitDever;  //vitesses en litres pas seconde
    	protected long tempsRemp;  
        private Carte carte;
        long dateOccupe;
        protected String image = "images/"; //Pour le dessin des robots
        
 
		public Robot (Case position, Carte carte, long tempsRemp){
        	this.position = position;
        	this.carte = carte;
        	this.tempsRemp = tempsRemp;
        	setDateOccupe(-1); //le robot est libre des le début de la simalation
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
        
        public Carte getCarte(){
        	return this.carte;
        }

        public abstract double getVitesse(NatureTerrain NT);
        
    	public long getTempsRemp(){
    		return this.tempsRemp;
    	}
    	
    	public long getDateOccupe(){
    		return this.dateOccupe;
    	}
    	
    	public void setDateOccupe(long date){
    		this.dateOccupe = date;
    	}
    	
        public String getImage() {
 			return image;
 		}


        public abstract boolean peutSeRemplir();
        
        public abstract boolean deverserEau(Incendie incendie);
        	
        //public abstract void remplirReservoir();

        //Rajouter des nouvelles méthodes si nécessaires
        public String toString(){
        	return ("sur la " + this.position + " \n vitesse: " 
        + vitesse + " \n vitesse d'extinction : " + vitDever
        + "\n occupé jusqu'à la date :" + dateOccupe);
        }
}
