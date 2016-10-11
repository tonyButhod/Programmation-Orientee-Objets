/**
   Classe fournissant l'implémentation des incendies
   Invariant de classe : LtrEau >= 0
*/

package objects;
public class Incendie {
    public Case position;
    private int litresEau;

    public int getLitresEau() {
        return this.litresEau;
    }

    public void setLitresEau(int ltr) {
        if (ltr < 0) {
            throw new IllegalArgumentException("Invariant sur le litres d'eau nécessaires pour un incendie non respecte (>= 0)");
        }
        this.litresEau = ltr;
    }

    /* Construit un incendie */
    public Incendie(Case pos, int ltr) {
        this.Case = Case;
        setLitresEau(ltr);
    }

    @Override
    public String toString() {
        return new String("Feu à la case " + this.position.toString() + ", necessite " this.getLitresEau() + "L d'eau");
        
}
