package train;

/**
 * The Main class is the entry point of the application. It creates a railway with stations and sections,
 * and then creates and starts trains on this railway.
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 */
public class Main {
	/**
	 * The main method of the application.
	 * It creates a railway with two stations (A and D) and three sections (AB, BC, CD).
	 * Then it creates three trains and starts them on the railway from station A in the left to right direction.
	 * If a train is in a bad position, it catches the BadPositionForTrainException and prints an error message.
	 * 
	 * @param args Command line arguments. Not used in this application.
	 */
	public static void main(String[] args) {

		Station A = new Station("GareA", 3, 3);
		Station D = new Station("GareD", 2, 0);

		Section AB = new Section("AB   ");
		Section BC = new Section("BC   ");
		Section CD = new Section("CD   ");
		Railway r = new Railway(new Element[] { A, AB, BC, CD, D }, new Station[] { A, D });
		System.out.println("The railway is:");
		System.out.println("\t" + r);
		Position p = new Position(A, Direction.LR);
		try {
			for (int i = 1; i <= 3; i++) {
				Train t = new Train(Integer.toString(i), p);
				System.out.println(t);
				Thread tr = new Thread(t);
				tr.start();
			}

		} catch (BadPositionForTrainException e) {
			System.out.println("Le train " + e.getMessage());
		}
	}
}
