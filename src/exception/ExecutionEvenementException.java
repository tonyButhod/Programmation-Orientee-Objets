package exception;

public class ExecutionEvenementException extends Exception{
	public ExecutionEvenementException(String nomEvenement, String error) {
		System.out.println("Execution de l'évènement " + nomEvenement + " interrompue.\nErreur : " + error);
		}
}
