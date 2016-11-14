package objects;

/**
 * Classe fournissant l'implémentation de la structure de données représentant une carte.
 */
public class Carte {
    private int nbLignes;
    private int nbColonnes;
    private int tailleCases;
    Case[][] tab;

	/**
	 * Construit une carte.
	 * 
	 * @param nbLignes
	 *            nombre de lignes de la carte
	 * @param nbColonnes
	 *            nombe de colonnes de la carte
	 * @param tailleCases
	 *            taille des cases de la carte
	 */
	public Carte(int nbLignes, int nbColonnes, int tailleCases) {
		// Ces attributs restent inchangés par la suite
		this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.tailleCases = tailleCases;
        this.tab = new Case[nbLignes][nbColonnes];
        for (int i=0; i<nbLignes; i++) {
            for (int j=0; j<nbColonnes; j++) {
                this.tab[i][j] = new Case(i,j,NatureTerrain.TERRAIN_LIBRE);
            }
        }
    }
    public int getNbLignes() {
        return this.nbLignes;
    }

    public int getNbColonnes() {
        return this.nbColonnes;
    }

    public int getTailleCases() {
		return this.tailleCases;
	}

	/**
	 * Retourne la case à l'emplacement indiqué.
	 * 
	 * @param lig
	 *            lign de la case recherchée
	 * @param col
	 *            colonne de la case rechechée
	 * @return case à l'emplacement (lig, col)
	 */
	public Case getCase(int lig, int col) {
		if (lig < 0 || col < 0 || lig >= nbLignes || col >= nbColonnes) {
			System.out.println("Indices incorrects");
			throw new IllegalArgumentException ("Indices incorrects");
        }
        return this.tab[lig][col];
    }

	/**
	 * Teste si le déplacement ans une direction depuis une case est possible.
	 * 
	 * @param src
	 *            case à tester
	 * @param dir
	 *            diection à tester
	 * @return true si le déplacement se fait vers une case de la carte, false
	 *         si ce dernier est en dehos
	 */
	public boolean voisinExiste(Case src, Direction dir) {
		int lig = src.getLigne();
		int col = src.getColonne();
		if ((lig == 0 && dir ==Direction.NORD) || 
            (lig==nbLignes-1 && dir==Direction.SUD) ||
            (col==0 && dir==Direction.OUEST) ||
            (col==nbColonnes-1 && dir==Direction.EST)) {
            return false;
        }
        return true;
    }

	/**
	 * Retourne la case voisine de la case indiquée dans la direction indiquée.
	 * Lève une exception si la case voisine n'est pas dans la carte.
	 * 
	 * @param src
	 *            case de départ
	 * @param dir
	 *            diection à appliquer
	 * @return case voisine de src dans la diecion dir
	 */
	public Case getVoisin(Case src, Direction dir) {
		if (!voisinExiste(src, dir)) {
			System.out.println("Voisin inexistant");
            throw new IllegalArgumentException ("Voisin inexistant");
        }
        int dl=0;
        int dc=0;
        if (dir==Direction.NORD) {
            dl = -1;
        }
        else if (dir==Direction.SUD) {
            dl = +1;
        }
        else if (dir==Direction.OUEST) {
            dc = -1;
        }
        else {
            dc = +1;
        }
        return this.tab[src.getLigne()+dl][src.getColonne()+dc];
    }
}
