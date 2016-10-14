package objects;
/** Classe Case : 
 * -int ligne
 *  -int colonne
 *  -NatureTerrain Nature*/

public class Case {

	private int ligne;
	private int colonne;
	private NatureTerrain nature;

	public Case(int ligne, int colonne, NatureTerrain nature){
		if (ligne < 0 || colonne < 0){
			throw new IllegalArgumentException ("les coordonnées de la case doivent être positives !");  
		}
		this.ligne = ligne;
		this.colonne = colonne;
		this.nature = nature;
	}
	
	public int getLigne(){
		return this.ligne;
	}
	
	public int getColonne(){
		return this.colonne;
	}
	
	public NatureTerrain getNature(){
		return this.nature;
	}

    public void setNature(NatureTerrain NT) {
        this.nature = NT;
    }

	@Override
	public String toString(){
		return ("Case " + this.ligne + " " + this.colonne + " de nature " + this.nature);
	}
}




 
