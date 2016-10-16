package io;

import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;

import objects.*;


/**
 * Lecteur de cartes au format spectifié dans le sujet. Les données sur les
 * cases, robots puis incendies sont lues dans le fichier, puis simplement
 * affichées. A noter: pas de vérification sémantique sur les valeurs numériques
 * lues.
 *
 * IMPORTANT:
 *
 * Cette classe ne fait que LIRE les infos et les afficher. A vous de modifier
 * ou d'ajouter des méthodes, inspirées de celles présentes (ou non), qui CREENT
 * les objets au moment adéquat pour construire une instance de la classe
 * DonneesSimulation à partir d'un fichier.
 *
 * Vous pouvez par exemple ajouter une méthode qui crée et retourne un objet
 * contenant toutes les données lues: public static DonneesSimulation
 * creeDonnees(String fichierDonnees); Et faire des méthode creeCase(),
 * creeRobot(), ... qui lisent les données, créent les objets adéquats et les
 * ajoutent ds l'instance de DonneesSimulation.
 */
public class LecteurDonnees {

	/**
	 * Lit et affiche le contenu d'un fichier de donnees (cases, robots et
	 * incendies). Ceci est méthode de classe; utilisation:
	 * LecteurDonnees.lire(fichierDonnees)
	 * 
	 * @param fichierDonnees
	 *            nom du fichier à lire
	 */
	public static DonneesSimulation lire(String fichierDonnees)
			// Liste de Robots créée temporairement, à enlever lors de
			// l'implémentation de lireRobots()
			throws FileNotFoundException, DataFormatException {
		System.out.println("\n == Lecture du fichier " + fichierDonnees);
		LecteurDonnees lecteur = new LecteurDonnees(fichierDonnees);
		Carte map = lecteur.lireCarte();
		List<Incendie> incendies = lecteur.lireIncendies(map);
		// lecteur.lireRobots();
		// TEMP
		List<Robot> robots = lecteur.lireRobots(map);

		DonneesSimulation donnees = new DonneesSimulation(map, incendies, robots);

		scanner.close();
		System.out.println("\n == Lecture terminee");
		return donnees;
	}

	// Tout le reste de la classe est prive!

	private static Scanner scanner;

	/**
	 * Constructeur prive; impossible d'instancier la classe depuis l'exterieur
	 * 
	 * @param fichierDonnees
	 *            nom du fichier a lire
	 */
	private LecteurDonnees(String fichierDonnees) throws FileNotFoundException {
		scanner = new Scanner(new File(fichierDonnees));
		scanner.useLocale(Locale.US);
	}

	/**
	 * Lit et affiche les donnees de la carte.
	 * 
	 * @throws ExceptionFormatDonnees
	 */
	private Carte lireCarte() throws DataFormatException {
		ignorerCommentaires();
		Carte map;
		try {
			int nbLignes = scanner.nextInt();
			int nbColonnes = scanner.nextInt();
			int tailleCases = scanner.nextInt(); // en m
			/*
			 * System.out.println("Carte " + nbLignes + "x" + nbColonnes +
			 * "; taille des cases = " + tailleCases);
			 */
			map = new Carte(nbLignes, nbColonnes, tailleCases);

			Case c;
			for (int lig = 0; lig < nbLignes; lig++) {
				for (int col = 0; col < nbColonnes; col++) {
					c = map.getCase(lig, col);
					c.setNature(lireCase(lig, col));
				}
			}

		} catch (NoSuchElementException e) {
			throw new DataFormatException("Format invalide. " + "Attendu: nbLignes nbColonnes tailleCases");
		}
		// une ExceptionFormat levee depuis lireCase est remontee telle quelle
		return map;
	}

	/**
	 * Lit et affiche les donnees d'une case.
	 */
	private NatureTerrain lireCase(int lig, int col) throws DataFormatException {
		ignorerCommentaires();
		// System.out.print("Case (" + lig + "," + col + "): ");
		String chaineNature = new String();
		NatureTerrain nature;

		try {
			chaineNature = scanner.next();
			// si NatureTerrain est un Enum, vous pouvez recuperer la valeur
			// de l'enum a partir d'une String avec:
			nature = NatureTerrain.valueOf(chaineNature);

			verifieLigneTerminee();

			// System.out.print("nature = " + chaineNature);

		} catch (NoSuchElementException e) {
			throw new DataFormatException("format de case invalide. " + "Attendu: nature altitude [valeur_specifique]");
		}

		// System.out.println();
		return nature;
	}

	/**
	 * Lit et affiche les donnees des incendies.
	 */
	private List<Incendie> lireIncendies(Carte map) throws DataFormatException {
		ignorerCommentaires();

		List<Incendie> incendies = new ArrayList<Incendie>();

		try {
			int nbIncendies = scanner.nextInt();
			// System.out.println("Nb d'incendies = " + nbIncendies);
			for (int i = 0; i < nbIncendies; i++) {
				incendies.add(lireIncendie(map, i));
			}

		} catch (NoSuchElementException e) {
			throw new DataFormatException("Format invalide. " + "Attendu: nbIncendies");
		}

		return incendies;
	}

	/**
	 * Lit et affiche les donnees du i-eme incendie.
	 * 
	 * @param i
	 */
	private Incendie lireIncendie(Carte map, int i) throws DataFormatException {
		ignorerCommentaires();
		// System.out.print("Incendie " + i + ": ");
		Case pos;
		Incendie feu;

		try {
			int lig = scanner.nextInt();
			int col = scanner.nextInt();
			int intensite = scanner.nextInt();
			if (intensite <= 0) {
				throw new DataFormatException("incendie " + i + "nb litres pour eteindre doit etre > 0");
			}
			verifieLigneTerminee();

			pos = map.getCase(lig, col);
			feu = new Incendie(pos, intensite);

			/*
			 * System.out.println("position = (" + lig + "," + col +
			 * ");\t intensite = " + intensite);
			 */

		} catch (NoSuchElementException e) {
			throw new DataFormatException("format d'incendie invalide. " + "Attendu: ligne colonne intensite");
		}
		return feu;
	}

	/**
	 * Lit et affiche les donnees des robots.
	 */
	private List<Robot> lireRobots(Carte map) throws DataFormatException {
		ignorerCommentaires();
		List<Robot> robots = new ArrayList<Robot>();
		try {
			int nbRobots = scanner.nextInt();

			// System.out.println("Nb de robots = " + nbRobots);

			for (int i = 0; i < nbRobots; i++) {
				robots.add(lireRobot(i, map));
			}

		} catch (NoSuchElementException e) {
			throw new DataFormatException("Format invalide. " + "Attendu: nbRobots");
		}
		return robots;
	}

	/**
     * Lit et affiche les donnees du i-eme robot.
     * @param i
     */
    private Robot lireRobot(int i, Carte map) throws DataFormatException {

        Case pos;
		Robot robot;
		int vitesse = 0; //modification original
		
		ignorerCommentaires();
		
        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();    
            pos = map.getCase(lig, col);
            
            String type = scanner.next();

            System.out.print("\t type = " + type);
            

            // lecture eventuelle d'une vitesse du robot (entier)
            System.out.print("; \t vitesse = ");
            String s = scanner.findInLine("(\\d+)");	// 1 or more digit(s) ?
            // pour lire un flottant:    ("(\\d+(\\.\\d+)?)");

            if (s == null) {
                System.out.print("valeur par defaut");
            } else {
                vitesse = Integer.parseInt(s);
                System.out.print(vitesse);
            }
            if (type.equals("DRONE")){
            	robot = new Drone(defvit(100, vitesse, s), pos);
            }else if (type.equals("PATTES")){
            	robot = new Patte(pos);
            }else if (type.equals("ROUES")){
            	robot = new Roue(defvit(80, vitesse, s), pos);
            }else if (type.equals("CHENILLES")){
            	robot = new Chenille(defvit(60, vitesse, s), pos);
            }else{
            	throw new IllegalArgumentException("Type de robot inconnu !");
            }
            
            verifieLigneTerminee();

            System.out.println();

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de robot invalide. "
                    + "Attendu: ligne colonne type [valeur_specifique]");
        }
        return robot;
    }

	private int defvit(int vit1, int vit2, String check) {
		if (check == null) {
			return vit1;
		}
		return vit2;
	}

	/** Ignore toute (fin de) ligne commencant par '#' */
	private void ignorerCommentaires() {
		while (scanner.hasNext("#.*")) {
			scanner.nextLine();
		}
	}

	/**
	 * Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
	 * 
	 * @throws ExceptionFormatDonnees
	 */
	private void verifieLigneTerminee() throws DataFormatException {
		if (scanner.findInLine("(\\d+)") != null) {
			throw new DataFormatException("format invalide, donnees en trop.");
		}
	}
}
