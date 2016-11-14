package objects;

/**
 * Classe fournissant l'implémentation de la structure de données représentant une case.
 */
public class Case {

	private int ligne;
	private int colonne;
	private NatureTerrain nature;

	/**
	 * Construit une case.
	 * 
	 * @param ligne
	 *            ligne où se situe la case
	 * @param colonne
	 *            colonne où se situe la case
	 * @param nature
	 *            nature de la case à créer
	 */
	public Case(int ligne, int colonne, NatureTerrain nature) {
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
	public String toString() {
		return ("Case " + this.ligne + " " + this.colonne + " de nature " + this.nature);
	}

	/**
	 * Redéfinition de la methode equals pour les cases.
	 * 
	 * @param o
	 *            case à comparer avec celle sur laquelle la méthode est
	 *            instanciée
	 * @return true si les cases sont au même emplacement, false sinon ou si o
	 *         n'est pas une case
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Case)) {
			return false;
		}
		Case emplacement = (Case) o;
		return emplacement.getLigne() == this.getLigne() && emplacement.getColonne() == this.getColonne();
	}
}




 
