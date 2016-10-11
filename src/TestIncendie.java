import objects.*;
 
public class TestIncendie {
	public static void main(String args[]) {
		Case test = new Case(15, 30, NatureTerrain.FORET);
		Incendie feu = new Incendie(test, 125);
		System.out.println(feu.toString());

        Incendie autreFeu = new Incendie(test, -20);
		System.out.println(feu.toString());
	}
}
