package objets;

public class Carte {
        private int nbLignes;
        private int NbColonnes;
        private int tailleCases;
        Case[][] tab;

        public Carte(int nbLignes, int nbColonnes, int tailleCases) {
                //Ces attributs restent inchangés par la suite
                this.nbLignes = nbLignes;
                this.nbColonnes = nbColonnes;
                this.tailleCases = tailleCases;
                this.tab = new Case[nbLignes][nbColonnes];
                for (int i=0; i<nbLignes; i++) {
                        for (int j=0; j<nbColonnes; j++) {
                                this.tab[i][j] = null;
                        }
                }
        }
        
        public void evenement(long date) {
                // Bouger tous les robots ?
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

        public Case getCase(int lig, int col) {
                if (lig<0 || col<0 || lig>=NbLignes || col>=NbColonnes) {
                        throw new IllegalArgumentException ("Indices incorrects");
                }
                return this.tab[lig][col];
        }

        public boolean voisinExiste(Case src, Direction dir) {
                int lig = src.getLigne();
                int col = src.getColonne();
                if ((lig==0 && dir==Direction.NORD) || 
                    (lig==NbLignes-1 && dir==Direction.SUD) ||
                    (col==0 && dir==Direction.OUEST) ||
                    (col==NbColonnes-1 && dir==Direction.EST)) {
                        return false;
                }
                return true;
        }

        public Case getVoisin(Case src, Direction dir) {
                if (!voisinExiste(src, dir)) {
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
