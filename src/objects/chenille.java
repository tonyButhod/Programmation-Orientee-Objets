
class Chenille extends Robot {
        

        public double getVitesse(NatureTerrain NT) {
                if (NT == FORET) {
                        return 0.5*super.vitesse;
                }
                return super.vitesse;
        }
}
