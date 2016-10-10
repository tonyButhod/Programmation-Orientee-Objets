
class Patte extends Robot {
        

        public double getVitesse(NatureTerrain NT) {
                if (NT == ROCHE) {
                        return super.vitesse-10;
                }
                return super.vitesse;
        }
}
