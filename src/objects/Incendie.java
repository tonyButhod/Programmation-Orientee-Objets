/**
   Classe fournissant l'implémentation des incendies
   Invariant de classe : LtrEau >= 0
*/

package objects;
public class Incendie {
    private Case position;
    private int litresEau;

    public int getLitresEau() {
        return this.litresEau;
    }

    public void setLitresEau(int ltr) {
        if (ltr <= 0) {
            throw new IllegalArgumentException("Invariant sur les litres d'eau nécessaires pour un incendie non respecte (> 0)");
        }
        this.litresEau = ltr;
    }

    public Case getPosition() {
        return this.position;
    }

    public void setPosition(Case pos) {
        this.position = pos;
    }

    /* Construit un incendie */
    public Incendie(Case pos, int ltr) {
        this.position = pos;
        setLitresEau(ltr);
    }

    @Override
    public String toString() {
        return new String("Feu à la case " + this.position.toString() + ", necessite " + this.getLitresEau() + "L d'eau");
    }
        
}
