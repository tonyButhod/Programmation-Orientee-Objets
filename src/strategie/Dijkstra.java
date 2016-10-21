package strategie;

import objects.*;
import animation.*;

import java.util.LinkedList;
import java.util.ListIterator;

public class Dijkstra {

    public static long tempsMin(Robot robot, Case end) {
        Carte map = robot.getCarte();
        Case start = robot.getPosition();
        int nbL = map.getNbLignes();
        int nbC = map.getNbColonnes();
        int posL = start.getLigne();
        int posC = start.getColonne();
        double temps[][] = new double[nbL][nbC];
        boolean lock[][] = new boolean[nbL][nbC];
        for (int i=0; i<nbL; i++) {
            for (int j=0; j<nbC; j++) {
                temps[i][j] = -1;
                lock[i][j] = false;
            }
        }
        temps[posL][posC] = 0;
        LinkedList<Case> sommets = new LinkedList<>();
        sommets.add(map.getCase(posL, posC));
        int endL = end.getLigne();
        int endC = end.getColonne();
        while (sommets.size() != 0 && !lock[endL][endC]) {
            Case cour = sommets.remove();
            posL = cour.getLigne();
            posC = cour.getColonne();
            lock[posL][posC]=true;
            //Ici, il nous reste à regarder les 4 voisins s'ils sont accessibles
            //Et les ajouter à la liste et mettre à jour leur temps
            for (Direction dir : Direction.values()) {
                if (map.voisinExiste(cour, dir)) {
                    Case voisin = map.getVoisin(cour, dir);
                    int posLvoisin = voisin.getLigne();
                    int posCvoisin = voisin.getColonne();
                    if (!lock[posLvoisin][posCvoisin]) {
                        double vit = robot.getVitesse(cour.getNature());
                        if (vit > 0) {
                            double time = map.getTailleCases() / vit;
                            if (temps[posLvoisin][posCvoisin] < 0) {
                                temps[posLvoisin][posCvoisin] = time + temps[posL][posC];
                                insereTrieCase(temps, sommets, map.getCase(posLvoisin, posCvoisin));
                            } else if (time + temps[posL][posC] < temps[posLvoisin][posCvoisin]) {
                                temps[posLvoisin][posCvoisin] = time + temps[posL][posC];
                                updateCase(temps, sommets, map.getCase(posLvoisin, posCvoisin));
                            }
                        }
                    }
                }
            }
        }
        if (!lock[endL][endC]) {
            return -1;
        }
        else {
            return (long) (temps[endL][endC]);
        }
    }

    public static long deplaceRobot(Simulateur simu, Robot robot, Case end, long tpsDebut) {
        Carte map = robot.getCarte();
        Case start = robot.getPosition();
        int nbL = map.getNbLignes();
        int nbC = map.getNbColonnes();
        int posL = start.getLigne();
        int posC = start.getColonne();
        double temps[][] = new double[nbL][nbC];
        boolean lock[][] = new boolean[nbL][nbC];
        Direction pred[][] = new Direction[nbL][nbC];
        for (int i=0; i<nbL; i++) {
            for (int j=0; j<nbC; j++) {
                temps[i][j] = -1;
                lock[i][j] = false;
                pred[i][j] = null;
            }
        }
        temps[posL][posC] = 0;
        LinkedList<Case> sommets = new LinkedList<>();
        sommets.add(map.getCase(posL, posC));
        int endL = end.getLigne();
        int endC = end.getColonne();
        while (sommets.size() != 0 && !lock[endL][endC]) {
            Case cour = sommets.remove();
            posL = cour.getLigne();
            posC = cour.getColonne();
            lock[posL][posC]=true;
            //Ici, il nous reste à regarder les 4 voisins s'ils sont accessibles
            //Et les ajouter à la liste et mettre à jour leur temps
            for (Direction dir : Direction.values()) {
                if (map.voisinExiste(cour, dir)) {
                    Case voisin = map.getVoisin(cour, dir);
                    int posLvoisin = voisin.getLigne();
                    int posCvoisin = voisin.getColonne();
                    if (!lock[posLvoisin][posCvoisin]) {
                        double vit = robot.getVitesse(cour.getNature());
                        if (vit > 0) {
                            double time = map.getTailleCases() / vit;
                            if (temps[posLvoisin][posCvoisin] < 0) {
                                temps[posLvoisin][posCvoisin] = time + temps[posL][posC];
                                pred[posLvoisin][posCvoisin] = dir;
                                insereTrieCase(temps, sommets, map.getCase(posLvoisin, posCvoisin));
                            } else if (time + temps[posL][posC] < temps[posLvoisin][posCvoisin]) {
                                temps[posLvoisin][posCvoisin] = time + temps[posL][posC];
                                pred[posLvoisin][posCvoisin] = dir;
                                updateCase(temps, sommets, map.getCase(posLvoisin, posCvoisin));
                            }
                        }
                    }
                }
            }
        }
        if (!lock[endL][endC]) {
            return -1;
        }
        else {
            Case cour = end;
            int posLstart = start.getLigne();
            int posCstart = start.getColonne();
            int posLcour = cour.getLigne();
            int posCcour = cour.getColonne();
            while (posLcour != posLstart || posCcour != posCstart) {
                Deplacement e = new Deplacement((long) temps[posLcour][posCcour]+tpsDebut, robot, pred[posLcour][posCcour]);
                simu.ajouteEvenement(e);
                Direction dirInv;
                if (pred[posLcour][posCcour]==Direction.NORD || pred[posLcour][posCcour]==Direction.SUD) {
                    dirInv = pred[posLcour][posCcour]==Direction.NORD?Direction.SUD:Direction.NORD;
                }
                else {
                    dirInv = pred[posLcour][posCcour]==Direction.OUEST?Direction.EST:Direction.OUEST;
                }
                cour = map.getVoisin(cour, dirInv);
                posLcour = cour.getLigne();
                posCcour = cour.getColonne();
            }
            return (long) (temps[endL][endC]);
        }
    }

    //On pourra optimiser et insérer directement les 4 cases
    //Insere une case dans une liste triée, en fonction des valeurs indiquées dans temps
    private static void insereTrieCase(double temps[][], LinkedList<Case> LC, Case c) {
        ListIterator<Case> ite = LC.listIterator();
        while (ite.hasNext()) {
            Case cour = ite.next();
            if (temps[cour.getLigne()][cour.getColonne()] > temps[c.getLigne()][c.getColonne()]) {
                ite.previous();
                break;
            }
        }
        ite.add(c);
    }

    //Fait pareil que insereTrieCase(), mais supprime l'occurrence déjà existante en plus
    private static void updateCase(double temps[][], LinkedList<Case> LC, Case c) {
        ListIterator<Case> ite = LC.listIterator();
        while (ite.hasNext()) {
            Case cour = ite.next();
            if (temps[cour.getLigne()][cour.getColonne()] > temps[c.getLigne()][c.getColonne()]) {
                ite.previous();
                break;
            }
        }
        ite.add(c);
        while (ite.hasNext()) {
            Case cour = ite.next();
            if (cour == c) {
                ite.remove();
                break;
            }
        }
    }
}