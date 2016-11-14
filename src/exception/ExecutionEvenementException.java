package exception;

/**
 * Classe fournissant une exception à lever lors d'un problème suvenu pendant un
 * événement. Classe héritant des exceptions.
 */
public class ExecutionEvenementException extends Exception {

	/**
	 * Construit une exception d'événement.
	 * 
	 * @param nomEvenement
	 *            nom de l'evenement d'où provient l'exception
	 * @param error
	 *            descripion textuelle de l'erreur
	 */
	public ExecutionEvenementException(String nomEvenement, String error) {
		System.out.println("Execution de l'évènement " + nomEvenement + " interrompue.\nErreur : " + error);
	}
}
