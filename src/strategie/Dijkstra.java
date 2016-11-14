package strategie;

import objects.*;
import animation.*;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Classe contenant que des fonctions statiques utilisant l'algorithme de Dijkstra.
 * Le principal objectif de cette classe est de clarifier le code en regroupant toutes
 * les fonctions liées à Dijkstra dans ce fichier, permettant de réduire la taille
 * du fichier roboot.
 */
public class Dijkstra {

    /**
     * Calcule et renvoie le temps minimum pour que le robot se déplace sur la
     * case end.
     * Cette fonction renvoie -1 si le robot ne peut pas atteindre la case end.
     *
     * @param robot
     * 		robot que l'on souhaite calculer le temps de déplacement minimum.
     * @param end
     * 		case de destination.
     * @return long
     *      temps nécessaire pour le déplacement du robot (-1 si pas possible)
     */
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

    /**
     * Calcule et renvoie le temps minimum pour que le robot atteigne la case end.
     * De plus, elle ajoute tous les évènements nécessaires au déplacement dans le simulateur
     * simu à compter du temps initial tpsDebut.
     * Renvoie -1 et ne prévoit aucun déplacement si la case n'est pas atteignable.
     *
     * @param simu
     * 		simulateur dans lequel on souhaite ajouter les évènements.
     * @param robot
     * 		robot que l'on souhaite déplacer.
     * @param end
     * 		case de destination.
     * @param tpsDebut
     * 		date initiale à partir de laquelle on commence le déplacement.
     * @return long
     *      temps nécessaire pour le déplacement du robot (-1 si pas possible)
     */
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

    /**
     * Calcule le point d'eau le plus proche et prévoit la série de déplacements
     * nécessaires pour l'atteindre, ainsi que le remplissage du robot.
     * Renvoie true si le robot peut se remplir, false sinon.
     *
     * @param simu
     * 		simulateur dans lequel on souhaite ajouter les évènements.
     * @param robot
     * 		robot que l'on souhaite déplacer.
     * @param tpsDebut
     * 		date initiale à partir de laquelle on commence le déplacement.
     * @return boolean
     *      si le robot peut se remplir ou non.
     */
    public static boolean fairePlein(Simulateur simu, Robot robot, long tpsDebut) {
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
        boolean eauTrouvee = false;
        while (sommets.size() != 0 && !eauTrouvee) {
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
                    if (robot.peutSeRemplir(cour)) {
                        eauTrouvee = true;
                    }
                    else if (!lock[posLvoisin][posCvoisin]) {
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
        if (!eauTrouvee) {
            return false;
        }
        else {
            Case cour = map.getCase(posL, posC);
            int posLstart = start.getLigne();
            int posCstart = start.getColonne();
            int posLcour = posL;
            int posCcour = posC;
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
            RemplirReservoir e = new RemplirReservoir((long) temps[posL][posC]+tpsDebut, robot, simu);
            simu.ajouteEvenement(e);
            return true;
        }
    }

    /**
     * Insere la case c dans la liste triée LC en fonction du temps indiqué dans le tableau temps.
     *
     * @param temps
     * 		tableau des temps pour chaque case.
     * @param LC
     * 		liste des cases atteignables dans l'algorithme de Dijkstra.
     * @param c
     * 		case que l'on souhaite insérer.
     */
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

    /**
     * Met à jour la position de la case c dans la liste triée LC en fonction
     * du temps dans le tableau temps (le temps lié c a été modifié).
     *
     * @param temps
     * 		tableau des temps pour chaque case.
     * @param LC
     * 		liste des cases atteignables dans l'algorithme de Dijkstra.
     * @param c
     * 		case que l'on souhaite repositionner dans la liste.
     */
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