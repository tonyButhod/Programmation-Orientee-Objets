package objects;

/**Robot est une classe en partie abstraite*/
public abstract class Robot {
        private Case position;
        protected double vitesse; //Necessaire pour changer valeur par défault
        protected double vitDever;  //vitesses en litres pas seconde
    	protected long tempsRemp;
        protected double volEau;
        protected double volEauMax;
        private Carte carte;
        protected String image = "images/"; //Pour le dessin des robots
        private long dateOccupe;
        
 
		public Robot (Case position, Carte carte, long tempsRemp){
        	this.position = position;
        	this.carte = carte;
        	this.tempsRemp = tempsRemp;
            //Par défault, réservoir vide (à part pour Patte, on modifie dans sa classe)
            this.volEau = 0;
        	setDateOccupe(-1); //le robot est libre dès le début de la simalation
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

    	public double getVolEau() {
            return this.volEau;
        }

    	public void setVolEau(double volEau) {
            if (this.volEauMax != -1 && (volEau > this.volEauMax || volEau < 0.0)) {
                throw new IllegalArgumentException("Volume d'eau incorrect !");
            } else {
                this.volEau = volEau;
            }
        }

        public double getVolEauMax() {
            return this.volEauMax;
        }

        public void setVolEauMax(double volEauMax) {
            this.volEauMax = volEauMax;
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


        public boolean peutSeRemplir() {
            //Normalement un robot patte ne devrait jamais appeler cette fonction
            //Il faudra redéfinir cette fonction pour le drone
            Case pos = this.getPosition();
            for (Direction dir : Direction.values()) {
                Case voisin = carte.getVoisin(pos, dir);
                if (voisin.getNature() == NatureTerrain.EAU) {
                    return true;
                }
            }
            return false;
        }

    public void deverserEau(Incendie incendie) {
        // On diminue l'intensité de l'incindie du maximum que le robot peut
        // deverser en une unité de
        // temps (vitesse de déversement ou réservoir vidé)
        double maxDever = java.lang.Math.min(this.getVitDever(), this.getVolEau());
        double intensiteApresDever = incendie.getLitresEau() - maxDever;
        // Si l'incendie est éteint il est possible qu'on ait mit "trop" d'eau
        // On passe donc son intensité à 0 pour ne pas avoir une intensite
        // negative
        if (intensiteApresDever <= 0.0) {
            if (this.volEauMax > 0) {
                this.volEau -= incendie.getLitresEau();
            }
            incendie.setLitresEau(0.0);
        }
        else {
            if (this.volEauMax > 0) {
                this.volEau -= maxDever;
            }
            incendie.setLitresEau(intensiteApresDever);
            //System.out.println("J'ai déversé " + java.lang.Math.min(this.getVitDever(), this.getVolEau()) + " litres d'eau");
        }
    }
        	
        //public abstract void remplirReservoir();
        
        public String toString(){
        	return ("sur la " + this.position + " \n vitesse: " 
        + vitesse + " \n vitesse d'extinction : " + vitDever
        + "\n occupé jusqu'à la date :" + dateOccupe);
        }
}
