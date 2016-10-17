
import objects.*;
import animation.*;

public class TestSimulateur {
    public static void main(String args[]) {
        Simulateur simu = new Simulateur();
        Evenement e1 = new Intervention(1, null);
        Evenement e2 = new Intervention(2, null);
        Evenement e3 = new Intervention(3, null);
        Evenement e4 = new Intervention(4, null);
        Evenement e5 = new Intervention(5, null);
        simu.ajouteEvenement(e4);
        simu.ajouteEvenement(e1);
        simu.ajouteEvenement(e3);
        simu.ajouteEvenement(e5);
        simu.ajouteEvenement(e2);
        System.out.println(simu);
    }
}
