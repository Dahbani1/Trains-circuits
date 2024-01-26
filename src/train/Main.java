package train;

/**
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 */
public class Main {
	public static void main(String[] args) {
		Station A = new Station("GareA", 5, 5);
		Station D = new Station("GareD", 5, 0);
		Section AB = new Section("AB");
		Section BC = new Section("BC");
		Section CD = new Section("CD");
		Railway r = new Railway(new Element[] { A, AB, BC, CD, D });
		System.out.println("The railway is:");
		System.out.println("\t" + r);
		Position p = new Position(A, Direction.LR);
		try {
			Train t1 = new Train("1", p);
			Train t2 = new Train("2", p);
			Train t3 = new Train("3", p);
			Train t4 = new Train("4", p);
			Train t5 = new Train("5", p);
			System.out.println(t1);
			System.out.println(t2);
			System.out.println(t3);
			System.out.println(t4);
			System.out.println(t5);
			Thread tr1=new Thread(t1);
			Thread tr2=new Thread(t2);
			Thread tr3=new Thread(t3);
			Thread tr4=new Thread(t4);
			Thread tr5=new Thread(t5);
			tr1.start();
			tr2.start();
			tr3.start();
			tr4.start();
			tr5.start();
		} catch (BadPositionForTrainException e) {
			System.out.println("Le train " + e.getMessage());
		}
		
		
		
	

	}
}
