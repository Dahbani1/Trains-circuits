package train;

/**
 * Représentation d'un train. Un train est caractérisé par deux valeurs :
 * <ol>
 *   <li>
 *     Son nom pour l'affichage.
 *   </li>
 *   <li>
 *     La position qu'il occupe dans le circuit (un élément avec une direction) : classe {@link Position}.
 *   </li>
 * </ol>
 * 
 * @author Fabien Dagnat <fabien.dagnat@imt-atlantique.fr>
 * @author Mayte segarra <mt.segarra@imt-atlantique.fr>
 * Test if the first element of a train is a station
 * @author Philippe Tanguy <philippe.tanguy@imt-atlantique.fr>
 * @version 0.3
 */
public class Train implements Runnable {
	private final String name;
	private  Position pos;

	/**
	 * Constructor of the Train class. Initializes the Train with a name and a Position.
	 * @param name The name of the Train.
	 * @param p The Position of the Train.
	 * @throws BadPositionForTrainException if the Position's Element is not a Station.
	 * @throws NullPointerException if the name or the Position is null.
	 */
	public Train(String name, Position p) throws BadPositionForTrainException {
		if (name == null || p == null)
			throw new NullPointerException();

		// A train should be first be in a station
		if (!(p.getElement() instanceof Station))
			throw new BadPositionForTrainException(name);

		this.name = name;
		this.pos = p.clone();
	}

	
	/**
	 * Returns a string representation of the Train.
	 * @return A string representation of the Train.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Train[");
		result.append(this.name);
		result.append("]");
		result.append(" is on ");
		result.append(this.pos);
		return result.toString();
	}
	
	
	/**
	 * Returns the Element of the Train's Position.
	 * @return The Element of the Train's Position.
	 */
	public Element getElement() {
		return this.pos.getElement();
	}
	
	
	/**
	 * Returns the Position of the Train.
	 * @return The Position of the Train.
	 */
	public Position getPosition() {
		return this.pos;
	}
	
	
	/**
	 * Returns the next Element in the Train's Position.
	 * @return The next Element in the Train's Position.
	 */
	public Element nextElement() {
		return this.getElement().nextElement(pos);
	}
	
	
	/**
	 * Moves the Train to the next Element in its Position.
	 */
	public void move() {
		Element nxtElement = this.nextElement();
		Element currElement = this.getElement();
		nxtElement.allowTrain(this);
		currElement.notifyTrains(this);
		System.out.println("\n"+this);
	}
	
	
	/**
	 * The run method of the Runnable interface. It continuously moves the Train and then makes it sleep for 1 millisecond.
	 */
	public void run() {
		while(true) {
			this.move();
			try {
			    Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
